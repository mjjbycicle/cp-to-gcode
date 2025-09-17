package util;

public class Point {
    public final double x, y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point normalize(double length, Point origin) {
        return new Point(
                (x - origin.x) / length,
                (y - origin.y) / length
        );
    }
}
