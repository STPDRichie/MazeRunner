import java.util.LinkedList;

public class MazeRunner {
    public MazeState[][] maze;

    private final int rowCount;
    private final int columnCount;

    public MazeRunner(String[] stringMaze) {
        rowCount = stringMaze.length;
        columnCount = stringMaze[0].split(" ").length;

        maze = new MazeState[rowCount][columnCount];
        for (var row = 0; row < stringMaze.length; row++) {
            var mazeRow = stringMaze[row].split(" ");

            for (var column = 0; column < columnCount; column++)
                maze[row][column] = MazeState.values()[Integer.parseInt(mazeRow[column])];
        }
    }

    public String[] Run(MazePoint start, MazePoint end) {
        if (maze[start.x][start.y] == MazeState.Wall || maze[end.x][end.y] == MazeState.Wall)
            return new String[0];

        SinglyLinkedList requiredPath = null;
        var requiredPathLength = Integer.MAX_VALUE;

        var paths = new LinkedList<SinglyLinkedList>();

        paths.add(new SinglyLinkedList(start, null));

        while (!paths.isEmpty()) {
            var path = paths.pop();
            var currentPoint = path.point;

            if (isPointIncorrect(new MazePoint(currentPoint.x, currentPoint.y)))
                continue;

            maze[currentPoint.x][currentPoint.y] = MazeState.Visited;

            for (var x = -1; x <= 1; x++)
                for (var y = -1; y <= 1; y++) {
                    if (Math.abs(x) + Math.abs(y) != 2 && !(x == 0 && y == 0)) {
                        if (currentPoint.x + x == end.x && currentPoint.y + y == end.y) {
                            var currentPath = new SinglyLinkedList(
                                    new MazePoint(currentPoint.x + x, currentPoint.y + y), path);
                            if (currentPath.length < requiredPathLength) {
                                requiredPathLength = currentPath.length;
                                requiredPath = currentPath;
                            }
                        }
                        else if (maze[currentPoint.x + x][currentPoint.y + y] == MazeState.Empty)
                            paths.push(new SinglyLinkedList(
                                    new MazePoint(currentPoint.x + x, currentPoint.y + y), path));
                    }
                }
        }
        if (requiredPath == null)
            return new String[0];

        return pathToStringArray(requiredPath);
    }

    private boolean isPointIncorrect(MazePoint point) {
        return point.x < 0 || point.x > rowCount ||
                point.y < 0 || point.y > columnCount ||
                maze[point.x][point.y] != MazeState.Empty;
    }

    private String[] pathToStringArray(SinglyLinkedList path) {
        var stringPath = new String[path.length];

        for (var i = path.length - 1; i >= 0; i--) {
            var move = new MazePoint(path.path[i].x, path.path[i].y);
            stringPath[i] = MazePoint.toString(move);
        }

        return stringPath;
    }
}
