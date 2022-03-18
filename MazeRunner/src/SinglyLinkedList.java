public class SinglyLinkedList {
    public final MazePoint point;
    public final SinglyLinkedList previous;
    public final int length;

    public final MazePoint[] path;

    public SinglyLinkedList(MazePoint point, SinglyLinkedList previous) {
        this.point = point;
        this.previous = previous;
        this.length = (previous != null) ? previous.length + 1 : 1;

        if (previous == null)
            path = new MazePoint[] {new MazePoint(point.x + 1, point.y + 1)};
        else {
            path = new MazePoint[length];
            for (var i = 0; i < previous.length; i++)
                path[i] = previous.path[i];
            path[length - 1] = new MazePoint(point.x + 1, point.y + 1);
        }
    }
}
