package raster;

import java.util.Arrays;
import java.util.Optional;

import transforms.Col;

public class DepthBuffer implements Raster<Double>{

    private final double [][] buffer;
    private final int width;
    private final int height;

    public DepthBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.buffer = new double[width][height];
        for (double[] ds : buffer) {
            Arrays.fill(ds, 10000.0);
        }
    }

    @Override
    public void clear() {
        for (double[] ds : buffer) {
            Arrays.fill(ds, 10000.0);
        }
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return height;
    }

    @Override
    public Optional<Double> getValue(int x, int y) {
        // TODO Auto-generated method stub
        return Optional.of(new Double(buffer[x][y]));
    }

    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return width;
    }

    @Override
    public void setValue(int x, int y, Double value) {
        // TODO Auto-generated method stub
        if (getValue(x, y).orElse(1.0)>value) {
            buffer[x][y] = value;
        }
    }
}
