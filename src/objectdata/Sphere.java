package objectdata;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec2D;

public class Sphere extends Solid {
    int stacks = 20;
    int slices = 20;
    float radius = 1f;
    public Sphere() {

        for (int i = 0; i <= stacks; i++) {
            double phi = Math.PI * i / stacks;

            for (int j = 0; j <= slices; j++) {
                double theta = 2 * Math.PI * j / slices;

                float x = (float) (radius * Math.sin(phi) * Math.cos(theta));
                float y = (float) (radius * Math.cos(phi));
                float z = (float) (radius * Math.sin(phi) * Math.sin(theta));

                vertexBuffer.add(new Vertex(new Point3D(x, y, z), new Col(255, 255, 255), new Vec2D(1,1)));
            }
        }

        for (int i = 0; i < stacks; i++) {
            for (int j = 0; j < slices; j++) {

                int first = i * (slices + 1) + j;
                int second = first + slices + 1;

                addIndices(first, second, first + 1);
                addIndices(second, second + 1, first + 1);
                
            }
        }
        partBuffer.add(new Part(TopologyType.TRIANGLES, 0, indexBuffer.size()/3));
    }
}