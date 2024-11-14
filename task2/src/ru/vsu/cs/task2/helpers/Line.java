package ru.vsu.cs.task2.helpers;

public class Line {
    public static final float EPSILON = 1.175494351E-38F;
    public static int getX(int y, LPoint p1, LPoint p2) {
        if(Math.abs(p1.y-p2.y) < EPSILON) {
                return p1.x;
        }
        return (int) ((float)(y - p1.y) / (p2.y - p1.y) * (p2.x - p1.x) + p1.x);
    }
}
