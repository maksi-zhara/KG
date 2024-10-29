package ru.vsu.cs.lighthouse;

import ru.vsu.cs.lighthouse.Helpers.LPoint;
import ru.vsu.cs.lighthouse.Shapes.Triangle;

import java.awt.*;

public class Shark {
    private LPoint pos;
    private int width;
    private Triangle fin;
    private int speed;
    private final Color SHARKCOLOR = new Color(18, 32, 33);
    public Shark(LPoint pos,  int width, int speed) {
        this.pos = new LPoint(pos.x, pos.y);
        this.width = width;
        this.fin = new Triangle(pos, new LPoint(pos.x+width, pos.y), new LPoint(pos.x, pos.y-width));
        this.speed = speed;
    }
    public int getWidth() { return width; }
    public LPoint getPos() {
        return pos;
    }
    public void setPos(LPoint pos) {
        this.pos = pos;
        fin.setPoints(new LPoint(pos.x, pos.y), new LPoint(pos.x+width, pos.y), new LPoint(pos.x, pos.y-width));
    }
    public void addOffsetX(int xOffset) {
        pos.x+=xOffset;
        fin.addOffset(xOffset);
    }
    public void draw(Graphics2D g2) {
        fin.fill(g2, SHARKCOLOR);
    }
    public void update()
    {
        addOffsetX(speed);
    }
}
