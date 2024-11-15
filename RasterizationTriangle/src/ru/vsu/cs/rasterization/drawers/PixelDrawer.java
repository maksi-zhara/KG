package ru.vsu.cs.rasterization.drawers;

import java.awt.*;

public interface PixelDrawer {
    void drawPixel(int x, int y, Color c);
}
