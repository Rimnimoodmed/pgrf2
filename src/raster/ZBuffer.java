package raster;

import transforms.Col;

public class ZBuffer {

    private final Raster<Col> colorBuffer;
    private final Raster<Double> depthBuffer;

    
    public ZBuffer(Raster<Col> colorBuffer) {
        this.colorBuffer = colorBuffer;
        this.depthBuffer = new DepthBuffer(colorBuffer.getHeight(),colorBuffer.getWidth());
    }


    public void setPixelWithZTest(int x, int y, double z, Col color){
        if (x<0 || y<0 || z<0 || x>colorBuffer.getWidth() || y>colorBuffer.getHeight()) {
            System.out.println(x+"x");
            System.out.println(y+"y");
            System.out.println(z+"z");
            return;
        }
        else if ((depthBuffer.getValue(x, y).orElse(1.0))>z) {
            depthBuffer.setValue(x, y, z);
            colorBuffer.setValue(x, y, color);
            
        }
    }

    public void clear(){
        depthBuffer.clear();
        colorBuffer.clear();
    }
}
