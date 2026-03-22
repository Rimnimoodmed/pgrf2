package shader;

import objectdata.Vertex;
import transforms.Col;

public class ShaderConstant implements Shader{
    Col color;
    
    @Override
    public Col shade(Vertex pixel) {
        return color;
    }

    public ShaderConstant(Col color) {
        this.color = color;
    }
}