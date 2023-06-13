package sk.stuba.fei.uim.oop.window;

import sk.stuba.fei.uim.oop.logic.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Window {
    private JFrame frame;
    private DrawingPanel drawingPanel;
    private JPanel menuPanel;
    private JButton domButton;
    private JLabel label;
    private JButton changeColorButton;
    private JButton presunButton;
    private List<Color> colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE);
    private int colorIndex = 0;

    public Window() {
        frame = new JFrame("Drawing Application");


        drawingPanel = new DrawingPanel();
        menuPanel = new JPanel(new GridLayout(1, 4));
        menuPanel.setBackground(Color.LIGHT_GRAY);


        domButton = new JButton("House");
        presunButton = new JButton("Move");
        changeColorButton = new JButton("Next Color");
        label = new JLabel("Kreslenie");
        label.setOpaque(true);

        drawingPanel.setDrawingMode(true);

        menuPanel.add(domButton);
        menuPanel.add(changeColorButton);
        menuPanel.add(presunButton);
        menuPanel.add(label);

        domButton.addActionListener(e -> {
            drawingPanel.setDrawingMode(true);
            drawingPanel.setDraggingMode(false);
            label.setText("Draw");
        });


        presunButton.addActionListener(e -> {
            drawingPanel.setDrawingMode(false);
            drawingPanel.setDraggingMode(true);
            label.setText("Drag");
        });

        frame.setLayout(new BorderLayout());
        frame.add(menuPanel, BorderLayout.NORTH);
        frame.add(drawingPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        changeColorButton.addActionListener(e -> {
            colorIndex = (colorIndex + 1) % colors.size();
            Color selectedColor = colors.get(colorIndex);
            label.setBackground(selectedColor);
            drawingPanel.setColor(selectedColor);


        });

    }
}
