#version 330 core

out vec4 color;

in vec3 vertexColor;
in float fragZ;

void main() {
	color = vec4(vertexColor, 1);
	gl_FragDepth = fragZ;
}
