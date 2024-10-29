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
import java.util.Random;

public class DrawPanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int TIMER_DELAY;
    private Timer timer;
    private int ticksFromStart = 0;
    private LightHouse lh;
    private Water water;
    private WaveAnimation wave;
    private WaveAnimation[] waves;
    private final Ship ship;
    private Shark shark;
    private Mountains mountains;
    private Sun sun;
    private Clouds[] clouds;
    private Random random = new Random();
    private final Color LIGHTHOUS_MAIN_COLOR = new Color(183, 65, 53);
    public DrawPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
        timer = new Timer(timerDelay, this);
        timer.start();
        setBackground(new Color(255, 124, 80));
        mountains = new Mountains(500);
        lh = new LightHouse(600, 800, 500, 150, 400);
        water = new Water(PANEL_HEIGHT-350, 350, PANEL_WIDTH, new Color(48, 67, 64));
        LPoint[] controlPoints = new LPoint[4];
        controlPoints[0] = new LPoint(50, 200);
        controlPoints[1] = new LPoint(150, 100);
        controlPoints[2] = new LPoint(250, 300);
        controlPoints[3] = new LPoint(350, 200);
        waves = new WaveAnimation[25];
        initWaves(waves);
        LPoint[] points = new LPoint[]{
                new LPoint(100, 400),
                new LPoint(200, 100),
                new LPoint(600, 100),
                new LPoint(700, 400)
        };
        ship = new Ship(150, getShipRandomPos());
        shark = new Shark(getShipRandomPos(), random.nextInt(20) + 20, 5);
        sun = new Sun(200, new LPoint(200, 200));
        initClouds();
    }
    public void initClouds()
    {
        clouds = new Clouds[10];
        for(int i = 0; i < clouds.length; i++)
        {
            clouds[i] = new Clouds(new LPoint(random.nextInt(PANEL_WIDTH), random.nextInt(300)+50), random.nextInt(100)+100, random.nextInt(20)+30);
        }
    }
    public void drawClouds(Graphics2D gr) {
        for(Clouds cloud : clouds) {
            if(cloud.getPos().x > PANEL_WIDTH) cloud.setPos(getCloudsRandomPos());
            else if(cloud.getPos().x + 500 > 0) cloud.draw(gr);
        }
    }
    public void updateClouds() {
        for(Clouds cloud : clouds) cloud.update(5);
    }
    public LPoint getCloudsRandomPos() {
        return new LPoint(-random.nextInt(600), random.nextInt(300)+50);
    }
    private void drawShark(Graphics2D gr)
    {
        if(shark.getPos().x > PANEL_WIDTH) {
            shark.setPos(getShipRandomPos());
        }
        else if(ship.getPosX() + shark.getWidth() > 0) ship.draw(gr);
    }
    private void initWaves(WaveAnimation[] waves) {
        for(int i = 0; i < waves.length; i++)
        {
            waves[i] = new WaveAnimation(generateWaveControlPoints(), random.nextInt(10)+80, random.nextInt(5)+10);
            waves[i].setControlPoints(generateWaveControlPointsAtStart());
        }
    }
    private LPoint[] generateWaveControlPointsAtStart() {
        LPoint[] controlPoints = new LPoint[4];
        int step = random.nextInt(30) + 20;
        int startX = random.nextInt(PANEL_WIDTH);
        int startY = random.nextInt(300) + (PANEL_HEIGHT-300);
        controlPoints[0] = new LPoint(startX, startY);
        controlPoints[1] = new LPoint(startX+step, startY-step);
        controlPoints[2] = new LPoint(controlPoints[1].x+step, startY+step);
        controlPoints[3] = new LPoint(controlPoints[2].x+step, startY);
        return controlPoints;
    }
    private LPoint[] generateWaveControlPoints() {
        LPoint[] controlPoints = new LPoint[4];
        int step = random.nextInt(30) + 20;
        int startX = -step*4 - random.nextInt(100) + 50;
        int startY = random.nextInt(300) + (PANEL_HEIGHT-300);
        controlPoints[0] = new LPoint(startX, startY);
        controlPoints[1] = new LPoint(startX+step, startY-step);
        controlPoints[2] = new LPoint(controlPoints[1].x+step, startY+step);
        controlPoints[3] = new LPoint(controlPoints[2].x+step, startY);
        return controlPoints;
    }
    private void updateWaves() {
        for(WaveAnimation wave : waves) {
            if(wave.getXPos() > PANEL_WIDTH) {
                wave.setControlPoints(generateWaveControlPoints());
            }
            else wave.update(ticksFromStart);
        }
    }
    private void drawWaves(Graphics2D gr)
    {
        for(WaveAnimation wave : waves) wave.draw(gr);
    }
    private void updateShip() {
        if(ship.getPosX() > PANEL_WIDTH) {
            ship.setPos(getShipRandomPos());
        }
        ship.update(25);
    }
    private LPoint getShipRandomPos() {
        return new LPoint(-random.nextInt(200), random.nextInt(100) + (PANEL_HEIGHT-200));
    }
    private void drawShip(Graphics2D gr) {
        if(ship.getPosX() + ship.getWidth() > 0) ship.draw(gr);
    }
    @Override
    public void paint(final Graphics gr) {
        super.paint(gr);
        sun.draw((Graphics2D) gr);
        drawClouds((Graphics2D)gr);
        mountains.draw((Graphics2D)gr);
        lh.draw((Graphics2D)gr, LIGHTHOUS_MAIN_COLOR);
        water.draw((Graphics2D)gr);
        drawWaves((Graphics2D) gr);
        drawShip((Graphics2D)gr);
        shark.draw((Graphics2D)gr);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
            updateWaves();
            updateShip();
            shark.update();
            updateClouds();
            ++ticksFromStart;
        }
    }
}