package Render;

import Matrixes.Matrix4f;
import Vectors.Vector3f;

public class GraphicsConveyor {
    public static Matrix4f lookAt(Vector3f eye, Vector3f target) {
        return lookAt(eye, target, new Vector3f(0F, 1.0F, 0F));
    }

    public static Matrix4f lookAt(Vector3f eye, Vector3f target, Vector3f up) {
        Vector3f resultZ = eye.subtract(target);
        resultZ.normalize();

        Vector3f resultX = up.vectorProduct(resultZ);
        resultX.normalize();

        Vector3f resultY = resultZ.vectorProduct(resultX);
        resultY.normalize();

        return new Matrix4f(
                resultX.getX(), resultX.getY(), resultX.getZ(), -resultX.scalarMultiplication(eye),
                resultY.getX(), resultY.getY(), resultY.getZ(), -resultY.scalarMultiplication(eye),
                resultZ.getX(), resultZ.getY(), resultZ.getZ(), -resultZ.scalarMultiplication(eye),
                0.0f, 0.0f, 0.0f, 1.0f
        );
    }

    public static Matrix4f perspective(
            final float fov,
            final float aspectRatio,
            final float nearPlane,
            final float farPlane) {
        Matrix4f result = new Matrix4f(
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        float tangentMinusOnDegree = (float) (1.0F / (Math.tan(fov)));
        result.changeElement(1, 1, tangentMinusOnDegree/aspectRatio);
        result.changeElement(2, 2, tangentMinusOnDegree);
        result.changeElement(3, 3, -(farPlane + nearPlane) / (farPlane - nearPlane));
        result.changeElement(4, 3, -1.0F);
        result.changeElement(3, 4, -2 * (nearPlane * farPlane) / (farPlane - nearPlane));
        result.changeElement(4, 4, 0.0F);
        return result;
    }

    public static Vector3f vertexToPoint(final Vector3f vertex, final int width, final int height) {
        return new Vector3f(((float) width - 1) / 2 * vertex.getX() + ((float) width - 1) / 2, (1 - (float) height) / 2 * vertex.getY() + ((float) height - 1) / 2, vertex.getZ());
    }

}
