package ru.vsu.cs.lighthouse.Shapes;

import ru.vsu.cs.lighthouse.Helpers.LPoint;

import java.awt.*;
import java.awt.geom.*;

public class Triangle implements Shape {
    private GeneralPath path;
    private LPoint[] points;
    public Triangle()
    {
        resetPath(new LPoint(0,0), new LPoint(0, 0), new LPoint(0,0));
    }
    public Triangle(LPoint p1, LPoint p2, LPoint p3)
    {
        resetPath(p1, p2, p3);
    }
    public void resetPath(LPoint p1, LPoint p2, LPoint p3)
    {
        path = new GeneralPath();
        points = new LPoint[3];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;

        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.closePath();
    }
    public void addOffset(int xOffset) {
        for(LPoint point : points) point.x += xOffset;
        resetPath(points[0], points[1], points[2]);
    }
    public void setPoints(LPoint p1, LPoint p2, LPoint p3) {
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        resetPath(p1, p2, p3);
    }
    @Override
    public Rectangle getBounds() {
        return path.getBounds();
    }

    @Override
    public Rectangle2D getBounds2D() {
        return path.getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
        return path.contains(x, y);
    }

    @Override
    public boolean contains(Point2D p) {
        return path.contains(p);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return path.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return path.intersects(r);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return path.contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return path.contains(r);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return path.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return path.getPathIterator(at, flatness);
    }
    public void fill(Graphics2D g2, Color color) {
        g2.setColor(color);
        g2.fill(path);
    }

    public void draw(Graphics2D g2, Color color) {
        g2.setColor(color);
        g2.draw(path);
    }
}
