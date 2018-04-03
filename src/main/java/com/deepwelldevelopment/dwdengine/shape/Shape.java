package com.deepwelldevelopment.dwdengine.shape;

import com.deepwelldevelopment.dwdengine.Window;
import com.deepwelldevelopment.dwdengine.shader.BasicShader;
import com.deepwelldevelopment.dwdengine.shader.Shader;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;

/**
 * Represents a basic primitive shape which can be rendered to an OpenGL context. By default these basic shapes are not
 * textured. To get a textured primitive shape, use one of the TexturedShape classes such as {@link TexturedQuad}
 *
 * @author CJ Schaefer
 * @since 1.0
 */
public abstract class Shape {

    protected FloatBuffer vertices;
    protected FloatBuffer outlineVertices;
    protected FloatBuffer color;
    protected FloatBuffer outlineColor;
    Shader shader;
    int vertexBuffer;
    int outlineBuffer;
    int colorBuffer;
    int outlineColorBuffer;

    float x;
    float y;
    float z;
    float outlineWidth;

    int screenLocation;

    Window window;

    boolean fill;

    protected Shape(Window window, float x, float y, float z, boolean fill) {
        this.window = window;
        this.x = x;
        this.y = y;
        this.z = z;
        this.fill = fill;

        shader = new BasicShader();
        shader.useProgram();
        screenLocation = glGetUniformLocation(shader.getId(), "screen");
    }

    public abstract void setOutlineWidth(float width);

    public abstract void setColor(float r, float g, float b);

    public abstract void setOutlineColor(float r, float g, float b);

    /**
     * Draws the shape using the active OpenGL context
     */
    public abstract void render();

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
