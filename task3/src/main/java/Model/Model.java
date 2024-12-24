package Model;

import Vectors.Vector2f;
import Vectors.Vector3f;
import ru.vsu.cs.affineTransormation.Rotate;
import ru.vsu.cs.affineTransormation.Scale;
import ru.vsu.cs.affineTransormation.Translation;

import java.util.ArrayList;

public class Model {
    public ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<Vector2f>();
    public ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
    public ArrayList<Polygon> polygons = new ArrayList<Polygon>();
    private Translation translation;
    private Rotate rotation;
    private Scale scale;
    public Model() {
        translation = new Translation();
        rotation = new Rotate();
        scale = new Scale();
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    public Rotate getRotation() {
        return rotation;
    }

    public void setRotation(Rotate rotation) {
        this.rotation = rotation;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }
}
