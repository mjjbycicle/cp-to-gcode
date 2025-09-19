package util;

public class CompareUtil {
    public static final double epsilon = 0.000001;
    
    public static int compare(double a, double b) {
        if (Math.abs(a - b) < epsilon) {
            return 0;
        }
        return Double.compare(a, b);
    }
}
