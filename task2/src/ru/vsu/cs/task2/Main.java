package ru.vsu.cs.task2;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 600);
        window.setVisible(true);

        ColorChanger colorChanger = new ColorChanger(window);
        colorChanger.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        colorChanger.setSize(400, 400);
        colorChanger.setVisible(true);
    }
}
