package ru.vsu.cs.task3.Math.Vectors;

public class Vector4F extends Vector3F{
    public float w;
    public Vector4F()
    {
        super();
        w = 0.0f;
    }
    public Vector4F(float x, float y, float z, float w)
    {
        super(x, y, z);
        this.w = w;
    }
    public float magnitude()
    {
        return (float)Math.sqrt(x*x+y*y+z*z+w*w);
    }
    public void normalize() throws Exception {
        float magn = magnitude();
        if(magn<=EPSILON) throw new Exception("Vector magnitude is less then epsilon");
        x/=magn;
        y/=magn;
        z/=magn;
        w/=magn;
    }
    public Vector4F normalized() throws Exception {
        float magn = magnitude();
        if(magn<=EPSILON) throw new Exception("Vector magnitude is less then epsilon");
        return new Vector4F(x/magn, y/magn, z/magn, w/magn);
    }
    public static Vector4F sum(Vector4F v1, Vector4F v2)
    {
        return new Vector4F(v1.x+v2.x, v1.y+v2.y, v1.z+v2.z, v1.w+v2.w);
    }
    public static Vector4F sub(Vector4F v1, Vector4F v2)
    {
        return new Vector4F(v1.x-v2.x, v1.y-v2.y, v1.z-v2.z, v1.w-v2.w);
    }
    public void add(Vector4F v)
    {
        x+=v.x;
        y+=v.y;
        z+=v.z;
        w+=v.w;
    }
    public void sub(Vector4F v)
    {
        x-=v.x;
        y-=v.y;
        z-=v.z;
        w-=v.w;
    }
    public static float dot(Vector4F v1, Vector4F v2)
    {
        return v1.x*v2.x+v1.y*v2.y+v1.z*v2.z+v1.w*v2.w;
    }
    public static Vector4F cross(Vector4F v1, Vector4F v2)
    {
        return new Vector4F(
                v1.y*v2.z-v1.z*v2.y+v1.w*v2.z-v1.z*v2.w,
                v1.z*v2.x-v1.x*v2.z + v1.w*v2.x-v1.x*v2.w,
                v1.x*v2.y-v1.y*v2.x+v1.w*v2.y-v1.y*v2.w,
                v1.x*v2.y+v1.y*v2.x+v1.z*v2.z-v1.w*v2.w
        );
    }
}
