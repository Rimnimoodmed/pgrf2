package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import objectdata.Arrow;
import objectdata.Solid;
import objectdata.Vertex;
import raster.RasterBufferedImage;
import raster.ZBuffer;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerGraphics;
import rasterize.TriangleRasterizer;
import renderer.RendererSolid;
import shader.shader;
import shader.shaderConstant;
import transforms.*;
import view.Panel;


public class Controller3D {
    private final Panel panel;
    private final ZBuffer zBuffer;
    private LineRasterizer lineRasterizer;
    private TriangleRasterizer triangleRasterizer;
    private RendererSolid renderer;


    private BufferedImage grayConcrete;
    private shader concShader;

    private Solid Arrow;


    public Controller3D(Panel panel) {
        this.panel = panel;
        this.zBuffer = new ZBuffer(panel.getRaster());
        lineRasterizer = new LineRasterizerGraphics(panel.getRaster());
        triangleRasterizer = new TriangleRasterizer(zBuffer);
        
        try {
            grayConcrete = ImageIO.read(new File("res/textury/premium_photo-1742642385948-78abcce5656b.jpeg"));
        } catch (Exception e) {
            System.out.println("a");
        }
        Arrow = new Arrow();

        initListeners();

        drawScene();
    }

    private void initListeners() {
        // TODO: Inicializace listenerů např. pohyb kamerou
    }
    shader coShader = new shader() {
        @Override
        public Col shade(Vertex pixel){
            double x = pixel.getUv().getX();
            double y = pixel.getUv().getY();
            if (x>1) {
                x=1;
            }
            if (y>1) {
                y=1;
            }
            if (x<0) {
                x=0;
            }
            if (y<0) {
                y=0;
            }
            return new Col(grayConcrete.getRGB((int)(x*(grayConcrete.getWidth()-1)),(int)(y*(grayConcrete.getHeight()-1))));
        }
    };
    private void drawScene() {
        panel.getRaster().clear();

        zBuffer.setPixelWithZTest(50, 50, 0.1, new Col(0x00ff00));
        zBuffer.setPixelWithZTest(50, 50, 0.4, new Col(0xff00ff));
        
        Vertex a = new Vertex(new Point3D(400, 0, 1), new Col(1.,0,0), new Vec2D(1,0));
        Vertex b = new Vertex(new Point3D(0, 300, 1) , new Col(0.,1,0), new Vec2D(0,0));
        Vertex c = new Vertex(new Point3D(599, 599, 0), new Col(1.,0,0), new Vec2D(1,1));
        
        triangleRasterizer.rasterize(a, b, c, coShader);
        
        renderer.render(arrow);

        /*Vertex d = new Vertex(new Point3D(200, 0, 0), new Col(0.,1,0));
        Vertex e = new Vertex(new Point3D(0, 100, 0) , new Col(0.,1.,0));
        Vertex f = new Vertex(new Point3D(599, 500, 1), new Col(0.,1.,0));
        triangleRasterizer.rasterize(d, e, f, new shaderConstant());*/

        panel.repaint();
    }
}
