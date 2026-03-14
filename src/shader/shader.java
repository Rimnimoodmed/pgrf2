package shader;

import objectdata.Vertex;
import transforms.Col;

public interface shader {
    Col shade(Vertex pixel);

}
