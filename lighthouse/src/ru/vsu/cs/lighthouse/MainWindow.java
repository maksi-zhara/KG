package ru.vsu.cs.lighthouse;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow extends JFrame {
    private DrawPanel panel;
    public MainWindow() throws HeadlessException {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();
                panel = new DrawPanel(width, height, 100);
                add(panel);
                revalidate();
                repaint();
            }
        });
    }
}
