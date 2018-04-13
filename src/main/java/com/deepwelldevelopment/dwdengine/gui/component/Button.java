package com.deepwelldevelopment.dwdengine.gui.component;

import com.deepwelldevelopment.dwdengine.Window;
import com.deepwelldevelopment.dwdengine.gui.event.InputEvent;
import com.deepwelldevelopment.dwdengine.gui.event.MouseEvent;
import com.deepwelldevelopment.dwdengine.shape.Quad;
import com.deepwelldevelopment.dwdengine.shape.Shape;

public class Button extends GuiComponent {

    private IButtonListener onPress = () -> {
    };
    private Shape shape;

    private boolean consume;

    public Button(Window window, float x, float y, float width, float height) {
        super(window, x, y, width, height);
        shape = new Quad(window, x, y, 0, width, height);
        shape.setOutlineColor(0.95f, 0.0f, 0.95f);
        consume = true;
    }

    public Button(Window window, Shape shape) {
        super(window, shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        this.shape = shape;
    }

    @Override
    public void handleEvent(InputEvent e) {
        if (e.isConsumed()) return;
        if (e instanceof MouseEvent) {
            MouseEvent me = ((MouseEvent) e);

            if (!isPointInComponent(me.getX(), me.getY())) {
                shape.setOutlineWidth(0);
                return;
            }

            if (me.getEventCode() == MouseEvent.BUTTON_PRESS) {
                //ignore, buttons are only considered pressed once the mouse has been released
            } else if (me.getEventCode() == MouseEvent.BUTTON_RELEASE) {
                onPress.onPress();
                if (consume) me.consume();
            } else if (me.getEventCode() == MouseEvent.MOVED) {
                shape.setOutlineWidth(5);
            }
        }
    }

    @Override
    public void render() {
        shape.render();
    }

    @Override
    public void notifySizeChange() {
        shape.setX(getX());
        shape.setY(getY());
        shape.notifySizeChange();
    }

    @Override
    public boolean isPointInComponent(int x, int y) {
        return shape.isPointInShape(x, y);
    }

    public void setOnPress(IButtonListener onPress) {
        this.onPress = onPress;
    }

    public void setConsume(boolean consume) {
        this.consume = consume;
    }

    public interface IButtonListener {
        void onPress();
    }
}
