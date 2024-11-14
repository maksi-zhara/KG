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
    public void draw(Graphics2D g2) {
        LPoint newPoint = new LPoint(Line.getX(points[1].y, points[0], points[2]), points[1].y);
        for(int y = points[0].y; y <= points[1].y; y++)
        {
            LPoint p1 = points[0];
            LPoint p2 = points[1];
            LPoint p3 = newPoint;
            int x1 = Line.getX(y, p1, p2);
            int x2 = Line.getX(y, p1, p3);
            for(int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                float[] bCoords = barycentricCoordinates(new LPoint(x, y));
                Color color = Color.BLACK;
                if(colors.length == 1) color=colors[0];
                else if(colors.length == 3) color = interpolateColor(bCoords);
                g2.setColor(color);
                g2.fillRect(x, y, 1, 1);
            }
        }
    }
    private float[] barycentricCoordinates(LPoint p) {
        float det = (points[1].y - points[2].y)* (points[0].x - points[2].x) + (points[2].x - points[1].x) * (points[0].y - points[2].y);
        if(det<=EPSILON) return new float[]{0.0f, 0.0f, 0.0f};
        float a = ((points[1].y - points[2].y) * (p.x - points[2].x) + (points[2].x - points[1].x) * (p.y - points[2].y))/det;
        float b = ((points[2].y-points[0].y)*(p.x-points[2].x)+(points[0].x - points[2].x) * (p.y - points[2].y))/det;
        float c = 1 - a - b;
        return new float[]{a, b, c};
    }
    private Color interpolateColor(float[] coords) {
        int red = (int) (coords[0]*colors[0].getRed() + coords[1] * colors[1].getRed() + coords[2] * colors[2].getRed());
        int green = (int) (coords[0] * colors[0].getGreen()+ coords[1] * colors[1].getGreen() + coords[2] * colors[2].getGreen());
        int blue = (int) (coords[0] * colors[0].getBlue() + coords[1]*colors[1].getBlue() +  coords[2]*colors[2].getBlue());

        return new Color(clamp(red), clamp(green), clamp(blue));
    }
    private int clamp(int val) {
        return Math.max(0, Math.min(255, val));
    }
}
