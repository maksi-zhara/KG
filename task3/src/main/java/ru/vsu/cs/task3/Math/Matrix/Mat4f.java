package ru.vsu.cs.task3.Math.Matrix;

import ru.vsu.cs.task3.Math.Vectors.Vector3F;

import java.nio.FloatBuffer;

public class Mat4f {
    public float m00, m10, m20, m30,
            m01, m11, m21, m31,
            m02, m12, m22, m32,
            m03, m13, m23, m33;

    public Mat4f() {
        loadIdentity();
    }
    public Mat4f(Vector3F x, Vector3F y, Vector3F z)
    {
        m00 = x.x; m10 = y.x; m20 = z.x; m30 = 0.0f;
        m01 = x.y; m11 = y.y; m21 = z.y; m31 = 0.0f;
        m02 = x.z; m12 = y.z; m22 = z.z; m32 = 0.0f;
        m03 = 0.0f; m13 = 0.0f; m23 = 0.0f; m33 = 1.0f;
    }
    public Mat4f(float m00, float m10, float m20, float m30,
                 float m01, float m11, float m21, float m31,
                 float m02, float m12, float m22, float m32,
                 float m03, float m13, float m23, float m33)
    {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;

        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;

        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;

        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }
    public Mat4f(Mat3f mat)
    {
        m00 = mat.m00;
        m01 = mat.m01;
        m02 = mat.m02;
        m03 = 0;

        m10 = mat.m00;
        m11 = mat.m01;
        m12 = mat.m02;
        m13 = 0;

        m20 = mat.m00;
        m21 = mat.m01;
        m22 = mat.m02;
        m23 = 0;

        m30 = 0;
        m31 = 0;
        m32 = 0;
        m33 = 1;

    }
    public Mat4f loadIdentity() {
        m00 = 1.0f; m10 = 0.0f; m20 = 0.0f; m30 = 0.0f;
        m01 = 0.0f; m11 = 1.0f; m21 = 0.0f; m31 = 0.0f;
        m02 = 0.0f; m12 = 0.0f; m22 = 1.0f; m32 = 0.0f;
        m03 = 0.0f; m13 = 0.0f; m23 = 0.0f; m33 = 1.0f;
        return this;
    }

    public Mat4f rotateX(float x, Mat4f dest) {
        float cos = (float) Math.cos(x);
        float sin = (float) Math.sin(x);
        float rm11 = cos;
        float rm12 = sin;
        float rm21 = -sin;
        float rm22 = cos;

        float nm10 = m10 * rm11 + m20 * rm12;
        float nm11 = m11 * rm11 + m21 * rm12;
        float nm12 = m12 * rm11 + m22 * rm12;
        float nm13 = m13 * rm11 + m23 * rm12;

        dest. m20 = m10 * rm21 + m20 * rm22;
        dest.m21 = m11 * rm21 + m21 * rm22;
        dest.m22 = m12 * rm21 + m22 * rm22;
        dest. m23 = m13 * rm21 + m23 * rm22;

        dest.m10 = nm10;
        dest.m11 = nm11;
        dest.m12 = nm12;
        dest.m13 = nm13;

        return this;
    }

    public Mat4f rotateX(float x) {
        return rotateX(x, this);
    }

    public Mat4f rotateY(float y, Mat4f dest) {
        float cos = (float) Math.cos(y);
        float sin = (float) Math.sin(y);
        float rm00 = cos;
        float rm02 = -sin;
        float rm20 = sin;
        float rm22 = cos;

        float nm00 = m00 * rm00 + m20 * rm02;
        float nm01 = m01 * rm00 + m21 * rm02;
        float nm02 = m02 * rm00 + m22 * rm02;
        float nm03 = m03 * rm00 + m23 * rm02;

        dest.m20 = m00 * rm20 + m20 * rm22;
        dest.m21 = m01 * rm20 + m21 * rm22;
        dest.m22 = m02 * rm20 + m22 * rm22;
        dest.m23 = m03 * rm20 + m23 * rm22;

        dest.m00 = nm00;
        dest.m01 = nm01;
        dest.m02 = nm02;
        dest.m03 = nm03;

        return this;
    }

    public Mat4f rotateY(float y) {
        return rotateY(y, this);
    }

    public Mat4f rotateZ(float z, Mat4f dest) {
        float cos = (float) Math.cos(z);
        float sin = (float) Math.sin(z);
        float rm00 = cos;
        float rm01 = sin;
        float rm10 = -sin;
        float rm11 = cos;

        float nm00 = m00 * rm00 + m10 * rm01;
        float nm01 = m01 * rm00 + m11 * rm01;
        float nm02 = m02 * rm00 + m12 * rm01;
        float nm03 = m03 * rm00 + m13 * rm01;

        dest.m10 = m00 * rm10 + m10 * rm11;
        dest.m11 = m01 * rm10 + m11 * rm11;
        dest.m12 = m02 * rm10 + m12 * rm11;
        dest.m13 = m03 * rm10 + m13 * rm11;

        dest.m00 = nm00;
        dest.m01 = nm01;
        dest.m02 = nm02;
        dest.m03 = nm03;

        return this;
    }

    public Mat4f rotateZ(float z) {
        return rotateZ(z, this);
    }
    public static Mat4f multiply(Mat4f a, Mat4f b) {
        Mat4f result = new Mat4f();

        result.m00 = a.m00 * b.m00 + a.m01 * b.m10 + a.m02 * b.m20 + a.m03 * b.m30;
        result.m01 = a.m00 * b.m01 + a.m01 * b.m11 + a.m02 * b.m21 + a.m03 * b.m31;
        result.m02 = a.m00 * b.m02 + a.m01 * b.m12 + a.m02 * b.m22 + a.m03 * b.m32;
        result.m03 = a.m00 * b.m03 + a.m01 * b.m13 + a.m02 * b.m23 + a.m03 * b.m33;

        result.m10 = a.m10 * b.m00 + a.m11 * b.m10 + a.m12 * b.m20 + a.m13 * b.m30;
        result.m11 = a.m10 * b.m01 + a.m11 * b.m11 + a.m12 * b.m21 + a.m13 * b.m31;
        result.m12 = a.m10 * b.m02 + a.m11 * b.m12 + a.m12 * b.m22 + a.m13 * b.m32;
        result.m13 = a.m10 * b.m03 + a.m11 * b.m13 + a.m12 * b.m23 + a.m13 * b.m33;

        result.m20 = a.m20 * b.m00 + a.m21 * b.m10 + a.m22 * b.m20 + a.m23 * b.m30;
        result.m21 = a.m20 * b.m01 + a.m21 * b.m11 + a.m22 * b.m21 + a.m23 * b.m31;
        result.m22 = a.m20 * b.m02 + a.m21 * b.m12 + a.m22 * b.m22 + a.m23 * b.m32;
        result.m23 = a.m20 * b.m03 + a.m21 * b.m13 + a.m22 * b.m23 + a.m23 * b.m33;

        result.m30 = a.m30 * b.m00 + a.m31 * b.m10 + a.m32 * b.m20 + a.m33 * b.m30;
        result.m31 = a.m30 * b.m01 + a.m31 * b.m11 + a.m32 * b.m21 + a.m33 * b.m31;
        result.m32 = a.m30 * b.m02 + a.m31 * b.m12 + a.m32 * b.m22 + a.m33 * b.m32;
        result.m33 = a.m30 * b.m03 + a.m31 * b.m13 + a.m32 * b.m23 + a.m33 * b.m33;

        return result;
    }

}
