import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.*;

import java.io.IOException;
import java.util.List;

public class CreasePatternTests {
    private final String axolotlFilePath = "res/axolotl.cp";
    private CreasePattern cp;

    @BeforeEach
    public void setup() throws IOException {
        cp = CreasePattern.parseCP(axolotlFilePath);
    }

    @Test
    @DisplayName("Should Correctly Normalize Points")
    void testNormalizePoints() {
        List<Point> points = List.of(
                new Point(-100, -100),
                new Point(0, 0),
                new Point(100, 100)
        );
        List<Point> expectedPoints = List.of(
                new Point(0, 0),
                new Point(0.5, 0.5),
                new Point(1, 1)
        );
        Point origin = new Point(-100, -100);
        for (int i = 0; i < points.size(); i++) {
            Assertions.assertEquals(expectedPoints.get(i), points.get(i).normalize(200, origin));
        }
    }

    @Test
    @DisplayName("Should Correctly Normalize Lines")
    void testNormalizeLines() {
        Point origin = cp.getOriginPoint(), topRight = cp.getTopRightPoint();
        double x_length = topRight.x() - origin.x();
        Point bottomRight = new Point(topRight.x(), origin.y()), topLeft = new Point(origin.x(), topRight.y());
        List<Line> lines = List.of(
                new Line(origin, topLeft, LineType.EDGE),
                new Line(origin, topRight, LineType.EDGE),
                new Line(bottomRight, topLeft, LineType.EDGE),
                new Line(bottomRight, topRight, LineType.EDGE)
        );
        List<Line> expectedLines = List.of(
                new Line(new Point(0, 0), new Point(0, 1), LineType.EDGE),
                new Line(new Point(0, 0), new Point(1, 1), LineType.EDGE),
                new Line(new Point(1, 0), new Point(0, 1), LineType.EDGE),
                new Line(new Point(1, 0), new Point(1, 1), LineType.EDGE)
        );
        for (int i = 0; i < lines.size(); i++) {
            Assertions.assertEquals(0, expectedLines.get(i).compareTo(lines.get(i).normalize(x_length, origin)));
        }
    }

    @Test
    @DisplayName("Should Correctly Identify Origin Point in Crease Pattern")
    void testIdentifyOriginPoint() {
        Point expectedOrigin = new Point(-200, -200);
        Point actualOrigin = cp.getOriginPoint();
        Assertions.assertEquals(expectedOrigin.x(), actualOrigin.x(), CompareUtil.epsilon, "origin x comparison failed");
        Assertions.assertEquals(expectedOrigin.y(), actualOrigin.y(), CompareUtil.epsilon, "origin y comparison failed");
    }

    @Test
    @DisplayName("Should Correctly Identify Top Right Point in Crease Pattern")
    void testIdentifyTopRightPoint() {
        Point expectedTopRight = new Point(200, 200);
        Point actualTopRight = cp.getTopRightPoint();
        Assertions.assertEquals(expectedTopRight.x(), actualTopRight.x(), CompareUtil.epsilon, "top right x comparison failed");
        Assertions.assertEquals(expectedTopRight.y(), actualTopRight.y(), CompareUtil.epsilon, "top right y comparison failed");
    }
}
