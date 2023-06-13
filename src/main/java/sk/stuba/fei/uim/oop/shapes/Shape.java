package sk.stuba.fei.uim.oop.shapes;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class Shape {

    @Getter @Setter
    int x;
    @Setter @Getter
    int y;
    @Setter @Getter
    int width;
    @Setter @Getter
    int height;
    @Setter @Getter
    Color color;


    public Shape(int x, int y, int width, int height, Color color) {

        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public abstract boolean contains(Point p);

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract void draw(Graphics g);
}
