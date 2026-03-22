package objectdata;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;
import transforms.Vec3D;

public class Arrow extends Solid{

    Vertex a = new Vertex(new Point3D(0, 0, 0), new Col(1,0,0), new Vec2D(1,1));
    Vertex b = new Vertex(new Point3D(0.8, 0, 0) , new Col(1,0,0), new Vec2D(0.5,0));
    Vertex c = new Vertex(new Point3D(0.8, -0.2, 0), new Col(1,0,0), new Vec2D(1,0));
    Vertex d = new Vertex(new Point3D(1, 0, 0) , new Col(1,0,0), new Vec2D(0.5,0));
    Vertex e = new Vertex(new Point3D(0.8, 0.2, 0), new Col(1,0,0), new Vec2D(1,0));
    public Arrow() {
        vertexBuffer.add(a);
        vertexBuffer.add(b);
        vertexBuffer.add(c);
        vertexBuffer.add(d);
        vertexBuffer.add(e);

        addIndices(0, 1);
        addIndices(4, 3, 2);

        partBuffer.add(new Part(TopologyType.LINES, 0, 1));
        partBuffer.add(new Part(TopologyType.TRIANGLES, 2, 1));

    }

    
    
}
