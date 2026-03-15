package rasterize;

import objectdata.Vertex;
import raster.ZBuffer;
import shader.Shader;
import util.Lerp;

public class LineRasterizerPrimitive extends LineRasterizer{
    public LineRasterizerPrimitive(ZBuffer zBuffer) {
        super(zBuffer);
        //TODO Auto-generated constructor stub
    }

    public void rasterize(Vertex a, Vertex b, Shader shader){
        Vertex temp;
        if (a.getY() > b.getY()) {
            temp = a;
            a = b;
            b = temp;
        }

        Lerp<Vertex> lerp = new Lerp<>();

        int aY = (int)Math.round(a.getY());
        int bY = (int)Math.round(b.getY());

        for(int y = aY; y <=bY; y++){
            double tAB = (double)(y-aY)/(bY-aY);
            Vertex pixel = lerp.lerp(a, b, tAB);
            zBuffer.setPixelWithZTest((int)pixel.getX(), y, pixel.getZ(), shader.shade(pixel));
            
        }
    }
}
