package shader;

import objectdata.Vertex;
import transforms.Col;

public class ShaderConstant implements Shader{

    @Override
    public Col shade(Vertex pixel) {
        return pixel.getColor();
    }

    
}