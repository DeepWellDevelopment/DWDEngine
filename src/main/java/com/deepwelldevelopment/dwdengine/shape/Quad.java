package com.deepwelldevelopment.dwdengine.shape;

import com.deepwelldevelopment.dwdengine.Window;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Quad extends Shape {

    float width;
    float height;

    public Quad(Window window, float x, float y, float z, float width, float height) {
        super(window, x, y, z, true);

        this.width = width;
        this.height = height;

        vertices = BufferUtils.createFloatBuffer(2 * 3 * 3);
        vertices.put(x).put(y).put(z);
        vertices.put(x).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y).put(z);
        vertices.put(x).put(y).put(z);
        vertices.flip();

        outlineVertices = BufferUtils.createFloatBuffer(4 * 2 * 3 * 3);

        color = BufferUtils.createFloatBuffer(2 * 3 * 3);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.flip();

        outlineColor = BufferUtils.createFloatBuffer(4 * 2 * 3 * 3);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.put(0).put(0).put(0);
        outlineColor.flip();

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

    /**
     * Quad constructor. This constructor initializes a square shaped quad
     *
     * @param x
     * @param y
     * @param z
     * @param size
     */
    public Quad(Window window, float x, float y, float z, float size) {
        this(window, x, y, z, size, size);
    }

    public Quad(Window window, int x, int y, int width, int height) {
        this(window, x, y, 0, width, height);
    }

    @Override
    public void setOutlineWidth(float outlineWidth) {
        vertices.rewind();
        vertices.put(x + outlineWidth).put(y + outlineWidth).put(z);
        vertices.put(x + outlineWidth).put(y + outlineWidth + (height - outlineWidth)).put(z);
        vertices.put(x + outlineWidth + (width - outlineWidth)).put(y + outlineWidth + (height - outlineWidth)).put(z);
        vertices.put(x + outlineWidth + (width - outlineWidth)).put(y + outlineWidth + (height - outlineWidth)).put(z);
        vertices.put(x + outlineWidth + (width - outlineWidth)).put(y + outlineWidth).put(z);
        vertices.put(x + outlineWidth).put(y + outlineWidth).put(z);
        vertices.flip();

        outlineVertices.rewind();
        //left outline
        outlineVertices.put(x).put(y).put(z);
        outlineVertices.put(x).put(y + height).put(z);
        outlineVertices.put(x + outlineWidth).put(y + height).put(z);
        outlineVertices.put(x + outlineWidth).put(y + height).put(z);
        outlineVertices.put(x + outlineWidth).put(y).put(z);
        outlineVertices.put(x).put(y).put(z);

        //top outline
        outlineVertices.put(x).put(y + height - outlineWidth).put(z);
        outlineVertices.put(x).put(y + height).put(z);
        outlineVertices.put(x + width).put(y + height).put(z);
        outlineVertices.put(x + width).put(y + height).put(z);
        outlineVertices.put(x + width).put(y + height - outlineWidth).put(z);
        outlineVertices.put(x).put(y + height - outlineWidth).put(z);

        //right outline
        outlineVertices.put(x + width - outlineWidth).put(y).put(z);
        outlineVertices.put(x + width - outlineWidth).put(y + height).put(z);
        outlineVertices.put(x + width).put(y + height).put(z);
        outlineVertices.put(x + width).put(y + height).put(z);
        outlineVertices.put(x + width).put(y).put(z);
        outlineVertices.put(x + width - outlineWidth).put(y).put(z);

        //bottom outline
        outlineVertices.put(x).put(y).put(z);
        outlineVertices.put(x).put(y + outlineWidth).put(z);
        outlineVertices.put(x + width).put(y + outlineWidth).put(z);
        outlineVertices.put(x + width).put(y + outlineWidth).put(z);
        outlineVertices.put(x + width).put(y).put(z);
        outlineVertices.put(x).put(y).put(z);
        outlineVertices.flip();
        this.outlineWidth = outlineWidth;

        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, outlineBuffer);
        glBufferData(GL_ARRAY_BUFFER, outlineVertices, GL_STATIC_DRAW);
    }

    @Override
    public void setColor(float r, float g, float b) {
        color.rewind();
        color.put(r).put(g).put(b);
        color.put(r).put(g).put(b);
        color.put(r).put(g).put(b);
        color.put(r).put(g).put(b);
        color.put(r).put(g).put(b);
        color.put(r).put(g).put(b);
        color.flip();
        glBindBuffer(GL_ARRAY_BUFFER, colorBuffer);
        glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW);
    }

    @Override
    public void setOutlineColor(float r, float g, float b) {
        outlineColor.rewind();
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.put(r).put(g).put(b);
        outlineColor.flip();
        glBindBuffer(GL_ARRAY_BUFFER, outlineColorBuffer);
        glBufferData(GL_ARRAY_BUFFER, outlineColor, GL_STATIC_DRAW);
    }

    @Override
    public void notifySizeChange() {

    }

    @Override
    public boolean isPointInShape(float x, float y) {
        return x > this.x && x < this.x + width && y > this.y && y < this.y + height;
    }

    @Override
    public void render() {
        shader.useProgram();
        shader.loadVector2(screenLocation, new Vector2f(window.getWidth(), window.getHeight()));
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, outlineBuffer);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, outlineColorBuffer);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
        glDrawArrays(GL_TRIANGLES, 0, outlineVertices.capacity());
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        if (fill) {
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

    public void setWidth(float width) {
        this.width = width;
        vertices.rewind();
        vertices.put(x).put(y).put(z);
        vertices.put(x).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y).put(z);
        vertices.put(x).put(y).put(z);
        vertices.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    public void setHeight(float height) {
        this.height = height;
        vertices.rewind();
        vertices.put(x).put(y).put(z);
        vertices.put(x).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y).put(z);
        vertices.put(x).put(y).put(z);
        vertices.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        vertices.rewind();
        vertices.put(x).put(y).put(z);
        vertices.put(x).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y).put(z);
        vertices.put(x).put(y).put(z);
        vertices.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        vertices.rewind();
        vertices.put(x).put(y).put(z);
        vertices.put(x).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y).put(z);
        vertices.put(x).put(y).put(z);
        vertices.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    @Override
    public void setZ(float z) {
        super.setZ(z);
        vertices.rewind();
        vertices.put(x).put(y).put(z);
        vertices.put(x).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y + height).put(z);
        vertices.put(x + width).put(y).put(z);
        vertices.put(x).put(y).put(z);
        vertices.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
