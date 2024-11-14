package ru.vsu.cs.task3.Renderer;

import ru.vsu.cs.task3.Math.Matrix.Mat3f;
import ru.vsu.cs.task3.Math.Matrix.Mat4f;
import ru.vsu.cs.task3.Math.Vectors.Vector3F;

public class GraphicsConveyor {
    public static Mat4f scaleRotateTranslate(Vector3F scale, Vector3F rotation, Vector3F translate)
    {
        Mat3f result = new Mat3f(scale.x, 0.0f, 0.0f, 0.0f, scale.y, 0.0f, 0.0f, 0.0f, scale.z);
        Mat3f rotationMatrix = new Mat3f(
                cos(rotation.x)*cos(rotation.y), sin(rotation.x)*cos(rotation.y), -sin(rotation.y),
                cos(rotation.x)*sin(rotation.y)*sin(rotation.z)-sin(rotation.x)*cos(rotation.z), sin(rotation.x)*sin(rotation.y)*sin(rotation.z)+cos(rotation.x)*sin(rotation.z), cos(rotation.y)*sin(rotation.z),
                cos(rotation.x)*sin(rotation.y)*cos(rotation.z) + sin(rotation.x)*sin(rotation.z), sin(rotation.x)*sin(rotation.y)*cos(rotation.z)-cos(rotation.x)*sin(rotation.z), cos(rotation.y)*cos(rotation.z)
                );

        return new Mat4f(Mat3f.multiply(new Mat3f(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, translate.x, translate.y, translate.z), Mat3f.multiply(rotationMatrix, result)));
    }
    public static Mat4f lookAt(Vector3F eye, Vector3F target) throws Exception {
        return lookAt(eye, target, new Vector3F(0F, 1.0F, 0F));
    }
    public static Mat4f lookAt(Vector3F eye, Vector3F target, Vector3F up) throws Exception {
        Mat4f translation = new Mat4f(
                1, 0, 0, -eye.x,
                0, 1, 0, -eye.y,
                0, 0, 1, -eye.z,
                0, 0, 0, 1
        );
        Vector3F z = Vector3F.sub(eye, target);
        Vector3F x = Vector3F.cross(up, z);
        Vector3F y = Vector3F.cross(z, x);
        x.normalize();
        y.normalize();
        z.normalize();
        return new Mat4f(
                x.x, y.x, z.x, 0,
                x.y, y.y, z.y, 0,
                x.z, y.z, z.z, 0,
                -Vector3F.dot(eye, x), -Vector3F.dot(eye, y), -Vector3F.dot(eye, z), 1
        );
    }
    public static Mat4f perspective(
            final float fov,
            final float aspectRatio,
            final float nearPlane,
            final float farPlane) {
        float tgMinusOneDegree = (float) (1.0f / Math.tan(Math.toRadians(fov)));
        return new Mat4f(
                tgMinusOneDegree, 0, 0, 0,
                0, tgMinusOneDegree / aspectRatio, 0, 0,
                0, 0, (farPlane + nearPlane) / (farPlane - nearPlane), 1,
                0, 0, 2 * farPlane * nearPlane / (nearPlane - farPlane), 0
        );
    }
    private static float cos(float angle)
    {
        return (float)Math.cos(Math.toRadians(angle));
    }
    private static float sin(float angle)
    {
        return (float)Math.sin(Math.toRadians(angle));
    }
}
