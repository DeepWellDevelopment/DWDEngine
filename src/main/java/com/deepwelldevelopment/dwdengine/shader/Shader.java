package com.deepwelldevelopment.dwdengine.shader;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;

import static com.deepwelldevelopment.dwdengine.shader.ShaderLoader.loadShaders;
import static org.lwjgl.opengl.GL20.*;

public abstract class Shader {

    protected int id;

    public Shader(String vertexFile, String fragmentFile) {
        try {
            id = loadShaders(vertexFile, fragmentFile);
        } catch (IOException e) {
            System.err.println("Error loading vertex shader " + vertexFile + " and fragment shader " + fragmentFile);
        }
        useProgram();
    }

    public void destruct() {
        glDeleteProgram(id);
    }

    public void useProgram() {
        glUseProgram(id);
    }

    public void loadInt(int location, int value) {
        glUniform1i(location, value);
    }

    public void loadFloat(int location, float value) {
        glUniform1f(location, value);
    }

    public void loadVector2(int location, Vector2f vect) {
        glUniform2fv(location, vect.get(BufferUtils.createFloatBuffer(2)));
    }

    public void loadVector3(int location, Vector3f vect) {
        glUniform3fv(location, vect.get(BufferUtils.createFloatBuffer(3)));
    }

    public void loadVector4(int location, Vector4f vect) {
        glUniform4fv(location, vect.get(BufferUtils.createFloatBuffer(4)));
    }

    public void loadMatrix4(int location, Matrix4f mat) {
        glUniformMatrix4fv(location, false, mat.get(BufferUtils.createFloatBuffer(16)));
    }

    protected abstract void getUniforms();
}
