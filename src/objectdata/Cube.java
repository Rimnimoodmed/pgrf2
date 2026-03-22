package objectdata;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;
import transforms.Vec3D;

public class Cube extends Solid{
    Vertex a = new Vertex(new Point3D(-1, -1, -1), new Col(1.,0,0), new Vec2D(1,1)); //0 red
    Vertex b = new Vertex(new Point3D(-1, -1, 1) , new Col(1.,1,0), new Vec2D(0,1)); //1 yellow
    Vertex c = new Vertex(new Point3D(-1, 1, -1), new Col(1,0,1), new Vec2D(1,0)); //2 purple
    Vertex d = new Vertex(new Point3D(-1, 1, 1), new Col(1.,1,1), new Vec2D(0,0)); //3 white
    Vertex e = new Vertex(new Point3D(1, -1, -1), new Col(0.,0,0), new Vec2D(1,1)); //4 black
    Vertex f = new Vertex(new Point3D(1, -1, 1) , new Col(0.,1,0), new Vec2D(0,1)); //5 green
    Vertex g = new Vertex(new Point3D(1, 1, -1), new Col(0.,0,1), new Vec2D(1,0)); //6 blue
    Vertex h = new Vertex(new Point3D(1, 1, 1), new Col(0.,1,1), new Vec2D(0,0)); //7 cyan

    public Cube() {
        vertexBuffer.add(a); //0
        vertexBuffer.add(b); //1
        vertexBuffer.add(c); //2
        vertexBuffer.add(d); //3
        vertexBuffer.add(e); //4
        vertexBuffer.add(f); //5
        vertexBuffer.add(g); //6
        vertexBuffer.add(h); //7

        /*addIndices(0, 1, 3);
        addIndices(0, 2, 3);
        addIndices(1, 5, 6);
        addIndices(1, 6, 2);

        addIndices(5, 4, 7);
        addIndices(5, 7, 6);
        addIndices(4, 0, 3); 
        addIndices(4, 3, 7);

        addIndices(0, 1, 5);
        addIndices(4, 5, 3);
        addIndices(2, 6, 7);
        addIndices(2, 7, 3);*/

        /*addIndices(0, 2, 6);
        addIndices(0, 6, 4);
        addIndices(2, 1, 3);
        addIndices(2, 3, 6);

        addIndices(0, 2, 3);
        addIndices(0, 3, 1);
        addIndices(0, 4, 1); 
        addIndices(4, 5, 1);

        addIndices(3, 1, 5);
        addIndices(3, 5, 7);
        addIndices(4, 6, 7);
        addIndices(4, 7, 5);*/

        addIndices(  0,1,2);
        addIndices(   1,3,2);
        addIndices(4,6,5);
        addIndices(5,6,7);

        addIndices( 0,4,1);
        addIndices(1,4,5);
        addIndices(    2,3,6); 
        addIndices(  3,7,6);

        addIndices( 0,2,4);
        addIndices( 2,6,4);
        addIndices(  1,5,3);
        addIndices(   3,5,7);

        partBuffer.add(new Part(TopologyType.TRIANGLES, 0, 12));

    }
}

/*vertexBuffer.add(new Vertex(-1, -1, -1, new Col(0xffffff),new Vec3D(0,0,1),new Vec2D(0, 0))); // v0 a
        vertexBuffer.add(new Vertex(-1, 1, -1, new Col(0x0000ff),new Vec3D(0,0,1),new Vec2D(0, 1))); // v1 c
        vertexBuffer.add(new Vertex(1, 1, -1, new Col(0xff0000),new Vec3D(0,0,1),new Vec2D(1, 1))); // v2 g
        vertexBuffer.add(new Vertex(1, -1, -1, new Col(0x00ff00),new Vec3D(0,0,1),new Vec2D(1, 0))); // v3 e
        vertexBuffer.add(new Vertex(-1, -1, 1, new Col(0xffffff),new Vec3D(0,0,1),new Vec2D(0, 0))); // v0 b
        vertexBuffer.add(new Vertex(-1, 1, 1, new Col(0x0000ff),new Vec3D(0,0,1),new Vec2D(0, 1))); // v1 d
        vertexBuffer.add(new Vertex(1, 1, 1, new Col(0xff0000),new Vec3D(0,0,1),new Vec2D(1, 1))); // v2 h
        vertexBuffer.add(new Vertex(1, -1, 1, new Col(0x00ff00),new Vec3D(0,0,1),new Vec2D(1, 0))); // v3 f

        addIndices(0, 1, 2); // triangles a c g
        addIndices(0, 2, 3); // a g e
        addIndices(1,5,6); //c b d
        addIndices(1,6,2); //c d g
        addIndices(0,1,5); //a c d
        addIndices(0,5,4); //a d b
        addIndices(0,3,4); //a e b
        addIndices(3,7,4); //e f b
        addIndices(5,4,7); //d b f
        addIndices(5,7,6); //d f h
        addIndices(3,2,6); //e g h
        addIndices(3,6,7); //e h f

        partBuffer.add(new Part(TopologyType.TRIANGLES, 0, 12));*/