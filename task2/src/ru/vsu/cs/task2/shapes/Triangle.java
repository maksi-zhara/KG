package ru.vsu.cs.task2.shapes;

import ru.vsu.cs.task2.helpers.LPoint;

import java.awt.*;

public class Triangle{
    //private final GeneralPath path;
    private LPoint[] points;
    private Color[] colors;
    public Triangle(LPoint v1, LPoint v2, LPoint v3, Color c)
    {
        points = new LPoint[3];
        points[0] = v1;
        points[1] = v2;
        points[2] = v3;
        colors = new Color[]{c};
    }
    public Triangle(LPoint v1, LPoint v2, LPoint v3, Color c1, Color c2, Color c3)
    {
        points = new LPoint[3];
        points[0] = v1;
        points[1] = v2;
        points[2] = v3;

        colors = new Color[3];
        colors[0] = c1;
        colors[1] = c2;
        colors[2] = c3;
    }

    public void draw(Graphics2D g2) {
        int minX = Math.min(Math.min(points[0].x, points[1].x), points[2].x);
        int minY = Math.min(Math.min(points[0].y, points[1].y), points[2].y);
        int maxX = Math.max(Math.max(points[0].x, points[1].x), points[2].x);
        int maxY = Math.max(Math.max(points[0].y, points[1].y), points[2].y);

        for (int y = minY; y <= maxY; y++)
        {
            for (int x = minX; x <= maxX; x++)
            {
                double[] bCoords = barycentricCoordinates(new LPoint(x, y));
                if (bCoords[0] >= 0 && bCoords[1] >= 0 && bCoords[2] >= 0)
                {
                    Color color = Color.BLACK;
                    if(colors.length == 1) color=colors[0];
                    else if(colors.length == 3) color = interpolateColor(bCoords);
                    g2.setColor(color);
                    g2.fillRect(x, y, 1, 1);
                }
            }
        }
    }
    private double[] barycentricCoordinates(LPoint p) {
        double det = (points[1].y - points[2].y)* (points[0].x - points[2].x) + (points[2].x - points[1].x) * (points[0].y - points[2].y);
        double a = ((points[1].y - points[2].y) * (p.x - points[2].x) + (points[2].x - points[1].x) * (p.y - points[2].y))/det;
        double b = ((points[2].y-points[0].y)*(p.x-points[2].x)+(points[0].x - points[2].x) * (p.y - points[2].y))/det;
        double c = 1 - a - b;
        return new double[]{a, b, c};
    }
    private Color interpolateColor(double[] coords) {
        int red = (int) (coords[0]*colors[0].getRed() + coords[1] * colors[1].getRed() + coords[2] * colors[2].getRed());
        int green = (int) (coords[0] * colors[0].getGreen()+ coords[1] * colors[1].getGreen() + coords[2] * colors[2].getGreen());
        int blue = (int) (coords[0] * colors[0].getBlue() + coords[1]*colors[1].getBlue() +  coords[2]*colors[2].getBlue());

        return new Color(clamp(red), clamp(green), clamp(blue));
    }
    private int clamp(int val) {
        return Math.max(0, Math.min(255, val));
    }
}
