package com.deepwelldevelopment.dwdengine.shape;

import com.deepwelldevelopment.dwdengine.Window;
import com.deepwelldevelopment.dwdengine.shader.BasicShader;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Triangle extends Shape {

    public Triangle(Window window, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3) {
        super(window, x1, y1, z1, false);
        vertices = BufferUtils.createFloatBuffer(3 * 3);
        vertices.put(x1).put(y1).put(z1);
        vertices.put(x2).put(y2).put(z2);
        vertices.put(x3).put(y3).put(z3);
        vertices.flip();

        color = BufferUtils.createFloatBuffer(3 * 3);
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
