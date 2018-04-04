package com.deepwelldevelopment.dwdengine.gui.component;

import com.deepwelldevelopment.dwdengine.Window;
import com.deepwelldevelopment.dwdengine.gui.event.InputEvent;
import com.deepwelldevelopment.dwdengine.gui.event.MouseEvent;
import com.deepwelldevelopment.dwdengine.gui.listener.IKeyboardListener;
import com.deepwelldevelopment.dwdengine.gui.listener.IMouseButtonPressListener;
import com.deepwelldevelopment.dwdengine.gui.listener.IMouseButtonReleaseListener;
import com.deepwelldevelopment.dwdengine.gui.listener.IMouseMotionListener;

import java.util.ArrayList;

/**
 * The base GUI Component.
 */
public abstract class GuiComponent {

    protected Window window;

    private float x;
    private float y;

    private float width;
    private float height;

    private ArrayList<IMouseMotionListener> mouseMotionListeners;
    private ArrayList<IMouseButtonPressListener> mouseButtonPressListeners;
    private ArrayList<IMouseButtonReleaseListener> mouseButtonReleaseListeners;
    private ArrayList<IKeyboardListener> keyboardListeners;

    public GuiComponent(Window window, float x, float y, float width, float height) {
        this.window = window;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        mouseMotionListeners = new ArrayList<>();
        mouseButtonPressListeners = new ArrayList<>();
        mouseButtonReleaseListeners = new ArrayList<>();
        keyboardListeners = new ArrayList<>();
    }

    public ArrayList<IMouseMotionListener> getMouseMotionListeners() {
        return mouseMotionListeners;
    }

    public void addMouseMotionListener(IMouseMotionListener listener) {
        mouseMotionListeners.add(listener);
    }

    public ArrayList<IMouseButtonPressListener> getMouseButtonPressListeners() {
        return mouseButtonPressListeners;
    }

    public void addMouseButtonReleaseListener(IMouseButtonReleaseListener listener) {
        mouseButtonReleaseListeners.add(listener);
    }

    public ArrayList<IMouseButtonReleaseListener> getMouseButtonReleaseListeners() {
        return mouseButtonReleaseListeners;
    }

    public void addMouseButtonListener(IMouseButtonPressListener listener) {
        mouseButtonPressListeners.add(listener);
    }

    public void addKeyboardListener(IKeyboardListener listener) {
        keyboardListeners.add(listener);
    }

    public ArrayList<IKeyboardListener> getKeyboardListeners() {
        return keyboardListeners;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void handleEvent(InputEvent e) {
        if (e instanceof MouseEvent) {
            MouseEvent me = (MouseEvent) e;
            if (me.getEventCode() == MouseEvent.MOVED) {
                getMouseMotionListeners().forEach((listener) -> {
                    if (!me.isConsumed()) {
                        listener.mouseMoved(me);
                    }
                });
            } else if (me.getEventCode() == MouseEvent.BUTTON_PRESS) {
                mouseButtonPressListeners.forEach((listener) -> {
                    if (!me.isConsumed()) {
                        listener.mouseDown(me);
                    }
                });
            } else if (me.getEventCode() == MouseEvent.BUTTON_RELEASE) {
                mouseButtonReleaseListeners.forEach((listener) -> {
                    if (!me.isConsumed()) {
                        listener.mouseUp(me);
                    }
                });
            }
        }
    }

    public abstract void render();

    public abstract void notifySizeChange();

    public boolean isPointInComponent(int x, int y) {
        return x > this.x && x < this.x + width && y > this.y && y < this.y + height;
    }
}
