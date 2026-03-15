package rasterize;

import objectdata.Vertex;
import raster.ZBuffer;
import shader.Shader;
import util.Lerp;

public class TriangleRasterizer {

    private ZBuffer img;

    public TriangleRasterizer(ZBuffer zbuffer) {
        this.img = zbuffer;
    }

    public void rasterize(Vertex a, Vertex b, Vertex c, Shader shader){
        Vertex temp;
        if (a.getY() > b.getY()) {
            temp = a;
            a = b;
            b = temp;
        }
        if(a.getY() > c.getY()) {
            temp = a;
            a = c;
            c = temp;
        }
        if(b.getY() > c.getY()) {
            temp = c;
            c = b;
            b = temp;
        }

        Lerp<Vertex> lerp = new Lerp<>();

        int aY = (int)Math.round(a.getY());
        int bY = (int)Math.round(b.getY());
        int cY = (int)Math.round(c.getY());

        for(int y = aY; y <=bY; y++){
            double tAB = (double)(y-aY)/(bY-aY);
            Vertex ab = lerp.lerp(a, b, tAB);

            double tAC = (double) (y - aY) / (cY - aY);
            Vertex ac = lerp.lerp(a, c, tAC);

            if (ab.getX() > ac.getX()) {
                Vertex tempx = ab;
                ab = ac;
                ac = tempx;
            }

            //orezani
            //if a.getz()<zMin(0)

            for (int x = (int) Math.round(ab.getX()); x <= (int) Math.round(ac.getX()); x++) {
                double t = (x - ab.getX()) / (ac.getX() - ab.getX());
                Vertex pixel = lerp.lerp(ab, ac, t);
                img.setPixelWithZTest(x, y, pixel.getZ(), shader.shade(pixel));
            }
        }
       for(int y = bY; y <=cY; y++){
            double tBC = (double)(y-bY)/(cY-bY);
            Vertex bc = lerp.lerp(b, c, tBC);

            double tAC = (double) (y - aY) / (cY - aY);
            Vertex ac = lerp.lerp(a, c, tAC);

            if (bc.getX() > ac.getX()) {
                Vertex tempx = bc;
                bc = ac;
                ac = tempx;
            }

            for (int x = (int) Math.round(bc.getX()); x <= (int) Math.round(ac.getX()); x++) {
                double t = (x - bc.getX()) / (ac.getX() - bc.getX());
                Vertex pixel = lerp.lerp(bc, ac, t);
                img.setPixelWithZTest(x, y, pixel.getZ(), shader.shade(pixel));

            }
        }

        
        //todo overit jestli xab neni versi nez xac
    }
}
