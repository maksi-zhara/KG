package ru.vsu.cs.lighthouse;

import ru.vsu.cs.lighthouse.Helpers.LPoint;
import ru.vsu.cs.lighthouse.Shapes.Trapezoid;
import ru.vsu.cs.lighthouse.Shapes.Triangle;

import java.awt.*;

public class LightHouse {
    private int posX, posY, height, widthTop, widthBottom;
    private final Trapezoid tr;
    private final Trapezoid leftWindow;
    private final Trapezoid rightWindow;
    private final Trapezoid top;
    private final Triangle topLeftTriangle;
    private final Triangle topRightTriangle;
    private final Color TOP_MAIN_COLOR = new Color(49, 59, 59);
    public LightHouse(int posX, int posY, int height, int widthTop, int widthBottom)
    {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.widthTop = widthTop;
        this.widthBottom = widthBottom;
        tr = new Trapezoid(new LPoint(posX, posY), new LPoint(posX+widthBottom, posY), new LPoint(posX+widthBottom-(widthBottom-widthTop)/2, posY-height), new LPoint(posX+(widthBottom-widthTop)/2, posY-height));
        leftWindow = new Trapezoid(new LPoint(posX+widthBottom/2-(widthTop+10)/8-3, posY-height-105-60),
                                    new LPoint(posX+widthBottom/2-(widthTop+10)/8-3, posY-height-105),
                                    new LPoint(posX+widthBottom/2-(widthTop+10)/8 - 35, posY-height-108),
                                    new LPoint(posX+widthBottom/2-(widthTop+10)/8 - 35, posY-height-162)
                                    );
        rightWindow = new Trapezoid(new LPoint(posX+widthBottom/2+(widthTop+10)/8+3, posY-height-105-60),
                new LPoint(posX+widthBottom/2+(widthTop+10)/8+3, posY-height-105),
                new LPoint(posX+widthBottom/2+(widthTop+10)/8+35, posY-height-108),
                new LPoint(posX+widthBottom/2+(widthTop+10)/8+35, posY-height-162));
        top = new Trapezoid();
        topLeftTriangle = new Triangle();
        topRightTriangle = new Triangle();
    }
    public void draw(Graphics2D g2, Color color) {
        tr.fill(g2, color);
        g2.setColor(TOP_MAIN_COLOR);
        g2.fillRect(posX + 100, posY - 200, 30, 40);
        g2.fillRect(posX + 200, posY - 400, 30, 40);
        g2.fillRect(posX + 220, posY - 250, 30, 40);
        g2.fillRect(posX + 150, posY - 320, 30, 40);

        g2.setColor(new Color(17, 29, 28));
        g2.fillRect(posX + 104, posY - 196, 22, 32);
        g2.fillRect(posX + 204, posY - 396, 22, 32);
        g2.fillRect(posX + 224, posY - 246, 22, 32);
        g2.fillRect(posX + 154, posY - 316, 22, 32);

        drawLighthouseTop(g2);
    }
    public void drawLighthouseTop(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(posX, posY, 200, 200);
        g2.setColor(TOP_MAIN_COLOR);
        g2.fillRect(posX+widthBottom/2-widthTop/2-20, posY-height, widthTop+40, 10);
        g2.fillRect(posX+widthBottom/2-widthTop/2-30, posY-height-10, widthTop+60, 10);
        g2.fillRect(posX+widthBottom/2-widthTop/2, posY-height-40, widthTop, 30);
        g2.fillRect(posX+widthBottom/2-widthTop/2-30, posY-height-55, widthTop+60, 15);
        g2.fillRect(posX+widthBottom/2-widthTop/2-20, posY-height-100, 5, 50);
        g2.fillRect(posX+widthBottom/2+widthTop/2+15, posY-height-100, 5, 50);
        g2.fillRect(posX+widthTop/2+30, posY-height-65, widthTop+37, 3);
        g2.fillRect(posX+widthTop/2+30, posY-height-70, widthTop+37, 3);
        g2.fillRect(posX+widthTop/2+30, posY-height-75, widthTop+37, 3);
        g2.fillRect(posX+widthTop/2+30, posY-height-100, widthTop+37, 3);
        g2.fillRect(posX+widthBottom/2-widthTop/2+10, posY-height-105, widthTop-20, 50);
        g2.fillRect(posX+widthBottom/2-widthTop/2+10, posY-height-175, widthTop-20, 70);
        g2.setColor(new Color(250, 237, 204));
        g2.fillRect(posX+widthBottom/2-(widthTop+10)/8, posY-height-105-60, (widthTop+10)/4, 60);
        leftWindow.fill(g2, new Color(236, 181, 127));
        rightWindow.fill(g2, new Color(236, 181, 127));

        LPoint L1 = new LPoint(posX+widthBottom/2-widthTop/2+10, posY-height-175);
        LPoint L2 = new LPoint(posX+widthBottom/2-widthTop/2+widthTop-20+10, posY-height-175);
        LPoint L3 = new LPoint(posX+widthBottom/2-widthTop/2+widthTop-20+18, posY-height-175-10);
        LPoint L4 = new LPoint(posX+widthBottom/2-widthTop/2+10-8, posY-height-175-10);
        top.resetPath(L1, L2, L3, L4);
        top.fill(g2, new Color(49, 59, 59));

        topLeftTriangle.setPoints(L4, new LPoint((L4.x+L2.x)/2, L3.y), new LPoint((L4.x+L2.x)/2, L3.y-70));
        topLeftTriangle.fill(g2, TOP_MAIN_COLOR);

        topRightTriangle.setPoints(L3, new LPoint((L4.x+L2.x)/2, L3.y), new LPoint((L4.x+L2.x)/2, L3.y-70));
        topRightTriangle.fill(g2, TOP_MAIN_COLOR);
    }
}
