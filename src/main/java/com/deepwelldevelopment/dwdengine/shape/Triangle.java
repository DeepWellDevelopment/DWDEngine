package com.deepwelldevelopment.dwdengine.shape;

import com.deepwelldevelopment.dwdengine.shader.BasicShader;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class Triangle extends Shape {

    public Triangle(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3) {
        vertices = BufferUtils.createFloatBuffer(3 * 3);
        vertices.put(x1).put(y1).put(z1);
        vertices.put(x2).put(y2).put(z2);
        vertices.put(x3).put(y3).put(z3);
        vertices.flip();

        shader = new BasicShader();
        shader.useProgram();

        buffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, buffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    @Override
    public void render() {
        shader.useProgram();
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, buffer);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glDrawArrays(GL_TRIANGLES, 0, vertices.capacity());
        glDisableVertexAttribArray(0);
    }
}
