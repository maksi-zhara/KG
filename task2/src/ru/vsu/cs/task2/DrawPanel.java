package ru.vsu.cs.task2;

import ru.vsu.cs.task2.helpers.LPoint;
import ru.vsu.cs.task2.shapes.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawPanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int TIMER_DELAY;
    private Timer timer;
    private int ticksFromStart = 0;
    private Triangle tr;
    public DrawPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
        timer = new Timer(timerDelay, this);
        timer.start();
        this.tr = new Triangle(new LPoint(200, 300), new LPoint(600, 300), new LPoint(400, 100), Color.RED, Color.GREEN, Color.BLUE);
    }

    public void changeTriangleColor(Color c1, Color c2, Color c3)
    {
        tr.changeColor(c1, c2, c3);
    }

    @Override
    public void paint(final Graphics gr) {
        super.paint(gr);
        tr.draw((Graphics2D) gr);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
            ++ticksFromStart;
        }
    }
}