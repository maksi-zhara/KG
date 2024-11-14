package ru.vsu.cs.task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChanger extends JFrame {
    MainWindow window = new MainWindow();
    private JTextField[] redFields = new JTextField[3];
    private JTextField[] greenFields = new JTextField[3];
    private JTextField[] blueFields = new JTextField[3];
    private Color[] currentColors;
    public ColorChanger(MainWindow window) {
        this.window = window;
        this.currentColors = new Color[3];
        setTitle("Цветовой сменщик");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 6));

        for (int i = 0; i < 3; i++) {
            panel.add(new JLabel("Цвет " + (i + 1) + " - Красный (0-255):"));
            redFields[i] = new JTextField();
            panel.add(redFields[i]);

            panel.add(new JLabel("Цвет " + (i + 1) + " - Зеленый (0-255):"));
            greenFields[i] = new JTextField();
            panel.add(greenFields[i]);

            panel.add(new JLabel("Цвет " + (i + 1) + " - Синий (0-255):"));
            blueFields[i] = new JTextField();
            panel.add(blueFields[i]);
        }

        JButton changeColorButton = new JButton("Поменять цвет");
        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor();
            }
        });

        panel.add(changeColorButton);

        add(panel);
    }

    private void changeColor() {
        try {
            for (int i = 0; i < 3; i++) {
                int red = Integer.parseInt(redFields[i].getText());
                int green = Integer.parseInt(greenFields[i].getText());
                int blue = Integer.parseInt(blueFields[i].getText());
                if (isValidColorValue(red) && isValidColorValue(green) && isValidColorValue(blue)) {
                    currentColors[i] = new Color(red, green, blue);
                } else {
                    JOptionPane.showMessageDialog(this, "Значения цветовых компонентов должны быть от 0 до 255.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            window.panel.changeTriangleColor(currentColors[0], currentColors[1], currentColors[2]); //todo
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, введите корректные числовые значения.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidColorValue(int value) {
        return value >= 0 && value <= 255;
    }
}
