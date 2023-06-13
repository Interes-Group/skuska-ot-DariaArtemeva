package sk.stuba.fei.uim.oop.shapes;
import java.awt.*;



public class Dom extends Shape {


    public Dom(int x, int y, int height, int width, Color color) {
        super(x, y, width, height, color);

    }

    @Override
    public boolean contains(Point p) {
        Rectangle base = new Rectangle(getX(), getY() + getHeight() / 2, getWidth(), getHeight() / 2);
        if (base.contains(p)) {
            return true;
        }

        Point a = new Point(getX(), getY() + getHeight() / 2);
        Point b = new Point(getX() + getWidth() / 2, getY());
        Point c = new Point(getX() + getWidth(), getY() + getHeight() / 2);
        if (pointInTriangle(p, a, b, c)) {
            return true;
        }

        return false;
    }

    public void update(int x, int y, int height, int width, Color color) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setColor(color);
    }


    public void draw(Graphics g) {
        g.setColor(this.getColor());

        g.fillRect(this.getX(), this.getY() + this.getHeight()/2, this.getWidth(), this.getHeight()/2);

        int[] xPoints = {this.getX(), this.getX() + this.getWidth()/2, this.getX() + this.getWidth()};
        int[] yPoints = {this.getY() + this.getHeight()/2, this.getY(), this.getY() + this.getHeight()/2};

        g.fillPolygon(xPoints, yPoints, 3);
    }
    private boolean pointInTriangle(Point s, Point a, Point b, Point c) {
        int as_x = s.x-a.x;
        int as_y = s.y-a.y;

        boolean s_ab = (b.x-a.x)*as_y-(b.y-a.y)*as_x > 0;

        if(((c.x-a.x)*as_y-(c.y-a.y)*as_x > 0) == s_ab) return false;

        if(((c.x-b.x)*(s.y-b.y)-(c.y-b.y)*(s.x-b.x) > 0) != s_ab) return false;

        return true;
    }

}






