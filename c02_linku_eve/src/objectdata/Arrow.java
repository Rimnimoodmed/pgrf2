package objectdata;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;

public class Arrow extends Solid{


    Vertex a = new Vertex(new Point3D(6, 3, 0.5), new Col(1.,0,0), new Vec2D(1,1));
    Vertex b = new Vertex(new Point3D(3, 3, 0.5) , new Col(1.,0,0), new Vec2D(0.5,0));
    Vertex c = new Vertex(new Point3D(3, 4.5, 0.5), new Col(1.,0,0), new Vec2D(1,0));
    Vertex d = new Vertex(new Point3D(1, 3, 0.5) , new Col(1.,0,0), new Vec2D(0.5,0));
    Vertex e = new Vertex(new Point3D(3, 1.5, 0.5), new Col(1.,0,0), new Vec2D(1,0));
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
