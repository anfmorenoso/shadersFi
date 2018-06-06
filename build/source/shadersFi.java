import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import remixlab.proscene.*; 
import remixlab.dandelion.core.*; 
import remixlab.dandelion.geom.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class shadersFi extends PApplet {

//1. Combine all the simple lighting models using per-vertex and per-pixel shaders
//2. Use up to 8 lights in the model
//3. Implement other simple light model such as normal mapping or Warn lights




Boolean test_pixel = false;
Boolean test_vertex = false;
Boolean diffuse_vertex = false;
Boolean diffuse_pixel = false;
Boolean specular_vertex = false;
Boolean specular_pixel = false;
Boolean warn_vertex = false;
Boolean normal_mapping = false;
String mode = "No shader";

PShape can;

PShader pvertex_diffuse, ppixel_diffuse, pvertex_specular, ppixel_specular, pvertex_warn, ppixel_warn, pvertex_test, ppixel_test, normal_mapping_shader;

Scene scene;
InteractiveFrame[] lights;
InteractiveFrame shape, custom_eye;

public void setup() {
  

  can = createCan(100, 200, 32);

  pvertex_test = loadShader("diffuse_pvertex_test_frag.glsl", "diffuse_pvertex_test_vert.glsl");
  ppixel_test = loadShader("diffuse_ppixel_test_frag.glsl", "diffuse_ppixel_test_vert.glsl");
  //ppixel_test = loadShader("lightfrag.glsl", "lightvert.glsl");
  pvertex_diffuse = loadShader("diffuse_pvertex_frag.glsl", "diffuse_pvertex_vert.glsl");
  ppixel_diffuse = loadShader("diffuse_ppixel_frag.glsl", "diffuse_ppixel_vert.glsl");
  pvertex_specular = loadShader("specular_pvertex_frag.glsl", "specular_pvertex_vert.glsl");
  ppixel_specular = loadShader("specular_ppixel_frag.glsl", "specular_ppixel_vert.glsl");
  normal_mapping_shader = loadShader("normal_mapping_frag.glsl", "normal_mapping_vert.glsl");

  scene = new Scene(this);
  lights = new InteractiveFrame[1];

  for (int i = 0; i < lights.length; i++) {
    lights[i] = new InteractiveFrame(scene, lightShape(255,255,0));
    lights[i].translate(random(-100, 100), random(-100, 100), random(-100, 100));
  }
  shape = new InteractiveFrame(scene, createCan(50,100,100));

  println("Ligth: " + mode);
}

public void draw() {
  background(0);
  noLights();

  //pvertex_test.set("light0Position",lights[0].position().x(), lights[0].position().y(), lights[0].position().z());
  if(test_vertex)
    shader(pvertex_test);
  if(test_pixel)
    shader(ppixel_test);
  if(diffuse_vertex)
    shader(pvertex_diffuse);
  if(diffuse_pixel)
    shader(ppixel_diffuse);
  if(specular_vertex)
    shader(pvertex_specular);
  if(specular_pixel)
    shader(ppixel_specular);
  //if(warn_vertex)
  //  shader(pvertex_warn);
  if(normal_mapping)
    shader(normal_mapping_shader);

  pointLight(255, 255, 255, lights[0].position().x(), lights[0].position().y(), lights[0].position().z());
  //pointLight(255, 255, 255, lights[1].position().x(), lights[1].position().y(), lights[1].position().z());
  //pointLight(255, 255, 255, lights[2].position().x(), lights[2].position().y(), lights[2].position().z());
  //pointLight(255, 255, 255, lights[3].position().x(), lights[3].position().y(), lights[3].position().z());
  //pointLight(255, 255, 255, lights[4].position().x(), lights[4].position().y(), lights[4].position().z());
  //pointLight(255, 255, 255, lights[5].position().x(), lights[5].position().y(), lights[5].position().z());
  //pointLight(255, 255, 255, lights[6].position().x(), lights[6].position().y(), lights[6].position().z());
  //pointLight(255, 255, 255, lights[7].position().x(), lights[7].position().y(), lights[7].position().z());

  scene.drawFrames();
}

public void customBindings1() {
  scene.mouseAgent().setPickingMode(MouseAgent.PickingMode.MOVE);
  // eyeFrame() and frame
  for (InteractiveFrame iFrame : scene.frames()) {
    iFrame.removeBindings();
    iFrame.setMotionBinding(LEFT, "rotate");
    iFrame.setMotionBinding(RIGHT, "translate");
    iFrame.setMotionBinding(CENTER, "scale");
    iFrame.setMotionBinding((Event.SHIFT | Event.CTRL), RIGHT, "screenTranslate");
    iFrame.setMotionBinding(MouseAgent.WHEEL_ID, "scale");
    iFrame.setClickBinding(LEFT, 1, "center");
    iFrame.setClickBinding(RIGHT, 1, "align");
    println(iFrame == scene.eyeFrame() ? "eyeFrame BINDINGS" : "frame BINDINGS");
    println(iFrame.info());
  }
}

public void keyPressed() {
  if(key == '0'){
    test_vertex =!test_vertex;
    test_pixel = false;
    diffuse_vertex = false;
    diffuse_pixel = false;
    specular_vertex = false;
    specular_pixel = false;
    warn_vertex = false;
    normal_mapping = false;
    mode = "0. diffuse + specular vertex";
    println("Ligth: " + mode);
  }
  if(key == '9'){
    test_pixel =!test_pixel;
    test_vertex = false;
    diffuse_vertex = false;
    diffuse_pixel = false;
    specular_vertex = false;
    specular_pixel = false;
    warn_vertex = false;
    normal_mapping = false;
    mode = "9. diffuse + specular pixel";
    println("Ligth: " + mode);
  }
  if(key == '1'){
    diffuse_vertex =!diffuse_vertex;
    test_pixel = false;
    test_vertex = false;
    diffuse_pixel = false;
    specular_vertex = false;
    specular_pixel = false;
    warn_vertex = false;
    normal_mapping = false;
    mode = "1. pvertex_diffuse";
    println("Ligth: " + mode);
  }
  if(key == '2'){
    diffuse_pixel =!diffuse_pixel;
    test_pixel = false;
    test_vertex = false;
    diffuse_vertex = false;
    specular_vertex = false;
    specular_pixel = false;
    warn_vertex = false;
    normal_mapping = false;
    mode = "2. ppixel_diffuse";
    println("Ligth: " + mode);
  }  if(key == '3'){
    specular_vertex =!specular_vertex;
    test_pixel = false;
    test_vertex = false;
    diffuse_pixel = false;
    diffuse_vertex = false;
    specular_pixel = false;
    warn_vertex = false;
    normal_mapping = false;
    mode = "3. pvertex_specular";
    println("Ligth: " + mode);
  }
  if(key == '4'){
    specular_pixel =!specular_pixel;
    test_pixel = false;
    test_vertex = false;
    diffuse_vertex = false;
    specular_vertex = false;
    diffuse_pixel = false;
    warn_vertex = false;
    normal_mapping = false;
    mode = "4. ppixel_specular";
    println("Ligth: " + mode);
  }
  if(key == '5'){
    normal_mapping =!normal_mapping;
    test_pixel = false;
    test_vertex = false;
    diffuse_vertex = false;
    specular_vertex = false;
    diffuse_pixel = false;
    warn_vertex = false;
    specular_pixel = false;
    mode = "5. normal_mapping";
    println("Ligth: " + mode);
  }
  if (scene.mouseAgent().pickingMode() == MouseAgent.PickingMode.CLICK)
     customBindings1();
}

public PShape lightShape(float r, float g, float b) {
  PShape s = createShape(SPHERE, 10);
  s.setStroke(color(r,g,b));
  s.setFill(color(r,g,b));
  return s;
}

public PShape createCan(float r, float h, int detail) {
  textureMode(NORMAL);
  PShape sh = createShape();
  sh.beginShape(QUAD_STRIP);
  sh.noStroke();
  sh.fill(255);
  for (int i = 0; i <= detail; i++) {
    float angle = TWO_PI / detail;
    float x = sin(i * angle);
    float z = cos(i * angle);
    float u = PApplet.parseFloat(i) / detail;
    sh.normal(x, 0, z);
    sh.vertex(x * r, -h/2, z * r, u, 0);
    sh.vertex(x * r, +h/2, z * r, u, 1);
  }
  sh.endShape();
  return sh;
}
  public void settings() {  size(650, 650, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "shadersFi" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
