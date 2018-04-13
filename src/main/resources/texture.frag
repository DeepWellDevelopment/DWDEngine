#version 330 core

in vec2 UV;
in float fragZ;

out vec4 color;

uniform sampler2D textureSampler;

void main() {
    color = texture(textureSampler, UV).rgba;
    gl_FragDepth = fragZ;
    if (color.a < 0.1) {
        discard;
    }
}