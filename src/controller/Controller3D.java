package controller;

import java.awt.Color;

import objectdata.Vertex;
import raster.ZBuffer;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerGraphics;
import rasterize.TriangleRasterizer;
import transforms.*;
import view.Panel;


public class Controller3D {
    private final Panel panel;
    private final ZBuffer zBuffer;
    private LineRasterizer lineRasterizer;
    private TriangleRasterizer triangleRasterizer;


    public Controller3D(Panel panel) {
        this.panel = panel;
        this.zBuffer = new ZBuffer(panel.getRaster());
        lineRasterizer = new LineRasterizerGraphics(panel.getRaster());
        triangleRasterizer = new TriangleRasterizer(zBuffer);

        initListeners();

        drawScene();
    }

    private void initListeners() {
        // TODO: Inicializace listenerů např. pohyb kamerou
    }

    private void drawScene() {
        panel.getRaster().clear();

        zBuffer.setPixelWithZTest(50, 50, 0.1, new Col(0x00ff00));
        zBuffer.setPixelWithZTest(50, 50, 0.4, new Col(0xff00ff));
        
        Vertex a = new Vertex(new Point3D(400, 0, 1), new Col(1.,0,0));
        Vertex b = new Vertex(new Point3D(0, 300, 1) , new Col(1.,0,0));
        Vertex c = new Vertex(new Point3D(599, 599, 0), new Col(1.,0,0));
        triangleRasterizer.rasterize(a, b, c);
        Vertex d = new Vertex(new Point3D(200, 0, 0), new Col(0.,1,0));
        Vertex e = new Vertex(new Point3D(0, 100, 0) , new Col(0.,1.,0));
        Vertex f = new Vertex(new Point3D(599, 500, 1), new Col(0.,1.,0));
        triangleRasterizer.rasterize(d, e, f);

        panel.repaint();
    }
}
