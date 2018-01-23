package com.deepwelldevelopment.dwdengine.gui;

/**
 * The base GUI Component.
 */
public abstract class GuiComponent {

    float x;
    float y;

    float width;
    float height;

    public GuiComponent(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render();
}
