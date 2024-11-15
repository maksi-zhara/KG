package ru.vsu.cs.rasterization.drawers;

import java.awt.*;

public class SwingPixelDrawer implements PixelDrawer{
    public final Graphics2D gr;
    public SwingPixelDrawer(Graphics2D gr) {
        this.gr = gr;
    }
    @Override
    public void drawPixel(int x, int y, Color c) {
        gr.setColor(c);
        gr.fillRect(x, y, 1, 1);
    }
}
