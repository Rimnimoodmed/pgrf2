package shader;

import objectdata.Vertex;
import transforms.Col;

public class shaderConstant implements shader{

    @Override
    public Col shade(Vertex pixel) {
        return pixel.getColor();
    }

    
}