package hw3;
import java.util.Scanner;

import hw4.Point;

public class Hw3 {
    public static void main(String[] args) {
        // Original Main Point (x,y)
        // Move
        // Rotate 1th
        // Rotate 2th
        // Rotate 3th
        // Rotate 4th
        // Manhattan Distance 2.0
        // Chebyshev Distance
        Scanner scanner = new Scanner(System.in);
        Point mainPoint = new Point();
        mainPoint.Set(scanner.nextInt(), scanner.nextInt());
        System.out.printf("%d %d\n", mainPoint.RetrieveVertical(), mainPoint.RetrieveHorizontal());
        Point otherPoint = new Point();
        otherPoint.Set(scanner.nextInt(), scanner.nextInt());
        mainPoint.Move(scanner.nextInt(), scanner.nextInt());
        System.out.printf("%d %d\n", mainPoint.RetrieveVertical(), mainPoint.RetrieveHorizontal());
        for (int i = 0; i < 4; i++) {
            mainPoint.Rotate();
            System.out.printf("%d %d\n", mainPoint.RetrieveVertical(), mainPoint.RetrieveHorizontal());
        }
        System.out.println(mainPoint.calculateManhattanDistance(otherPoint));
        System.out.println(mainPoint.ChebyshevDistance(otherPoint));

        scanner.close();
    }
}
