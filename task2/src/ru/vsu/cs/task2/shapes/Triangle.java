package ru.vsu.cs.task2.shapes;

import ru.vsu.cs.task2.helpers.LPoint;
import ru.vsu.cs.task2.helpers.Line;

import java.awt.*;

import static ru.vsu.cs.task2.helpers.Line.EPSILON;

public class Triangle{
    private LPoint[] points;
    private Color[] colors;
    public Triangle(LPoint v1, LPoint v2, LPoint v3, Color c1, Color c2, Color c3)
    {
        points = new LPoint[3];
        if(v1.y == Math.min(v1.y, Math.min(v2.y, v3.y))) {
            points[0] = v1;
            points[1] = (v3.y<v2.y) ? v3 : v2;
            points[2] = (v3.y<v2.y) ? v2 : v3;
        }
        else if(v1.y < v2.y) {
            points[1] = v1;
            points[2] = v2;
            points[0] = v3;
        } else if(v1.y < v3.y) {
            points[1] = v1;
            points[2] = v3;
            points[0] = v2;
        }
        else {
            points[2] = v1;
            points[0] = (v2.y < v3.y) ? v2 : v3;
            points[1] = (v2.y < v3.y) ? v3 : v2;
        }
        colors = new Color[3];
        colors[0] = c1;
        colors[1] = c2;
        colors[2] = c3;
    }
    public void changeColor(Color c1, Color c2, Color c3)
    {
        colors[0] = c1;
        colors[1] = c2;
        colors[2] = c3;
    }
    public LPoint[] getPoints() { return points; }
    public Color[] getColors() { return colors; }
}
