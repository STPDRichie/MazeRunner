import java.util.LinkedList;

public class MazeRunner {
    public MazeState[][] maze;

    private int rowCount;
    private int columnCount;

    public MazeRunner() {}

    public MazeRunner(String[] stringMaze) {
        rowCount = stringMaze.length;
        columnCount = stringMaze[0].split(" ").length;

        maze = new MazeState[rowCount][columnCount];
        for (var row = 0; row < stringMaze.length; row++) {
            var mazeRow = stringMaze[row].split(" ");

            for (var column = 0; column < columnCount; column++)
                maze[row][column] = MazeState.valueOf(mazeRow[column]);
        }
    }

    public String[] Run(MazePoint start, MazePoint end) {
        if (maze[start.x][start.y] == MazeState.Wall || maze[end.x][end.y] == MazeState.Wall)
            return new String[] {"N"};

        var stack = new LinkedList<MazePoint>();
        stack.push(start);

        while (!stack.isEmpty()) {
            var currentPoint = stack.pop();

            if (currentPoint.x < 0 || currentPoint.x > rowCount ||
                    currentPoint.y < 0 || currentPoint.y > columnCount)
                continue;
            if (maze[currentPoint.x][currentPoint.y] != MazeState.Empty)
                continue;

            maze[currentPoint.x][currentPoint.y] = MazeState.Visited;

            for (var x = -1; x <= 1; x++)
                for (var y = -1; y <= 1; y++)
                    if (x == 0 || y == 0)
                        stack.push(new MazePoint(currentPoint.x + x, currentPoint.y + y));
        }
    }
}
