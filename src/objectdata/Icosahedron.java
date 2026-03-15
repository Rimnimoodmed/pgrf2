package objectdata;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;

public class Icosahedron extends Solid{
    public Icosahedron(){
        double ratio = (1+Math.sqrt(5))/2;
        Vertex A = new Vertex(new Point3D(0,1.,ratio), new Col(1.,0,1), new Vec2D(1,1));
        Vertex B = new Vertex(new Point3D(0,1.,-ratio), new Col(1.,0,1), new Vec2D(1,1));
        Vertex C = new Vertex(new Point3D(0,-1.,ratio), new Col(1.,0,1), new Vec2D(1,1));
        Vertex D = new Vertex(new Point3D(0,-1.,-ratio), new Col(1.,0,1), new Vec2D(1,1));
        Vertex E = new Vertex(new Point3D(ratio,0,1), new Col(1.,0,1), new Vec2D(1,1));
        Vertex F = new Vertex(new Point3D(-ratio,0,1), new Col(1.,0,1), new Vec2D(1,1));
        Vertex G = new Vertex(new Point3D(ratio,0,-1), new Col(1.,0,1), new Vec2D(1,1));
        Vertex H = new Vertex(new Point3D(-ratio,0,-1), new Col(1.,0,1), new Vec2D(1,1));
        Vertex I = new Vertex(new Point3D(1.,ratio,0), new Col(1.,0,1), new Vec2D(1,1));
        Vertex J = new Vertex(new Point3D(-1.,ratio,0), new Col(1.,0,1), new Vec2D(1,1));
        Vertex K = new Vertex(new Point3D(-1.,-ratio,0), new Col(1.,0,1), new Vec2D(1,1));
        Vertex L = new Vertex(new Point3D(1.,-ratio,0), new Col(1.,0,1), new Vec2D(1,1));

        vertexBuffer.add(A); 
        vertexBuffer.add(B);
        vertexBuffer.add(C);
        vertexBuffer.add(D);
        vertexBuffer.add(E);
        vertexBuffer.add(F);
        vertexBuffer.add(G);
        vertexBuffer.add(H);
        vertexBuffer.add(I);
        vertexBuffer.add(J);
        vertexBuffer.add(K);
        vertexBuffer.add(L);

        indexBuffer.add(0); indexBuffer.add(2);
        indexBuffer.add(2); indexBuffer.add(5);
        indexBuffer.add(5); indexBuffer.add(0);
        indexBuffer.add(2); indexBuffer.add(4);
        indexBuffer.add(4); indexBuffer.add(0);
        indexBuffer.add(0); indexBuffer.add(8);
        indexBuffer.add(8); indexBuffer.add(9);
        indexBuffer.add(9); indexBuffer.add(0);
        indexBuffer.add(2); indexBuffer.add(10);
        indexBuffer.add(10); indexBuffer.add(11);
        indexBuffer.add(11); indexBuffer.add(2);
        indexBuffer.add(5); indexBuffer.add(9);
        indexBuffer.add(5); indexBuffer.add(10);
        indexBuffer.add(4); indexBuffer.add(11);
        indexBuffer.add(4); indexBuffer.add(8);
        indexBuffer.add(5); indexBuffer.add(7);
        indexBuffer.add(9); indexBuffer.add(7);
        indexBuffer.add(10); indexBuffer.add(7);
        indexBuffer.add(4); indexBuffer.add(6);
        indexBuffer.add(8); indexBuffer.add(6);
        indexBuffer.add(11); indexBuffer.add(6);
        indexBuffer.add(6); indexBuffer.add(1);
        indexBuffer.add(7); indexBuffer.add(1);
        indexBuffer.add(8); indexBuffer.add(1);
        indexBuffer.add(9); indexBuffer.add(1);
        indexBuffer.add(6); indexBuffer.add(3);
        indexBuffer.add(7); indexBuffer.add(3);
        indexBuffer.add(10); indexBuffer.add(3);
        indexBuffer.add(11); indexBuffer.add(3);
        indexBuffer.add(1); indexBuffer.add(3);
    }
}
