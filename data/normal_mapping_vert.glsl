#version 150
in vec4 gxl3d_Position;
in vec4 gxl3d_Normal;

out VertexData
{
  vec4 Position;
  vec4 OPosition;
  vec4 Normal;
  vec4 ONormal;
  vec4 UV;
} vertex;

// GLSL Hacker automatic uniforms
uniform mat4 gxl3d_ModelViewProjectionMatrix;
uniform mat4 gxl3d_ModelViewMatrix;

void main()
{
  vec4 P = gxl3d_ModelViewMatrix * gxl3d_Position;
  vertex.OPosition = P;
  gl_Position = gxl3d_ModelViewProjectionMatrix * gxl3d_Position;
  vertex.UV = normalize(P);
  vertex.Position = gxl3d_Position;
  vertex.Normal = gxl3d_ModelViewMatrix * gxl3d_Normal;
  vertex.ONormal = gxl3d_Normal;
}