package com.deepwelldevelopment.dwdengine.shader;

import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;

public class BasicShader extends Shader {
    private int projectionViewMatrixLocation;
    private int modelMatrixLocation;
    private int projectionMatrixLocation;
    private int viewMatrixLocation;

    public BasicShader(String vertexFile, String fragmentFile) {
        super(vertexFile, fragmentFile);
        getUniforms();
    }

    public BasicShader() {
        super("basic", "basic");
        getUniforms();
    }

    public void loadProjectionViewMatrix(Matrix4f matrix) {
        loadMatrix4(projectionViewMatrixLocation, matrix);
    }

    public void loadModelMatrix(Matrix4f matrix) {
        loadMatrix4(modelMatrixLocation, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        loadMatrix4(projectionMatrixLocation, matrix);
    }

    public void loadViewMatrix(Matrix4f matrix) {
        loadMatrix4(viewMatrixLocation, matrix);
    }

    @Override
    protected void getUniforms() {
        useProgram();
        projectionViewMatrixLocation = glGetUniformLocation(id, "projViewMatrix");
        modelMatrixLocation = glGetUniformLocation(id, "modelMatrix");
        projectionMatrixLocation = glGetUniformLocation(id, "projectionMatrix");
        viewMatrixLocation = glGetUniformLocation(id, "viewMatrix");
    }
}
