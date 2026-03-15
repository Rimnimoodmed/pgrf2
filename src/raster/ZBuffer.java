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
        if ((depthBuffer.getValue(x, y).orElse(1.0))>z) {
            depthBuffer.setValue(x, y, z);
            colorBuffer.setValue(x, y, color);
            
        }
    }
}
