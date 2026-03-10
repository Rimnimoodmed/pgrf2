package objectdata;

import model.Vectorizable;
import transforms.Col;
import transforms.Point3D;

public class Vertex implements Vectorizable<Vertex>{
    private final Point3D position;
    private final Col color;
    //todo normal uv one
    public Vertex(Point3D position, Col color) {
        this.position = position;
        this.color = color;
    }
    public Point3D getPosition() {
        return position;
    }
    public Col getColor() {
        return color;
    }
    public double getX(){
        return position.getX();
    }
    public double getY(){
        return position.getY();
    }
    public double getZ(){
        return position.getZ();
    }
    @Override
    public Vertex mul(double d) {
        return new Vertex(this.position.mul(d), this.color.mul(d));
    }

    @Override
    public Vertex add(Vertex v) {
        return new Vertex(this.position.add(v.getPosition()), this.color.add(v.getColor()));
    }

}
