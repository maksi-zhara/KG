package ru.vsu.cs.lighthouse;
import ru.vsu.cs.lighthouse.Helpers.LPoint;
import ru.vsu.cs.lighthouse.Shapes.BezierCurve;
import ru.vsu.cs.lighthouse.Shapes.Trapezoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class DrawPanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int TIMER_DELAY;
    private Timer timer;
    private int ticksFromStart = 0;
    private LightHouse lh;
    private Water water;
    private BezierCurve curve;
    private Ship ship;
    private Shark shark;
    private Mountains mountains;
    public DrawPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
        timer = new Timer(timerDelay, this);
        timer.start();
        mountains = new Mountains(100);

        lh = new LightHouse(600, 800, 500, 150, 400, new Color(183, 65, 53));
        water = new Water(PANEL_HEIGHT-300, 300, PANEL_WIDTH, new Color(48, 67, 64));
        Point[] points = new Point[]{
                new Point(100, 400),
                new Point(200, 100),
                new Point(600, 100),
                new Point(700, 400)
        };
        curve = new BezierCurve(points);
        ship = new Ship(100, new LPoint(200, 600));
        shark = new Shark(700, 700, 50);
    }

    @Override
    public void paint(final Graphics gr) {
        super.paint(gr);
        mountains.draw((Graphics2D)gr);

        lh.draw((Graphics2D)gr);
        water.draw((Graphics2D)gr);
        curve.draw((Graphics2D)gr);
        curve.drawControlPoints((Graphics2D)gr);
        ship.draw((Graphics2D)gr);
        shark.draw((Graphics2D)gr);

    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
            ++ticksFromStart;
        }
    }
}