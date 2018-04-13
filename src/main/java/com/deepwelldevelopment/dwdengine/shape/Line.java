package com.deepwelldevelopment.dwdengine.shape;

import com.deepwelldevelopment.dwdengine.Window;
import com.deepwelldevelopment.dwdengine.shader.BasicShader;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Line extends Shape {

    public Line(Window window, Vector3f start, Vector3f end) {
        super(window, start.x, start.y, start.z, false);
        vertices = BufferUtils.createFloatBuffer(2 * 3);
        vertices.put(start.x).put(start.y).put(start.z);
        vertices.put(end.x).put(end.y).put(end.z);
        vertices.flip();

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
        glBindBuffer(GL_ARRAY_BUFFER, colorBuffer);
        glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW);
    }

    @Override
    public void setOutlineColor(float r, float g, float b) {
        //dows nothing, lines dont have an outline
    }

    @Override
    public void notifySizeChange() {

    }

    @Override
    public boolean isPointInShape(float x, float y) {
        return false;
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
        glDrawArrays(GL_LINES, 0, vertices.capacity());
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }
}
