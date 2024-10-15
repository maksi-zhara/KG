package ru.vsu.cs.lighthouse;

import ru.vsu.cs.lighthouse.Helpers.LPoint;
import ru.vsu.cs.lighthouse.Shapes.Triangle;

import java.awt.*;

public class Shark {
    private int posX, posY, width;
    private Triangle fin;
    public Shark(int posX, int posY, int width) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.fin = new Triangle(Color.BLACK, new LPoint(posX, posY), new LPoint(posX+width, posY), new LPoint(posX, posY-width));
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void draw(Graphics2D g2) {
        Color color = new Color(255, 0, 0);
        g2.setColor(color);
        fin.draw(g2, color);
    }
}
