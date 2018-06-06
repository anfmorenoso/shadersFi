varying vec4 vertColor;
varying vec3 cameraDirection;
varying vec3 lightDirectionReflected;
varying vec3 ecNormal;
varying vec3 lightDir;

void main() {
  vec3 direction = normalize(lightDir);
  vec3 normal = normalize(ecNormal);
  vec3 direction_r = normalize(lightDirectionReflected);
  vec3 camera = normalize(cameraDirection);
  float intensity = max(0.0, dot(direction,normal)+dot(direction_r, camera));
  gl_FragColor = vec4(intensity, intensity, intensity, 1) * vertColor;
}
