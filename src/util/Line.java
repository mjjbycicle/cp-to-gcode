package util;

public class Line {
    public final Point a, b;

    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Line normalize(double length, Point origin) {
        return new Line(a.normalize(length, origin), b.normalize(length, origin));
    }
}
