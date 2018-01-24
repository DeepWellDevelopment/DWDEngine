package com.deepwelldevelopment.dwdengine.shape;

import com.deepwelldevelopment.dwdengine.shader.Shader;

import java.nio.FloatBuffer;

/**
 * Represents a basic primitive shape which can be rendered to an OpenGL context.
 *
 * @author CJ Schaefer
 * @since 1.0
 */
public abstract class Shape {

    protected FloatBuffer vertices;
    protected FloatBuffer uvCoordinates;
    protected FloatBuffer color;
    Shader shader;
    int vertexBuffer;
    int colorBuffer;

    protected Shape() {
    }

    public abstract void setColor(float r, float g, float b);

    /**
     * Draws the shape using the active OpenGL context
     */
    public abstract void render();
}
