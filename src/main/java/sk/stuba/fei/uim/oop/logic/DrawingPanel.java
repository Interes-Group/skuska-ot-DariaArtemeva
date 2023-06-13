package sk.stuba.fei.uim.oop.logic;

import sk.stuba.fei.uim.oop.shapes.Dom;
import sk.stuba.fei.uim.oop.shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
    private Point startDragPoint;
    private boolean drawingMode;
    private Color color;
    private boolean draggingMode;
    private sk.stuba.fei.uim.oop.shapes.Shape draggingShape;
    private Point previousPoint;

    private sk.stuba.fei.uim.oop.shapes.Shape currentShape;
    private ArrayList<sk.stuba.fei.uim.oop.shapes.Shape> shapes = new ArrayList<>();

    public DrawingPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void setDrawingMode(boolean drawingMode) {
        this.drawingMode = drawingMode;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (sk.stuba.fei.uim.oop.shapes.Shape shape : shapes) {
            shape.draw(g);
        }
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }

    public void setDraggingMode(boolean draggingMode) {
        this.draggingMode = draggingMode;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startDragPoint = e.getPoint();
        if (draggingMode) {
            for (Shape shape : shapes) {
                if (shape.contains(startDragPoint)) {
                    draggingShape = shape;
                    previousPoint = startDragPoint;
                    break;
                }
            }
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggingMode) {
            if (draggingShape != null && previousPoint != null) {
                Point currentPoint = e.getPoint();
                draggingShape.move(currentPoint.x - previousPoint.x, currentPoint.y - previousPoint.y);
                previousPoint = currentPoint;
                repaint();
            }
        } else if (drawingMode) {
            int x = Math.min(startDragPoint.x, e.getX());
            int y = Math.min(startDragPoint.y, e.getY());
            int width = Math.abs(startDragPoint.x - e.getX());
            int height = Math.abs(startDragPoint.y - e.getY());

            if (currentShape == null) {
                currentShape = new Dom(x, y, height, width, this.color);
            } else {
                ((Dom) currentShape).update(x, y, height, width, this.color);
            }
            repaint();
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if (draggingMode) {
            if (draggingShape != null) {
                shapes.add(draggingShape);
                draggingShape = null;
            }
            previousPoint = null;
        } else {
            if (currentShape != null) {
                shapes.add(currentShape);
                currentShape = null;
            }
        }
    }





    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
