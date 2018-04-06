package com.deepwelldevelopment.dwdengine.shape;

import com.deepwelldevelopment.dwdengine.Window;
import com.deepwelldevelopment.dwdengine.shader.TextureShader;
import com.deepwelldevelopment.dwdengine.util.Texture;
import org.joml.Vector2f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class TexturedQuad extends Quad {

    private FloatBuffer uv;
    private int uvBuffer;
    private Texture texture;
    private TextureShader textureShader;
    private int textureID;

    public TexturedQuad(Window window, float x, float y, float z, float width, float height, String texture) {
        super(window, x, y, z, width, height);

        textureShader = new TextureShader(texture);
        uv = textureShader.getTexture().getUvCoordinates();

        uvBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, uvBuffer);
        glBufferData(GL_ARRAY_BUFFER, uv, GL_STATIC_DRAW);

        //dont attempt to fill the quad and texture, will result in strange behavior
        fill = false;
    }

    public TexturedQuad(Window window, float x, float y, float z, float size, String texture) {
        this(window, x, y, z, size, size, texture);
    }

    /**
     * Overridden to prevent strange rendering behavior by attempting to both fill the quad and texture it. Always sets
     * the fill flag to be false.
     *
     * @param fill Ignored
     */
    @Override
    public void setFill(boolean fill) {
        this.fill = false;
    }

    @Override
    public void render() {
        super.render(); //renders the outline
        textureShader.useProgram();
        textureShader.loadVector2(screenLocation, new Vector2f(window.getWidth(), window.getHeight()));
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, uvBuffer);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        glDrawArrays(GL_TRIANGLES, 0, vertices.capacity());
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glActiveTexture(0);
    }
}
