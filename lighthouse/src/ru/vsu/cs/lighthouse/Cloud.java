package ru.vsu.cs.lighthouse;

import java.awt.*;

public class Cloud {
    int posX, posY, offsetX, offsetY, width, height;
    public Cloud(int posX, int posY, int offsetX, int offsetY, int width, int height)
    {
        this.posX = posX;
        this.posY = posY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
    }
    public void draw(Graphics2D g2) {
        Color color = new Color(235, 222, 188);
        g2.setColor(color);
        g2.fillOval(posX, posY, (int)(width*0.8), (int)(height*0.8));
        g2.fillOval(posX+offsetX, posY+offsetY, (int)(width*0.8), (int)(height*0.8));
        g2.fillOval(posX-offsetX, posY+offsetY, (int)(width*0.8), (int)(height*0.8));
    }
    public void setPosX(int posX) { this.posX = posX; }
    public void setPosY(int posY) { this.posY = posY; }
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
}
