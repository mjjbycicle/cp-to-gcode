package util;

public record Point(double x, double y) implements Comparable<Point> {
    public Point normalize(double length, Point origin) {
        return new Point(
                (x - origin.x) / length,
                (y - origin.y) / length
        );
    }

    @Override
    public int compareTo(Point o) {
        if (CompareUtil.compare(this.x, o.x) != 0) return CompareUtil.compare(this.x, o.x);
        return CompareUtil.compare(this.y, o.y);
    }
}
