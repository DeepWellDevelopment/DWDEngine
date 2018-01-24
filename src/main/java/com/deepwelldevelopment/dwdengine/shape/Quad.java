package com.deepwelldevelopment.dwdengine.shape;

import com.deepwelldevelopment.dwdengine.shader.BasicShader;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class Quad extends Shape {

    public Quad(float x, float y, float z, float width, float height) {
        vertices = BufferUtils.createFloatBuffer(2 * 3 * 3);
        vertices.put(x).put(y).put(z);
        vertices.put(x).put(y + height).put(z);
        vertices.put(x+width).put(y+height).put(z);
        vertices.put(x+width).put(y+height).put(z);
        vertices.put(x+width).put(y).put(z);
        vertices.put(x).put(y).put(z);
        vertices.flip();
        
        color = BufferUtils.createFloatBuffer(2 * 3 * 3);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.put(1).put(1).put(1);
        color.flip();

        shader = new BasicShader();
        shader.useProgram();

        vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        colorBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, colorBuffer);
        glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW);
    }

    /**
     * Quad constructor. This constructor initializes a square shaped quad
     *
     * @param x
     * @param y
     * @param z
     * @param size
     */
    public Quad(float x, float y, float z, float size) {
        this(x, y, z, size, size);
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
    public void render() {
        shader.useProgram();
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
