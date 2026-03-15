package objectdata;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;

public class Quad extends Solid{
    Vertex a = new Vertex(new Point3D(-1, -1, 0.5), new Col(0.,1,0), new Vec2D(1,1));
    Vertex b = new Vertex(new Point3D(-1, 1, 0.5) , new Col(0.,1,0), new Vec2D(0,1));
    Vertex c = new Vertex(new Point3D(1, 1, 0.5), new Col(0.,1,0), new Vec2D(1,0));
    Vertex d = new Vertex(new Point3D(1, -1, 0.5), new Col(0.,1,0), new Vec2D(0,0));

    public Quad() {
        vertexBuffer.add(a);
        vertexBuffer.add(b);
        vertexBuffer.add(c);
        vertexBuffer.add(d);

        addIndices(0, 1, 2);
        addIndices(0, 2, 3);

        partBuffer.add(new Part(TopologyType.TRIANGLES, 0, 2));

    }
}