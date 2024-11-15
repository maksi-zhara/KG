package ru.vsu.cs.rasterization.helpers;

public class Line {
    public static final float EPSILON = 1.175494351E-38F;
    public static int getX(int y, int x1, int y1, int x2, int y2) {
        if(Math.abs(y1-y2) < EPSILON) {
                return x1;
        }
        return (int) ((float)(y - y1) / (y2 - y1) * (x2 - x1) + x1);
    }
}
