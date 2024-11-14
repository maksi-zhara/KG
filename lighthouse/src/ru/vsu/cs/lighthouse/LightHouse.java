package ru.vsu.cs.lighthouse;

import ru.vsu.cs.lighthouse.Helpers.LPoint;
import ru.vsu.cs.lighthouse.Shapes.Trapezoid;

import java.awt.*;

public class LightHouse {
    private int posX, posY, height, widthTop, widthBottom;
    private Color color;

    Trapezoid tr;
    public LightHouse(int posX, int posY, int height, int widthTop, int widthBottom, Color color)
    {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.widthTop = widthTop;
        this.widthBottom = widthBottom;
        this.color = color;
        tr = new Trapezoid(new LPoint(posX, posY), new LPoint(posX+widthBottom, posY), new LPoint(posX+widthBottom-(widthBottom-widthTop)/2, posY-height), new LPoint(posX+(widthBottom-widthTop)/2, posY-height));
    }
    public void draw(Graphics2D g2) {
        tr.fill(g2, color);
        tr.draw(g2, color);

    }
}
