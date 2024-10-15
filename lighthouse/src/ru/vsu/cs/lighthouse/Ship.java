package ru.vsu.cs.lighthouse;

import ru.vsu.cs.lighthouse.Helpers.LPoint;
import ru.vsu.cs.lighthouse.Shapes.Trapezoid;
import ru.vsu.cs.lighthouse.Shapes.Triangle;

import java.awt.*;

public class Ship {
    private int width;
    private int height;
    private LPoint pos;
    private Trapezoid main;
    private Triangle sail;

    public Ship(int width, LPoint pos)
    {
        this.width = width;
        this.height = (int)(width*0.9);
        this.pos = pos;
        int temp = (int)(width*0.15);
        LPoint mainLT = new LPoint(pos.x-temp, (int)(pos.y-height*0.2));
        LPoint mainRB = new LPoint(pos.x+width-temp, pos.y);
        LPoint mainRT = new LPoint(pos.x+width+temp, (int)(pos.y-height*0.2));

        main = new Trapezoid(pos, mainLT,
                mainRT, mainRB);
        sail = new Triangle(new Color(0, 255, 0), new LPoint(pos.x + width/3, mainRT.y - 30), new LPoint(pos.x + width/3, mainRT.y - 40 - (int)(height*0.7))
                                                    , new LPoint(pos.x + (int)(width*0.9), mainRT.y - 30));
    }
    public void draw(Graphics2D g2) {
        Color color = new Color(255, 0, 0);
        g2.setColor(color);
        main.draw(g2, color);
        sail.draw(g2, color);
        g2.drawLine(pos.x, (int)(pos.y-height*0.2), pos.x+width/3, pos.y-height);
        g2.drawLine((int)(pos.x+width*1.1), (int)(pos.y-height*0.2), pos.x + (int)(width*0.9), (int)(pos.y-height*0.2)-30);
        g2.drawLine(pos.x + width/3, pos.y, pos.x + width/3, pos.y-height);
    }
}
