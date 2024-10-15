package ru.vsu.cs.lighthouse;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class Mountains {
    private final  GeneralPath path;
    public Mountains(int startHeight) {
       path = new GeneralPath();
       path.moveTo(0, startHeight);
       path.lineTo(20, startHeight);
       path.lineTo(50, startHeight-50);
       path.lineTo(75, startHeight-30);
       path.lineTo(90, startHeight-60);
       path.lineTo(120, startHeight+60);
       path.lineTo(140, startHeight+40);
       path.lineTo(160, startHeight+70);
       path.lineTo(200, startHeight+50);
       path.lineTo(400, startHeight+180);
       path.lineTo(470, startHeight+200);
       path.lineTo(570, startHeight+320);
       path.lineTo(600, startHeight+280);
       path.lineTo(660, startHeight+330);
       path.lineTo(710, startHeight+290);
       path.lineTo(710, startHeight+10000);
       path.lineTo(0, startHeight+10000);

       path.closePath();

    }

    public void draw(Graphics2D g2d) {
        Color color = new Color(255, 0, 0);
        g2d.setColor(color);

        // Задаем цвет гор
        g2d.setColor(new Color(43, 61, 61)); // Цвет гор
        g2d.fill(path);

        // Рисуем контур (по желанию)
        g2d.setColor(Color.DARK_GRAY);
        g2d.draw(path);
    }
}
