package util;

public record Line(Point a, Point b, LineType type) implements Comparable<Line> {
    public Line normalize(double length, Point origin) {
        return new Line(a.normalize(length, origin), b.normalize(length, origin), type);
    }

    public double length() {
        return Math.hypot(a.x() - b.x(), a.y() - b.y());
    }


    @Override
    public int compareTo(Line o) {
        if (a.compareTo(o.a) == 0 && b.compareTo(o.b) == 0) return 0;
        if (a.compareTo(o.b) == 0 && b.compareTo(o.a) == 0) return 0;
        if (CompareUtil.compare(length(), o.length()) == 0) return 1;
        return CompareUtil.compare(length(), o.length());
    }
}
