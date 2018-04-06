package com.deepwelldevelopment.dwdengine.util;

import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.deepwelldevelopment.dwdengine.shader.ShaderLoader.ioResourceToByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {

    private String name;
    private float startU;
    private float startV;
    private float endU;
    private float endV;

    private FloatBuffer uvCoordinates;

    private int textureBuffer;

    public Texture(String name, float startU, float startV, float endU, float endV) {
        this.name = name;
        this.startU = startU;
        this.startV = startV;
        this.endU = endU;
        this.endV = endV;

        uvCoordinates = BufferUtils.createFloatBuffer(2 * 3 * 2);
        uvCoordinates.put(startU).put(startV);
        uvCoordinates.put(startU).put(endV);
        uvCoordinates.put(endU).put(endV);
        uvCoordinates.put(endU).put(endV);
        uvCoordinates.put(endU).put(startV);
        uvCoordinates.put(startU).put(startV);
        uvCoordinates.flip();
        textureBuffer = loadTexture(name);
    }

    public static int loadTexture(String loc) {
        int tex = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, tex);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        ByteBuffer imageBuffer;
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);
        ByteBuffer image;
        try {
            imageBuffer = ioResourceToByteBuffer(loc, 16 * 16);
            if (!stbi_info_from_memory(imageBuffer, w, h, comp)) {
                throw new IOException("Failed to bind texture " + stbi_failure_reason());
            }
            image = stbi_load_from_memory(imageBuffer, w, h, comp, 0);
            if (image == null) {
                throw new IOException("Failed to read image" + stbi_failure_reason());
            }
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(0), h.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            stbi_image_free(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tex;
    }

    public String getName() {
        return name;
    }

    public FloatBuffer getUvCoordinates() {
        return uvCoordinates;
    }

    public int getTextureBuffer() {
        return textureBuffer;
    }
}
