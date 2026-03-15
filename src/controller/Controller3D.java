package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.Scene;
import objectdata.Arrow;
import objectdata.Cube;
import objectdata.Icosahedron;
import objectdata.Solid;
import objectdata.Vertex;
import raster.ZBuffer;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerGraphics;
import rasterize.TriangleRasterizer;
import renderer.RendererSolid;
import shader.Shader;
import shader.ShaderConstant;
import shader.shaderInterpolated;
import transforms.*;
import view.Panel;


public class Controller3D {
    private final Panel panel;
    private final ZBuffer zBuffer;
    private LineRasterizer lineRasterizer;
    private TriangleRasterizer triangleRasterizer;
    private RendererSolid renderer;
    private Scene scene;

    private BufferedImage grayConcrete;
    private Shader concShader;

    private Solid selected;
    private Solid Arrow;
    private Solid Quad;
    private Solid cube;
    private double pohyb = 0.1;

    private ArrayList<Solid> solids = new ArrayList<>();
    private int solidPointer = 0;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.zBuffer = new ZBuffer(panel.getRaster());
        lineRasterizer = new LineRasterizerGraphics(panel.getRaster());
        triangleRasterizer = new TriangleRasterizer(zBuffer);
        renderer = new RendererSolid(lineRasterizer, triangleRasterizer, panel);
        scene = new Scene(panel);
        
        try {
            grayConcrete = ImageIO.read(new File("res/textury/premium_photo-1742642385948-78abcce5656b.jpeg"));
        } catch (Exception e) {
            System.out.println("a");
        }
        Arrow = new Arrow();
        
       
        initObjects();
        initListenery(panel);

        drawScene();
    }

        public void initListenery(Panel panel){
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                /*if (e.getKeyCode() == KeyEvent.VK_R) {
                    if (ortho) {
                        ortho=false;
                        System.out.println("neorthuju");
                        projection = projectionOrtho;
                    }
                    else{
                        ortho= true;
                        System.out.println("orthuju");
                        projection = projectionPersp;
                    }
                }*/
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    scene.setView(scene.getView().forward(pohyb));
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    scene.setView(scene.getView().left(pohyb));
                }
                if (e.getKeyCode() == KeyEvent.VK_S) { 
                    scene.setView(scene.getView().backward(pohyb));
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    scene.setView(scene.getView().right(pohyb));
                }
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    scene.setView(scene.getView().addAzimuth(pohyb));
                }
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    scene.setView(scene.getView().addAzimuth(-pohyb));
                }
                if (e.getKeyCode() == KeyEvent.VK_I) {
                    selected.setModel(selected.getModel().mul(new Mat4Transl(0.1,0,0)));
                }
                if (e.getKeyCode() == KeyEvent.VK_J) {
                    selected.setModel(selected.getModel().mul(new Mat4Transl(0,0.1,0)));
                }
                if (e.getKeyCode() == KeyEvent.VK_K) {
                    selected.setModel(selected.getModel().mul(new Mat4Transl(-0.1,0,0)));
                }
                if (e.getKeyCode() == KeyEvent.VK_L) {
                    selected.setModel(selected.getModel().mul(new Mat4Transl(0,-0.1,0)));
                }
                if (e.getKeyCode() == KeyEvent.VK_U) {
                    selected.setModel(selected.getModel().mul(new Mat4Transl(0,0,0.1)));
                }
                if (e.getKeyCode() == KeyEvent.VK_O) {
                    selected.setModel(selected.getModel().mul(new Mat4Transl(0,0,-0.1)));
                }
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    selected.setModel(selected.getModel().mul(new Mat4RotY(0.3)));
                }
                if (e.getKeyCode() == KeyEvent.VK_Y) {
                    selected.setModel(new Mat4Scale(1.1).mul(selected.getModel()));
                }
                if (e.getKeyCode() == KeyEvent.VK_H) {
                    selected.setModel(new Mat4Scale(0.9).mul(selected.getModel()));
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    scene.setView(scene.getView().up(pohyb));
                }
                if (e.getKeyCode() == 17) {
                    scene.setView(scene.getView().down(pohyb));
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    solidPointer--;
                    for (Solid solid : solids) {
                        solid.setSelected(false);
                    }
                    if (solidPointer < 0) {
                        solidPointer = solids.size()-1;
                    }
                    selected = solids.get(solidPointer);
                    selected.setSelected(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    solidPointer++;
                    for (Solid solid : solids) {
                        solid.setSelected(false);
                    }
                    
                    if (solidPointer > solids.size()-1) {
                        solidPointer = 0;
                    }
                    selected = solids.get(solidPointer);
                    selected.setSelected(true);
                }
                /*if (e.getKeyCode() == KeyEvent.VK_G) {
                    if (selected.isVisible()) {
                        selected.setVisible(false);
                    }
                    else{
                        selected.setVisible(true);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    selected.setSelected(false);
                    selected.setVisible(false);
                    meshes.remove(meshPointer);
                }*/
                drawScene();
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                double x = ((double)e.getX()-(panel.getWidth()/2))/(panel.getWidth()/2)*Math.PI;
                double y = ((double)e.getY()-(panel.getHeight()/2))/(panel.getHeight()/2)*Math.PI;
                scene.setView(scene.getView().withAzimuth((double)x));
                scene.setView(scene.getView().withZenith((double)y));
                drawScene();
                System.out.println(scene.getView().getAzimuth());
            }
        });
    }
    public void initObjects() {
        cube = new Cube();
        cube.setModel(cube.getModel().mul(new Mat4Transl(5,0,0)));
        solids.add(cube);
        Quad = new objectdata.Quad();
        //Quad.setModel(Quad.getModel().mul(new Mat4RotZ(Math.PI/2)));
        Quad.setModel(Quad.getModel().mul(new Mat4Transl(5,1,0)));
        //Quad.setModel(Quad.getModel().mul(new Mat4Scale(10)));
        Quad.setShader(new ShaderConstant());
        solids.add(Quad);
        selected = solids.get(solidPointer);
    }
    Shader coShader = new Shader() {
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
        zBuffer.clear();
        panel.getRaster().clear();

        /*zBuffer.setPixelWithZTest(50, 50, 0.1, new Col(0x00ff00));
        zBuffer.setPixelWithZTest(50, 50, 0.4, new Col(0xff00ff));*/
        
        /*Vertex a = new Vertex(new Point3D(1, 0, 0), new Col(1.,0,0), new Vec2D(1,0));
        Vertex b = new Vertex(new Point3D(0, 1, 1) , new Col(0.,1,0), new Vec2D(0,0));
        Vertex c = new Vertex(new Point3D(1, 1, 1), new Col(1.,0,0), new Vec2D(1,1));
        
        triangleRasterizer.rasterize(a, b, c, new ShaderConstant());*/
        
        //renderer.render(Arrow, scene);
        renderer.render(Quad, scene);
        renderer.render(cube, scene);
        //renderer.render(Icosahedron, scene);

        /*Vertex d = new Vertex(new Point3D(200, 0, 0), new Col(0.,1,0));
        Vertex e = new Vertex(new Point3D(0, 100, 0) , new Col(0.,1.,0));
        Vertex f = new Vertex(new Point3D(599, 500, 1), new Col(0.,1.,0));
        triangleRasterizer.rasterize(d, e, f, new shaderConstant());*/

        panel.repaint();
        

    }
}
