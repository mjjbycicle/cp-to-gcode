package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CreasePattern {
    private final Map<LineType, List<Line>> lines;
    private final Set<Point> borderPoints;
    
    private CreasePattern() {
        lines = new HashMap<>();
        for (LineType lineType : LineType.values()) {
            List<Line> lineList = new ArrayList<>();
            lines.put(lineType, lineList);
        }
        borderPoints = new TreeSet<>();
    }
    
    public static CreasePattern parseCP(String filePath) throws FileNotFoundException {
        CreasePattern cp = new CreasePattern();
        Scanner sc = new Scanner(new File(filePath));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Scanner lineScanner = new Scanner(line);
            int type = lineScanner.nextInt();
            LineType lineType = LineType.getLineType(type);
            double x1 = lineScanner.nextDouble(), y1 = lineScanner.nextDouble();
            double x2 = lineScanner.nextDouble(), y2 = lineScanner.nextDouble();
            Point p1 = new Point(x1, y1), p2 = new Point(x2, y2);
            if (lineType == LineType.EDGE) {
                cp.borderPoints.add(p1);
                cp.borderPoints.add(p2);
            }
            Line l = new Line(p1, p2, lineType);
            cp.lines.get(lineType).add(l);
        }
        sc.close();
        return cp;
    }
    
    public Point getOriginPoint() {
        List<Point> points = borderPoints.stream().toList();
        return points.getFirst();
    }
    
    public Point getTopRightPoint() {
        List<Point> points = borderPoints.stream().toList();
        return points.getLast();
    }
    
    public CreasePattern normalize() {
        Point origin = getOriginPoint();
        Point topRight = getTopRightPoint();
        double x_diff = topRight.x() - origin.x();
        CreasePattern cp = new CreasePattern();
        for (Map.Entry<LineType, List<Line>> entry : lines.entrySet()) {
            List<Line> lineList = entry.getValue();
            List<Line> newLineList = new ArrayList<>();
            for (Line line : lineList) {
                newLineList.add(line.normalize(x_diff, origin));
            }
            cp.lines.put(entry.getKey(), newLineList);
        }
        return cp;
    }
}
