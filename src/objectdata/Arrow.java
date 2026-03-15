package objectdata;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;

public class Arrow extends Solid{


    Vertex a = new Vertex(new Point3D(600, 300, 0.5), new Col(1.,0,0), new Vec2D(1,1));
    Vertex b = new Vertex(new Point3D(300, 300, 0.5) , new Col(1.,0,0), new Vec2D(0.5,0));
    Vertex c = new Vertex(new Point3D(300, 450, 0.5), new Col(1.,0,0), new Vec2D(1,0));
    Vertex d = new Vertex(new Point3D(100, 300, 0.5) , new Col(1.,0,0), new Vec2D(0.5,0));
    Vertex e = new Vertex(new Point3D(300, 150, 0.5), new Col(1.,0,0), new Vec2D(1,0));
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
