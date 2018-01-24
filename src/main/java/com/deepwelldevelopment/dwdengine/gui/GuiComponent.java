package com.deepwelldevelopment.dwdengine.gui;

import com.deepwelldevelopment.dwdengine.Window;

/**
 * The base GUI Component.
 */
public abstract class GuiComponent {

    Window window;

    float x;
    float y;

    float width;
    float height;

    public GuiComponent(Window window, float x, float y, float width, float height) {
        this.window = window;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render();
}
