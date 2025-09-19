package util;

public enum LineType {
    EDGE,
    MOUNTAIN,
    VALLEY;
    
    public static LineType getLineType(int n) {
        return LineType.values()[n - 1];
    }
}
