package ru.vsu.cs.task3.Math.Vectors;

public class Vector3F {
    public final float EPSILON = 2.220446049250313e-16F;
    public float x, y, z;
    public Vector3F()
    {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }
    public Vector3F(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public float magnitude()
    {
        return (float)Math.sqrt(x*x+y*y+z*z);
    }
    public void normalize() throws Exception {
        float magn = magnitude();
        if(magn<=EPSILON) throw new Exception("Vector magnitude is less then epsilon");
        x/=magn;
        y/=magn;
        z/=magn;
    }
    public Vector3F normalized() throws Exception {
        float magn = magnitude();
        if(magn<=EPSILON) throw new Exception("Vector magnitude is less then epsilon");
        return new Vector3F(x/magn, y/magn, z/magn);
    }
    public static Vector3F sum(Vector3F v1, Vector3F v2)
    {
        return new Vector3F(v1.x+v2.x, v1.y+v2.y, v1.z+v2.z);
    }
    public static Vector3F sub(Vector3F v1, Vector3F v2)
    {
        return new Vector3F(v1.x-v2.x, v1.y-v2.y, v1.z-v2.z);
    }
    public void add(Vector3F v)
    {
        x+=v.x;
        y+=v.y;
        z+=v.z;
    }
    public void sub(Vector3F v)
    {
        x-=v.x;
        y-=v.y;
        z-=v.z;
    }
    public static float dot(Vector3F v1, Vector3F v2)
    {
        return v1.x*v2.x+v1.y*v2.y+v1.z*v2.z;
    }
    public static Vector3F cross(Vector3F v1, Vector3F v2)
    {
        return new Vector3F(v1.y*v2.z - v1.z*v2.y, v1.z*v2.x-v1.x*v2.z, v1.x*v2.y-v1.y*v2.x);
    }
}
