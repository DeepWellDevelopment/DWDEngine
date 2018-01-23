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
    private FloatBuffer uvCoordinates;
    Shader shader;
    int buffer;

    /**
     * Draws the shape using the active OpenGL context
     */
    public abstract void render();
}
