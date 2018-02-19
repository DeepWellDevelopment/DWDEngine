#version 330 core

layout(location = 0) in vec2 vertexPosition_modelspace;
layout(location = 1) in vec2 vertexUV;

uniform vec2 screen;

out vec2 UV;

void main() {
    vec2 vertexPosition = vertexPosition_modelspace - (screen/2);
    vertexPosition /= (screen/2);
    gl_Position = vec4(vertexPosition, 0.0, 1.0);
    UV = vertexUV;
}

