package com.deepwelldevelopment.dwdengine.gui;

import com.deepwelldevelopment.dwdengine.Window;

/**
 * The base GUI Component.
 */
public abstract class GuiComponent {

    protected Window window;

    private float x;
    private float y;

    private float width;
    private float height;

    public GuiComponent(Window window, float x, float y, float width, float height) {
        this.window = window;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public abstract void render();
}
