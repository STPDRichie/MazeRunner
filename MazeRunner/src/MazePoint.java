public class MazePoint {
    public int x;
    public int y;

    public MazePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static String toString(MazePoint point) {
        return point.x + " " + point.y;
    }
}
