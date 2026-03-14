package objectdata;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;

public class Quad extends Solid{
    Vertex a = new Vertex(new Point3D(400, 0, 1), new Col(1.,0,0), new Vec2D(1,1));
    Vertex b = new Vertex(new Point3D(0, 300, 1) , new Col(1.,0,0), new Vec2D(0,1));
    Vertex c = new Vertex(new Point3D(599, 599, 0), new Col(1.,0,0), new Vec2D(1,0));
    Vertex d = new Vertex(new Point3D(0, 599, 0), new Col(1.,0,0), new Vec2D(0,0));

    public Quad() {
        
    }
    
    
}