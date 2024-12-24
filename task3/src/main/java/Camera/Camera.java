package Camera;

import Matrixes.Matrix4f;
import Vectors.Vector3f;
import Render.GraphicsConveyor;

public class Camera {
    private Vector3f position;
    private Vector3f target;
    private float fov;
    private float aspectRatio;
    private float nearPlane;
    private float farPlane;
    public Camera(
            final Vector3f position,
            final Vector3f target,
            final float fov,
            final float aspectRatio,
            final float nearPlane,
            final float farPlane) {
        this.position = position;
        this.target = target;
        this.fov = (float)Math.toRadians(fov);
        this.aspectRatio = aspectRatio;
        this.nearPlane = nearPlane;
        this.farPlane = farPlane;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void moveUp(float value) {
        position.addToMe(new Vector3f(0.0f, value, 0.0f));
    }
    public void moveRight(float value) {
        position.addToMe(new Vector3f(value, 0.0f, 0.0f));
    }

    public void rotateY(float value) {
        value %= 360;
        float radians = (float) Math.toRadians(value);
        float newX = position.getX() * (float) Math.cos(radians) + position.getZ() * (float) Math.sin(radians);
        float newZ = -position.getX() * (float) Math.sin(radians) + position.getZ() * (float) Math.cos(radians);
        position = new Vector3f(newX, position.getY(), newZ);
    }
    public void rotateX(float value) {
        value %= 360;
        float radians = (float) Math.toRadians(value);
        float newY = position.getY() * (float) Math.cos(radians) + position.getZ() * (float) Math.sin(radians);
        float newZ = -position.getY() * (float) Math.sin(radians) + position.getZ() * (float) Math.cos(radians);
        position = new Vector3f(position.getX(), newY, newZ);
    }

    public void zoom(float value) {
        position = position.add(new Vector3f(0, 0, 1*Math.signum(value)));
    }

    public Vector3f getTarget() {
        return target;
    }

    public void setTarget(Vector3f target) {
        this.target = target;
    }

    public float getFov() {
        return fov;
    }

    public void setFov(float fov) {
        this.fov = fov;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public float getNearPlane() {
        return nearPlane;
    }

    public void setNearPlane(float nearPlane) {
        this.nearPlane = nearPlane;
    }

    public float getFarPlane() {
        return farPlane;
    }

    public void setFarPlane(float farPlane) {
        this.farPlane = farPlane;
    }
    public Matrix4f getViewMatrix() {
        return GraphicsConveyor.lookAt(position, target);
    }

    public Matrix4f getProjectionMatrix() {
        return GraphicsConveyor.perspective(fov, aspectRatio, nearPlane, farPlane);
    }
}
