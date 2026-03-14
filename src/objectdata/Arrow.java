package objectdata;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;

public class Arrow extends Solid{


    Vertex a = new Vertex(new Point3D(400, 0, 1), new Col(1.,0,0), new Vec2D(1,1));
    Vertex b = new Vertex(new Point3D(0, 300, 1) , new Col(1.,0,0), new Vec2D(0.5,0));
    Vertex c = new Vertex(new Point3D(599, 599, 0), new Col(1.,0,0), new Vec2D(1,0));
    Vertex d = new Vertex(new Point3D(0, 300, 1) , new Col(1.,0,0), new Vec2D(0.5,0));
    Vertex e = new Vertex(new Point3D(599, 599, 0), new Col(1.,0,0), new Vec2D(1,0));
    public Arrow() {
        vertexBuffer.add(a); // v0
        vertexBuffer.add(b); // v1
        vertexBuffer.add(c); // v2
        vertexBuffer.add(d); // v3
        vertexBuffer.add(e); // v4

        addIndices(0, 1); // lines
        addIndices(4, 3, 2); // triangles

    }

    
    
}
