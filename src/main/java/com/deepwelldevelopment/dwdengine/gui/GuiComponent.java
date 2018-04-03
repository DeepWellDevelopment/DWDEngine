package com.deepwelldevelopment.dwdengine.gui;

import com.deepwelldevelopment.dwdengine.Window;

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
    private ArrayList<IMouseButtonPressListener> mouseButtonListeners;
    private ArrayList<IKeyboardListener> keyboardListeners;

    public GuiComponent(Window window, float x, float y, float width, float height) {
        this.window = window;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        mouseMotionListeners = new ArrayList<>();
        mouseButtonListeners = new ArrayList<>();
        keyboardListeners = new ArrayList<>();
    }

    public ArrayList<IMouseMotionListener> getMouseMotionListeners() {
        return mouseMotionListeners;
    }

    public void addMouseMotionListener(IMouseMotionListener listener) {
        mouseMotionListeners.add(listener);
    }

    public ArrayList<IMouseButtonPressListener> getMouseButtonListeners() {
        return mouseButtonListeners;
    }

    public void addMouseButtonListener(IMouseButtonPressListener listener) {
        mouseButtonListeners.add(listener);
    }

    public ArrayList<IKeyboardListener> getKeyboardListeners() {
        return keyboardListeners;
    }

    public void addKeyboardListener(IKeyboardListener listener) {
        keyboardListeners.add(listener);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.x = x;
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

    public abstract void handleEvent(InputEvent e);

    public abstract void render();

    public abstract void notifySizeChange();

    public boolean isPointInComponent(int x, int y) {
        return x > this.x && x < this.x + width && y > this.y && y < this.y + height;
    }
}
