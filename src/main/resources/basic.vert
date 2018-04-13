#version 330 core

layout(location = 0) in vec3 vertexPosition_screenspace;
layout(location = 1) in vec3 color;

uniform vec2 screen;

out vec3 vertexColor;
out float fragZ;

void main() {
    vec2 vertexPosition = vec2(vertexPosition_screenspace) - (screen/2);
    vertexPosition /= (screen/2);
	gl_Position = vec4(vertexPosition, 0.0, 1.0);
	vertexColor = color;
	fragZ = vertexPosition_screenspace.z;
}
