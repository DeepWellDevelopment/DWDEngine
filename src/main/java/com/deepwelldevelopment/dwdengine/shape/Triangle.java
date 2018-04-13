package com.deepwelldevelopment.dwdengine.shape;

import com.deepwelldevelopment.dwdengine.Window;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Triangle extends Shape {

    private float x1;
    private float y1;
    private float z1;
    private float x2;
    private float y2;
    private float z2;
    private float x3;
    private float y3;
    private float z3;

    public Triangle(Window window, float x1, float y1, float z1, float x2, float
            y2, float z2, float x3, float y3, float z3) {
        super(window, x1, y1, z1, false);

        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.x3 = x3;
        this.y3 = y3;
        this.z3 = z3;

        vertices = BufferUtils.createFloatBuffer(3 * 3);
        vertices.put(x1).put(y1).put(z1);
        vertices.put(x2).put(y2).put(z2);
        vertices.put(x3).put(y3).put(z3);
        vertices.flip();

        outlineVertices = BufferUtils.createFloatBuffer(3 * 3);

        color = BufferUtils.createFloatBuffer(3 * 3);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.flip();

        outlineColor = BufferUtils.createFloatBuffer(3 * 2 * 3 * 3);

        vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        outlineBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, outlineBuffer);
        glBufferData(GL_ARRAY_BUFFER, outlineVertices, GL_STATIC_DRAW);

        colorBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, colorBuffer);
        glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW);

        outlineColorBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, outlineColorBuffer);
        glBufferData(GL_ARRAY_BUFFER, outlineColor, GL_STATIC_DRAW);
    }

    @Override
    public void setOutlineWidth(float width) {

    }

    @Override
    public void setColor(float r, float g, float b) {
        color.rewind();
        color.put(r).put(g).put(b);
        color.put(r).put(g).put(b);
        color.put(r).put(g).put(b);
        color.flip();
        glBindBuffer(GL_ARRAY_BUFFER, colorBuffer);
        glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW);
    }

    @Override
    public void setOutlineColor(float r, float g, float b) {

    }

    @Override
    public void notifySizeChange() {
        vertices.rewind();
        vertices.put(x1).put(y1).put(z1);
        vertices.put(x2).put(y2).put(z2);
        vertices.put(x3).put(y3).put(z3);
        vertices.flip();

        vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    @Override
    public boolean isPointInShape(float x, float y) {
        if (x < x1 && x < x2 && x < x3)
            return false;
        if (x > x1 && x > x2 && x > x3)
            return false;
        if (y < y1 && y < y2 && y < y3)
            return false;
        return !(y > y1) || !(y > y2) || !(y > y3);
    }

    @Override
    public void render() {
        shader.useProgram();
        shader.loadVector2(screenLocation, new Vector2f(window.getWidth(), window.getHeight()));
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, colorBuffer);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
        glDrawArrays(GL_TRIANGLES, 0, vertices.capacity());
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
    }
}
