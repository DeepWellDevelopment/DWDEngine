#version 330 core

layout(location = 0) in vec2 vertexPosition_screenspace;
layout(location = 1) in vec3 color;

uniform vec2 screen;

out vec3 vertexColor;

void main() {
    vec2 vertexPosition = vertexPosition_screenspace - (screen/2);
    vertexPosition /= (screen/2);
	gl_Position = vec4(vertexPosition, 0.0, 1.0);
	vertexColor = color;
}
