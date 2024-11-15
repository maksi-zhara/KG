package ru.vsu.cs.rasterization.drawers;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class FXPixelDrawer implements PixelDrawer{
    private final GraphicsContext graphicsContext;
    public FXPixelDrawer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void drawPixel(int x, int y, java.awt.Color c) {
        graphicsContext.getPixelWriter().setColor(x,
                y,
                new Color(c.getRed(), c.getGreen(), c.getBlue(), 1));
    }
}
