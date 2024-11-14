package ru.vsu.cs.task3.Renderer;

import ru.vsu.cs.task3.Math.Matrix.Mat3f;
import ru.vsu.cs.task3.Math.Matrix.Mat4f;
import ru.vsu.cs.task3.Math.Vectors.Vector3F;

public class Camera {
    private Vector3F pos;
    private Mat3f orientationMatrix; //TODO(): make matrix3f
    public Camera(Vector3F eye, Vector3F target, Vector3F up) {
        Vector3F z = Vector3F.sub(eye, target);
        Vector3F x = Vector3F.cross(up, z);
        orientationMatrix = new Mat3f(x, Vector3F.cross(z, x), z);
        pos = eye;

    }
    public Vector3F getPos() {
        return pos;
    }
    public void setPos(Vector3F pos)
    {
        this.pos = pos;
    }
    public void translate(Vector3F translationVector)
    {
        pos.add(translationVector);
    }
    //TODO(): rotate camera
}
