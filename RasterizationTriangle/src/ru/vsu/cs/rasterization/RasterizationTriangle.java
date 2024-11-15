package ru.vsu.cs.rasterization;
import ru.vsu.cs.rasterization.drawers.PixelDrawer;
import ru.vsu.cs.rasterization.helpers.Line;
import java.awt.*;

import static ru.vsu.cs.rasterization.helpers.Line.EPSILON;

public class RasterizationTriangle {
    public static void RasterizeTriangle(PixelDrawer pxDrawer, int[] x, int[] y, Color c0, Color c1, Color c2) {
        if(y[0] > y[1])
        {
            swap(y, 0, 1);
            swap(x, 0, 1);
        }
        if(y[1] > y[2])
        {
            swap(x, 1, 2);
            swap(y, 1, 2);
        }
        if(y[0] > y[1])
        {
            swap(y, 0, 1);
            swap(x, 0, 1);
        }
        int middlePointX = Line.getX(y[1], x[0], y[0], x[2], y[2]);
        for(int yc = y[0]; yc <= y[1]; yc++) {
            int x11 = Line.getX(yc, x[0], y[0], x[1], y[1]);
            int x12 = Line.getX(yc, x[0], y[0], middlePointX, y[1]);
            for(int xc = Math.min(x11, x12); xc <= Math.max(x11, x12); xc++) {
                pxDrawer.drawPixel(xc, yc, getColorForPoint(x, y, xc, yc, c0, c1, c2));
            }
        }
        for(int yc = y[1]; yc <= y[2]; yc++) {
            int x11 = Line.getX(yc, x[1], y[1], x[2], y[2]);
            int x12 = Line.getX(yc, x[2], y[2], middlePointX, y[1]);
            for(int xc = Math.min(x11, x12); xc <= Math.max(x11, x12); xc++) {
                pxDrawer.drawPixel(xc, yc, getColorForPoint(x, y, xc, yc, c0, c1, c2));
            }
        }
        bresenham(pxDrawer, x[0], y[0], x[1], y[1], x, y, c0, c1, c2);
        bresenham(pxDrawer, x[1], y[1], x[2], y[2], x, y, c0, c1, c2);
        bresenham(pxDrawer, x[0], y[0], x[2], y[2], x, y, c0, c1, c2);
    }
    public static Color getColorForPoint(int[] x, int[] y, int xc, int yc, Color c0, Color c1, Color c2)
    {
        float[] bCoords = barycentricCoordinates(x[0], y[0], x[1], y[1], x[2], y[2], xc, yc);
        int red = (int) (bCoords[0]*c0.getRed() + bCoords[1] * c1.getRed() + bCoords[2] * c2.getRed());
        int green = (int) (bCoords[0] * c0.getGreen()+ bCoords[1] * c1.getGreen() + bCoords[2] * c2.getGreen());
        int blue = (int) (bCoords[0] * c0.getBlue() + bCoords[1]*c1.getBlue() +  bCoords[2]*c2.getBlue());
        return new Color(clamp(red), clamp(green), clamp(blue));
    }
    private static float[] barycentricCoordinates(int x0, int y0, int x1, int y1, int x2, int y2, int px, int py) {
        float det = (y1 - y2) * (x0 - x2) + (x2 - x1) * (y0 - y2);
        if (Math.abs(det) <= EPSILON) {
            return new float[]{0.0f, 0.0f, 0.0f};
        }

        float a = ((y1 - y2) * (px - x2) + (x2 - x1) * (py - y2)) / det;
        float b = ((y2 - y0) * (px - x2) + (x0 - x2) * (py - y2)) / det;
        float c = 1 - a - b;

        return new float[]{a, b, c};
    }
    private static int clamp(int val) {
        return Math.max(0, Math.min(255, val));
    }

    public static void bresenham(PixelDrawer pxDrawer, int x0, int y0, int x1, int y1, int[] xarr, int[] yarr, Color c0, Color c1, Color c2)
    {
        int dx = Math.abs(x1-x0);
        int sx = x0 < x1 ? 1 : -1;
        int dy = -Math.abs(y1-y0);
        int sy = y0 < y1 ? 1 : -1;
        int error = dx+dy;
        while(true)
        {
            pxDrawer.drawPixel(x0, y0, getColorForPoint(xarr, yarr, x0, y0, c0, c1, c2));
            if(x0 == x1 && y0 == y1) break;
            int e2 = 2*error;
            if(e2 >= dy) {
                error = error+dy;
                x0 = x0+sx;
            }
            if(e2<=dx)
            {
                error = error+dx;
                y0 = y0+sy;
            }
        }
    }

    public static void main(String[] args) {
    }

    public static void swap(int[] ar, int i, int j)
    {
        int temp = ar[i];
        ar[i] = ar[j];
        ar[j] = temp;
    }

}
