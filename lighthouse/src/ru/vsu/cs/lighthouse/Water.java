package ru.vsu.cs.lighthouse;

import java.awt.*;

public class Water {
    private int y, height, width;
    private Color color;

    public Water(int y, int height, int width, Color color) {
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(0, y, width, height);
        g2.drawRect(0, y, width, height);
    }
}
