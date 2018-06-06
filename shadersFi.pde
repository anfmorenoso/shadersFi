import frames.core.*;
import frames.primitives.*;
import frames.processing.*;
import frames.input.*;

Graph graph;
Node node1, node2, node3, eye;

float length = 50, theta = 0, r = 100;
PShader pvertex_diffuse, ppixel_diffuse, pvertex_specular, ppixel_specular, pvertex_warn, ppixel_warn, pvertex_test, ppixel_test, normal_mapping_shader;
int vertIndex = 0;
boolean rflag = true;

void setup(){

  pvertex_test = loadShader("diffuse_pvertex_test_frag.glsl", "diffuse_pvertex_test_vert.glsl");
  ppixel_test = loadShader("diffuse_ppixel_test_frag.glsl", "diffuse_ppixel_test_vert.glsl");
  pvertex_diffuse = loadShader("diffuse_pvertex_frag.glsl", "diffuse_pvertex_vert.glsl");
  ppixel_diffuse = loadShader("diffuse_ppixel_frag.glsl", "diffuse_ppixel_vert.glsl");
  pvertex_specular = loadShader("specular_pvertex_frag.glsl", "specular_pvertex_vert.glsl");
  ppixel_specular = loadShader("specular_ppixel_frag.glsl", "specular_ppixel_vert.glsl");

  size(700, 700, P3D);
  graph = new Scene(this);
  graph.setRadius(300);
  node1 = new OrbitNode(graph) {
    @Override
    public void visit() {
      pushStyle();
      stroke(0, 255, 0);
      fill(255, 0, 255, 125);
      float dim = graph.isInputGrabber(this) ? length*1.1 : length;
      if (graph().is3D()){
        noStroke();
        sphere(dim);
        axes(1, false);
      }
      else
        ellipse(0, 0, dim, dim);
      popStyle();
    }
  };
  node2 = new OrbitNode(node1) {
    @Override
    public void visit() {
      pushStyle();
      stroke(255, 0, 0);
      fill(255, 255, 255);
      float dim = graph.isInputGrabber(this) ? length*1.1 : length;
      float l = graph.isInputGrabber(this) ? r*1.1 : r;
      if (graph().is3D())
        drawCylinder(30, dim, l);
      else
        rect(0, 0, dim, dim);
      popStyle();
    }
  };

  node3 = new OrbitNode(node1) {
    @Override
    public void visit() {
      pushStyle();
      stroke(255, 0, 0);
      fill(0, 255, 255);
      float dim = graph.isInputGrabber(this) ? length*1.1 : length;
      float l = graph.isInputGrabber(this) ? r*1.1 : r;
      if (graph().is3D())
        drawCylinder(10, dim, l);
      else
        rect(0, 0, dim, dim);
      popStyle();
    }
  };
  node2.translate(new Vector(150, 150));
  node3.translate(new Vector(-150, -150));
  eye = new OrbitNode(graph);
  graph.setEye(eye);
  graph.setFieldOfView(PI / 3);
  //graph.setDefaultGrabber(eye);
  graph.fitBallInterpolation();
}

void draw()
{
  background(0);
  noLights();

  rotateZ(theta);
  rotateX(theta);

  graph.traverse();
  switch( vertIndex ){
    case 0:
      shader(pvertex_test);
      break;
    case 1:
      shader(ppixel_test);
      break;
    case 2:
      shader(pvertex_diffuse);
      break;
    case 3:
      shader(ppixel_diffuse);
      break;
    case 4:
      shader(pvertex_specular);
      break;
    case 5:
      shader(ppixel_specular);
      break;
  }

  pointLight(255, 255, 255, node1.position().x(), node1.position().y(), node1.position().z());

  if(rflag){
    theta += 0.02;
  }
}

void drawCylinder( int sides, float r, float h)
{
    float angle = 360 / sides;
    float halfHeight = h / 2;
    noStroke();
    // draw top of the tube
    beginShape();
    for (int i = 0; i < sides; i++) {
        float x = cos( radians( i * angle ) ) * r;
        float y = sin( radians( i * angle ) ) * r;
        vertex( x, y, -halfHeight);
    }
    endShape(CLOSE);

    // draw bottom of the tube
    beginShape();
    for (int i = 0; i < sides; i++) {
        float x = cos( radians( i * angle ) ) * r;
        float y = sin( radians( i * angle ) ) * r;
        vertex( x, y, halfHeight);
    }
    endShape(CLOSE);

    // draw sides
    beginShape(TRIANGLE_STRIP);
    for (int i = 0; i < sides + 1; i++) {
        float x = cos( radians( i * angle ) ) * r;
        float y = sin( radians( i * angle ) ) * r;
        vertex( x, y, halfHeight);
        vertex( x, y, -halfHeight);
    }
    endShape(CLOSE);

}

void keyPressed() {
  if(key == ' '){
    if( vertIndex < 5 ){
      vertIndex += 1;
    }
    else {
      vertIndex = 0;
    }
  }
  if( key == 'r' ){
    rflag = !rflag;
  }
  if (key == CODED)
    if (keyCode == UP)
      eye.translateYPos();
    else if (keyCode == DOWN)
      eye.translateYNeg();
    else if (keyCode == RIGHT)
      eye.translateXPos();
    else if (keyCode == LEFT)
      eye.translateXNeg();
}

void axes( float red, boolean flag ){
  if( flag ){
    pushStyle();
    // X-Axis
    strokeWeight(4 * red);
    stroke(255, 0, 0);
    fill(255, 0, 0);
    line(0, 0, 100 * red, 0);
    text("X", (100 + 5) * red, 0);
    // Y-Axis
    stroke(0, 0, 255);
    fill(0, 0, 255);
    line(0, 0, 0, 100 * red);
    text("Y", 0, (100 + 10) * red );
    // Z-Axis
    stroke(0, 255, 0);
    fill(0, 255, 0);
    line(0, 0, 0, 0, 0, 100 * red);
    text("Z", 0, 0, (100 + 5) * red );
    popStyle();
  }
}
