package ru.vsu.cs.lighthouse;

import ru.vsu.cs.lighthouse.Helpers.LPoint;
import ru.vsu.cs.lighthouse.Shapes.BezierCurve;

import java.awt.*;

public class WaveAnimation extends BezierCurve{
    private int animationSpeed;
    private int moveSpeed;

    public WaveAnimation(LPoint[] points, int animationSpeed, int moveSpeed) {
        controlPoints = points;
        this.animationSpeed = animationSpeed;
        this.moveSpeed = moveSpeed;
        setColor(Color.WHITE);
    }
    public void update(int tick)
    {
        controlPoints[1].y += (int) (50 * Math.sin((tick*animationSpeed + 150) * 0.1));
        controlPoints[2].y += (int) (50 * Math.sin((tick*animationSpeed + 50) * 0.2));
        addXOffset(moveSpeed);
    }
    public int getXPos() {
        return controlPoints[3].x;
    }

}