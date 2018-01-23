package com.deepwelldevelopment.dwdengine.gui;


import com.deepwelldevelopment.dwdengine.shape.Shape;

import java.util.ArrayList;

/***
 *The base GUI Component. Acts as a container for components as well as a renderable component in of itself.
 * Canvases are usually top level components in a group of related components. Every window must has a root
 * canvas to which it renders by default. A canvas is the only gui component that can act as a container for other
 * gui components.
 *
 * @author CJ Schaefer
 * @since 1.0
 */
public class Canvas extends GuiComponent {

    ArrayList<GuiComponent> children;
    ArrayList<Shape> shapes;

    public Canvas() {
        this(0, 0, 0, 0);

    }

    public Canvas(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void render() {

    }
}
