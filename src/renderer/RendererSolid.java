package renderer;

import model.Scene;
import objectdata.Part;
import objectdata.Solid;
import objectdata.Vertex;
import rasterize.LineRasterizer;
import rasterize.TriangleRasterizer;
import shader.Shader;
import shader.ShaderConstant;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;
import util.Lerp;
import view.Panel;

public class RendererSolid {
    private LineRasterizer lineRasterizer;
    private TriangleRasterizer triangleRasterizer;
    private view.Panel panel;

    public RendererSolid(LineRasterizer lineRasterizer, TriangleRasterizer triangleRasterizer, Panel panel) {
        this.lineRasterizer = lineRasterizer;
        this.triangleRasterizer = triangleRasterizer;
        this.panel = panel;
    }
    
    public void render(Solid solid, Scene scene) {
        Mat4 mvp = solid.getModel().mul(scene.getView().getViewMatrix()).mul(scene.getProjectionPersp());
        for (Part part : solid.getPartBuffer()) {
            switch (part.getType()) {
                case LINES:
                    int index = part.getStartIndex();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexA = solid.getIndexBuffer().get(index++);
                        int indexB = solid.getIndexBuffer().get(index++);

                        Vertex a = solid.getVertexBuffer().get(indexA);
                        Vertex b = solid.getVertexBuffer().get(indexB);

                        // TODO: transformace MVP

                        // TODO: ořezání

                        // TODO: dehomogenizace

                        // TODO: transformace do okna

                        lineRasterizer.rasterize(
                                (int) Math.round(a.getX()),
                                (int) Math.round(a.getY()),
                                (int) Math.round(b.getX()),
                                (int) Math.round(b.getY())
                        );
                    }
                break;
                case TRIANGLES:
                    
                    index = part.getStartIndex();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexA = solid.getIndexBuffer().get(index++);
                        int indexB = solid.getIndexBuffer().get(index++);
                        int indexC = solid.getIndexBuffer().get(index++);
                        
                        Vertex a = solid.getVertexBuffer().get(indexA).mul(mvp);
                        Vertex b = solid.getVertexBuffer().get(indexB).mul(mvp);
                        Vertex c = solid.getVertexBuffer().get(indexC).mul(mvp);
                            double zMin = 0;

                        Vertex temp;
                        if (a.getZ() < b.getZ()) {
                            temp = a;
                            a = b;
                            b = temp;
                        }
                        if(a.getZ() < c.getZ()) {
                            temp = a;
                            a = c;
                            c = temp;
                        }
                        if(b.getZ() < c.getZ()) {
                            temp = c;
                            c = b;
                            b = temp;
                        }

                        Lerp<Vertex> lerp = new Lerp<>();

                        if (a.getZ() < zMin){
                            System.out.println("a out of bounds");
                            continue;
                        }
                        else if (b.getZ() < zMin) {
                            System.out.println("b out of bounds");
                            double tAB = (a.getZ() - zMin)/(a.getZ()-b.getZ());
                            Vertex vAB = lerp.lerp(a,b,tAB);
                            double tAC = (a.getZ() - zMin)/(a.getZ()-c.getZ());
                            Vertex vAC = lerp.lerp(a,b,tAC);
                            renderTriangle(a, vAB,vAC, solid.getShader());
                            continue;
                        }

                        else if (c.getZ() < zMin) {
                            System.out.println("c out of bounds");
                            double tBC = (b.getZ() - zMin)/(b.getZ()-c.getZ());
                            Vertex vBC = lerp.lerp(b,c,tBC);
                            renderTriangle(a, b, vBC,solid.getShader());
                            double tAC = (a.getZ() - zMin)/(a.getZ()-c.getZ());
                            Vertex vAC = lerp.lerp(a,b,tAC);
                            renderTriangle(a, vBC,vAC, solid.getShader());
                            continue;
                        }


                        
                        // Rasterizace
                        renderTriangle(a, b, c, solid.getShader());
                    }

                break;
                    
            }
        }
    }
    public void renderTriangle(Vertex a, Vertex b, Vertex c, Shader shader) {
        if (inView(a)||inView(b)||inView(c)) {
        a = a.dehomog(a);
        b = b.dehomog(b);
        c = c.dehomog(c);

        transformujDoOkna(a);
        transformujDoOkna(b);
        transformujDoOkna(c);

        // Rasterizace
        triangleRasterizer.rasterize(a, b, c, shader);
        }
    }
    public Vertex transformujDoOkna(Vertex v){
        v.setPosition(new Point3D(new Vec3D(v.getPosition()).mul(new Vec3D(1,-1,1))));
        v.setPosition(new Point3D(new Vec3D(v.getPosition()).add(new Vec3D(1,1,0))));
        v.setPosition(new Point3D(new Vec3D(v.getPosition()).mul(new Vec3D((double) (panel.getWidth() - 1) /2, (double) (panel.getHeight() - 1) /2,1))));
        return v;
    }
    public boolean inView(Vertex v){
        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();
        double w = v.getW();

        return (-w<=x&&x<=w&&-w<=y&&y<=w&&0<=z&&z<=w);
    }

}
