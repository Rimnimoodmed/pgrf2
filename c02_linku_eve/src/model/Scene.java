package model;

import java.util.ArrayList;
import java.util.List;

import objectdata.Solid;
import transforms.Camera;
import transforms.Col;
import transforms.Mat4;
import transforms.Mat4OrthoRH;
import transforms.Mat4PerspRH;
import transforms.Vec3D;
import view.Panel;

public class Scene {
    protected final List<Solid> solidBuffer = new ArrayList<>();
    private Camera view;
    private Mat4 projectionPersp;
    private Mat4 projectionOrtho;
    private Mat4 projection;
    private Col ambientColor = new Col(128,128,255);
    public Scene(Panel panel) {
        view = new Camera((new Vec3D(0, 0, 0)), 0,0, 5, true);
        projectionPersp = new Mat4PerspRH(Math.PI/2, (double) panel.getHeight() /panel.getWidth(),0.1,10000);
        projectionOrtho = new Mat4OrthoRH(80,60,0.1,5000);
        projection = projectionPersp;
    }
    public List<Solid> getSolidBuffer() {
        return solidBuffer;
    }
    public Camera getView() {
        return view;
    }
    public void setView(Camera view) {
        this.view = view;
    }
    public Mat4 getProjectionPersp() {
        return projectionPersp;
    }
    public void setProjectionPersp(Mat4 projectionPersp) {
        this.projectionPersp = projectionPersp;
    }
    public Mat4 getProjectionOrtho() {
        return projectionOrtho;
    }
    public void setProjectionOrtho(Mat4 projectionOrtho) {
        this.projectionOrtho = projectionOrtho;
    }
    public Mat4 getProjection() {
        return projection;
    }
    public void setProjection(Mat4 projection) {
        this.projection = projection;
    }
    public Col getAmbientColor() {
        return ambientColor;
    }
    public void setAmbientColor(Col ambientColor) {
        this.ambientColor = ambientColor;
    }
    
}
