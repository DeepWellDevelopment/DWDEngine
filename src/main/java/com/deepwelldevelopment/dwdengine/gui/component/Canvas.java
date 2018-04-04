package com.deepwelldevelopment.dwdengine.gui.component;


import com.deepwelldevelopment.dwdengine.Window;
import com.deepwelldevelopment.dwdengine.gui.event.InputEvent;
import com.deepwelldevelopment.dwdengine.shape.Quad;
import com.deepwelldevelopment.dwdengine.shape.Shape;
import org.joml.Vector3f;

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

    private ArrayList<GuiComponent> children;
    private ArrayList<Shape> shapes;

    Vector3f color;

    /**
     * The internal representation of this canvas object. Used for rendering
     */
    private Quad quad;

    public Canvas(Window window, float x, float y, float width, float height) {
        super(window, x, y, width, height);

        children = new ArrayList<>();
        shapes = new ArrayList<>();
        color = new Vector3f(0.0f, 0.0f, 0.0f);
        quad = new Quad(window, x, y, 0, width, height);
        quad.setOutlineWidth(0);
    }

    @Override
    public void handleEvent(InputEvent e) {
        super.handleEvent(e);
        //handle the event here

        //delegate to children to handle the event
        for (GuiComponent c : children) {
            c.handleEvent(e);
        }
    }

    public void setColor(float r, float g, float b) {
        color.set(r, g, b);
        quad.setColor(r, g, b);
        quad.setOutlineColor(0, 0, 0);
    }

    @Override
    public void render() {
        for (GuiComponent c : children) {
            c.render();
        }
        for (Shape s : shapes) {
            s.render();
        }

        //self rendering
        quad.render();
    }

    @Override
    public void notifySizeChange() {
        quad.setWidth(getWidth());
        quad.setHeight(getHeight());
        quad.setX(getX());
        quad.setY(getY());
    }

    public void addComponent(GuiComponent component) {
        children.add(component);
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public void setOutlineColor(float r, float g, float b) {
        quad.setOutlineColor(r, g, b);
    }
}
