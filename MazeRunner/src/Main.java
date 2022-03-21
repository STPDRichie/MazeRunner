import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File input = new File("input.txt");
            Scanner scanner = new Scanner(input);

            var rowCount = Integer.parseInt(scanner.nextLine());
            var columnCount = Integer.parseInt(scanner.nextLine());

            var stringMaze = new String[rowCount];
            for (var row = 0; row < rowCount; row++)
                stringMaze[row] = scanner.nextLine();

            var mazeRunner = new MazeRunner(stringMaze);

            var start = scanner.nextLine().split(" ");
            var end = scanner.nextLine().split(" ");

            var resultArray = mazeRunner.run(
                    new MazePoint(Integer.parseInt(start[0]) - 1, Integer.parseInt(start[1]) - 1),
                    new MazePoint(Integer.parseInt(end[0]) - 1, Integer.parseInt(end[1]) - 1));

            FileWriter writer = new FileWriter("output.txt");
            if (resultArray.length != 0) {
                writer.write("Y\n");
                writer.write(String.join("\n", resultArray));
            }
            else
                writer.write("N");

//            writer.write(String.join("\n", resultArray));
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Writing failed...");
            e.printStackTrace();
        }
    }
}
