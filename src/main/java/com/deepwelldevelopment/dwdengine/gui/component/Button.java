package com.deepwelldevelopment.dwdengine.gui.component;

import com.deepwelldevelopment.dwdengine.Window;
import com.deepwelldevelopment.dwdengine.gui.event.InputEvent;
import com.deepwelldevelopment.dwdengine.gui.event.MouseEvent;
import com.deepwelldevelopment.dwdengine.gui.listener.IMouseButtonPressListener;
import com.deepwelldevelopment.dwdengine.gui.listener.IMouseButtonReleaseListener;
import com.deepwelldevelopment.dwdengine.shape.Quad;

public class Button extends GuiComponent {

    private IMouseButtonPressListener mouseDown = (e) -> {

    };
    private IMouseButtonReleaseListener mouseUp = (e) -> {

    };
    private Quad quad;

    public Button(Window window, float x, float y, float width, float height) {
        super(window, x, y, width, height);
        quad = new Quad(window, x, y, width, height);
        quad.setOutlineColor(0.95f, 0.0f, 0.95f);
    }

    @Override
    public void handleEvent(InputEvent e) {
        if (e instanceof MouseEvent) {
            MouseEvent me = ((MouseEvent) e);

            if (!isPointInComponent(me.getX(), me.getY())) {
                quad.setOutlineWidth(0);
                return;
            }

            if (me.getEventCode() == MouseEvent.BUTTON_PRESS) {
                mouseDown.mouseDown(me);
            } else if (me.getEventCode() == MouseEvent.BUTTON_RELEASE) {
                mouseUp.mouseUp(me);
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

    public void setMouseDown(IMouseButtonPressListener listener) {
        mouseDown = listener;
    }

    public void setMouseUp(IMouseButtonReleaseListener listener) {
        mouseUp = listener;
    }
}
