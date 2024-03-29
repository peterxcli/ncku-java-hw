package hw4;

import java.util.Scanner;

public class Hw4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        // Matrix y = new Matrix(n, 1);
        Matrix A = new Matrix(n, n+1);
        // input m line (m is unknown), each line has n+1 elements, the last element is
        // y
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                int temp = scanner.nextInt();
                if (temp == -999) {
                    break;
                }
                // if (j == n) {
                //     y.Set(i, 0, temp);
                // } else {
                A.Set(i, j, temp);
                // }
            }
        }
        // If there is a unique solution, output "The only solution". If there are
        // multiple solutions, output "Multiple solutions". If there is no solution,
        // output "No solution".
        A.GaussianElimination();
        // A.Print();
        boolean noSolution = false;
        boolean infiniteSolutions = false;

        // Check for no solution
        for (int i = 0; i < n; i++) {
            boolean allZeros = true;
            for (int j = 0; j < n+1; j++) {
                if (A.Get(i, j) != 0) {
                    allZeros = false;
                    break;
                }
            }
            if (allZeros) {
                infiniteSolutions = true;
                break;
            }
        }

        // Check for infinite solutions
        int rank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A.Get(i, j) != 0) {
                    rank++;
                    break;
                }
            }
        }
        if (rank < n) {
            noSolution = true;
        }

        // Output solution type
        if (infiniteSolutions) {
            System.out.println("Infinite solutions");
        }
        else if (noSolution) {
            System.out.println("No solution");
        }
        else {
            System.out.println("The only solution");
        }

        scanner.close();
    }
}
