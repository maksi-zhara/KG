package ru.vsu.cs.lighthouse.Shapes;

import java.awt.*;
import java.util.ArrayList;

public class BezierCurve {
    private Point[] controlPoints;
    public BezierCurve(Point[] controlPoints) {
        this.controlPoints = controlPoints;
    }
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(2.0f));
        g.setColor(Color.BLUE);
        Point previousPoint = null;

        for (double t = 0; t <= 1; t += 0.01) {
            Point p = bezier(t);
            if (previousPoint != null) {
                g.drawLine(previousPoint.x, previousPoint.y, p.x, p.y);
            }
            previousPoint = p;
        }
    }
    private Point bezier(double t) {
        int n = controlPoints.length - 1; // Степень многочлена
        double x = 0;
        double y = 0;

        for (int i = 0; i <= n; i++) {
            double binomialCoeff = binomial(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i);
            x += binomialCoeff * controlPoints[i].x;
            y += binomialCoeff * controlPoints[i].y;
        }

        return new Point((int) x, (int) y);
    }

    public void drawControlPoints(Graphics2D g) {
        g.setColor(Color.RED);
        for (Point p : controlPoints) {
            g.fillOval(p.x - 5, p.y - 5, 10, 10); // Рисуем контрольные точки
        }
    }

    private int binomial(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomial(n - 1, k - 1) + binomial(n - 1, k);
    }
}
