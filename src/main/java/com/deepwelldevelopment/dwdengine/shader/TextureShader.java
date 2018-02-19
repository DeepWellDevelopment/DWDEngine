package com.deepwelldevelopment.dwdengine.shader;

import com.deepwelldevelopment.dwdengine.util.Texture;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;

public class TextureShader extends BasicShader {

    Texture texture;
    int textureID;

    public TextureShader(String textureName) {
        super("texture", "texture");
        texture = new Texture(textureName, 0.0f, 0.0f, 1.0f, 1.0f);
    }

    @Override
    public void useProgram() {
        super.useProgram();
        if (texture != null) {
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, texture.getTextureBuffer());
            glUniform1i(textureID, 0);
        }
    }

    @Override
    protected void getUniforms() {
        super.getUniforms();
        textureID = glGetUniformLocation(id, "textureSampler");
    }

    public Texture getTexture() {
        return texture;
    }
}
