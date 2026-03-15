package renderer;

import model.Scene;
import objectdata.Part;
import objectdata.Solid;
import objectdata.Vertex;
import rasterize.LineRasterizer;
import rasterize.TriangleRasterizer;
import shader.Shader;
import transforms.Mat4;
import util.Lerp;

public class RendererSolid {
    private LineRasterizer lineRasterizer;
    private TriangleRasterizer triangleRasterizer;

    public RendererSolid(LineRasterizer lineRasterizer, TriangleRasterizer triangleRasterizer) {
        this.lineRasterizer = lineRasterizer;
        this.triangleRasterizer = triangleRasterizer;
    }
    
    public void render(Solid solid, Scene scene) {
        Mat4 mvp = solid.getModel().mul(scene.getView().getViewMatrix()).mul(scene.getProjection());
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

                        // TODO: transformace MVP

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

                        if (a.getZ() < zMin)
                            continue;

                        if (b.getZ() < zMin) {
                            double tAB = (a.getZ() - zMin)/(a.getZ()-b.getZ());
                            Vertex vAB = lerp.lerp(a,b,tAB);
                            double tAC = (a.getZ() - zMin)/(a.getZ()-c.getZ());
                            Vertex vAC = lerp.lerp(a,b,tAC);
                            renderTriangle(a, vAB,vAC, solid.getShader());
                            continue;
                        }

                        // TODO: dehomogenizace

                        // TODO: transformace do okna
                        if (c.getZ() < zMin) {
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
        // TODO: dehomogenizace

        // TODO: transformace do okna

        // Rasterizace
        triangleRasterizer.rasterize(a, b, c, shader);
    }

}
