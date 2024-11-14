package ru.vsu.cs.task3.Math.Matrix;

import ru.vsu.cs.task3.Math.Vectors.Vector3F;

public class Mat3f {
    public float m00, m10, m20,
            m01, m11, m21,
            m02, m12, m22;
    public Mat3f() {
        loadIdentity();
    }
    public Mat3f(float m00, float m10, float m20,
                 float m01, float m11, float m21,
                 float m02, float m12, float m22)
    {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;

        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;

        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
    }
    public Mat3f(Vector3F x, Vector3F y, Vector3F z)
    {
        m00 = x.x; m10 = y.x; m20 = z.x;
        m01 = x.y; m11 = y.y; m21 = z.y;
        m02 = x.z; m12 = y.z; m22 = z.z;
    }
    public Mat3f loadIdentity() {
        m00 = 1.0f; m10 = 0.0f; m20 = 0.0f;
        m01 = 0.0f; m11 = 1.0f; m21 = 0.0f;
        m02 = 0.0f; m12 = 0.0f; m22 = 1.0f;
        return this;
    }
    public static Mat3f multiply(Mat3f a, Mat3f b) {
        Mat3f result = new Mat3f();
        result.m00 = a.m00 * b.m00 + a.m01 * b.m10 + a.m02 * b.m20;
        result.m01 = a.m00 * b.m01 + a.m01 * b.m11 + a.m02 * b.m21;
        result.m02 = a.m00 * b.m02 + a.m01 * b.m12 + a.m02 * b.m22;

        result.m10 = a.m10 * b.m00 + a.m11 * b.m10 + a.m12 * b.m20;
        result.m11 = a.m10 * b.m01 + a.m11 * b.m11 + a.m12 * b.m21;
        result.m12 = a.m10 * b.m02 + a.m11 * b.m12 + a.m12 * b.m22;

        result.m20 = a.m20 * b.m00 + a.m21 * b.m10 + a.m22 * b.m20;
        result.m21 = a.m20 * b.m01 + a.m21 * b.m11 + a.m22 * b.m21;
        result.m22 = a.m20 * b.m02 + a.m21 * b.m12 + a.m22 * b.m22;
        return result;
    }
}
