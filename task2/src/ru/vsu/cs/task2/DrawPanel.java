package ru.vsu.cs.task2;

import ru.vsu.cs.rasterization.RasterizationTriangle;
import ru.vsu.cs.rasterization.drawers.FXPixelDrawer;
import ru.vsu.cs.rasterization.drawers.SwingPixelDrawer;

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
    public DrawPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
        timer = new Timer(timerDelay, this);
        timer.start();
    }

    @Override
    public void paint(final Graphics gr) {
        super.paint(gr);
        RasterizationTriangle.RasterizeTriangle(new SwingPixelDrawer((Graphics2D) gr),
                    new int[]{200, 400, 700}, new int[] {100, 200, 400}, Color.RED, Color.GREEN, Color.BLUE
                );
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
            ++ticksFromStart;
        }
    }
}