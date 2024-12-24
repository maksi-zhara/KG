package ru.vsu.cs.task2;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public final DrawPanel panel;

    public MainWindow() throws HeadlessException {
        panel = new DrawPanel(this.getWidth(), this.getHeight(), 100);
        this.add(panel);
    }
}