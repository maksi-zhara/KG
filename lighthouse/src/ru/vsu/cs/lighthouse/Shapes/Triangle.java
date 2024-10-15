package ru.vsu.cs.lighthouse.Shapes;

import ru.vsu.cs.lighthouse.Helpers.LPoint;

import java.awt.*;
import java.awt.geom.*;

public class Triangle implements Shape {
    private Color color;
    private final GeneralPath path;
    public Triangle(Color color, LPoint v1, LPoint v2, LPoint v3)
    {
        this.color = color;
        path = new GeneralPath();
        path.moveTo(v1.x, v1.y);
        path.lineTo(v2.x, v2.y);
        path.lineTo(v3.x, v3.y);
        path.closePath();
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
    public void fill(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(path);
    }

    public void draw(Graphics2D g2, Color color) {
        g2.setColor(color);
        g2.draw(path);
    }
}
