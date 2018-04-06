package com.deepwelldevelopment.dwdengine.gui.component;

import com.deepwelldevelopment.dwdengine.Window;
import com.deepwelldevelopment.dwdengine.gui.event.InputEvent;
import com.deepwelldevelopment.dwdengine.gui.event.MouseEvent;
import com.deepwelldevelopment.dwdengine.shape.Quad;

public class Button extends GuiComponent {

    private IButtonListener onPress = () -> {
    };
    private Quad quad;

    private boolean consume;

    public Button(Window window, float x, float y, float width, float height) {
        super(window, x, y, width, height);
        quad = new Quad(window, x, y, 0, width, height);
        quad.setOutlineColor(0.95f, 0.0f, 0.95f);
        consume = true;
    }

    @Override
    public void handleEvent(InputEvent e) {
        if (e.isConsumed()) return;
        if (e instanceof MouseEvent) {
            MouseEvent me = ((MouseEvent) e);

            if (!isPointInComponent(me.getX(), me.getY())) {
                quad.setOutlineWidth(0);
                return;
            }

            if (me.getEventCode() == MouseEvent.BUTTON_PRESS) {
                //ignore, buttons are only considered pressed once the mouse has been released
            } else if (me.getEventCode() == MouseEvent.BUTTON_RELEASE) {
                onPress.onPress();
                if (consume) me.consume();
            } else if (me.getEventCode() == MouseEvent.MOVED) {
                quad.setOutlineWidth(5);
            }
        }
    }

    @Override
    public void render() {
        quad.render();
    }

    @Override
    public void notifySizeChange() {
        quad.setWidth(getWidth());
        quad.setHeight(getHeight());
        quad.setX(getX());
        quad.setY(getY());
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
