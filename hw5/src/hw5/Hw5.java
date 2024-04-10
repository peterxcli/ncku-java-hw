package hw5;

import java.util.Scanner;

public class Hw5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        // create a 2D array with char type
        String[][] arr = new String[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.next();
            }
        }
        String targetString = scanner.next();

        // find the target string in the 2D array
        boolean result = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result = findString(arr, i, j, 0, targetString);
                if (result) {
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        System.out.println(result);

        scanner.close();
    }

    public static boolean findString(String[][] arr, int x, int y, int current_len, String targetString) {
        // if the current length is equal to the length of the target string, print the result
        if (current_len == targetString.length()) {
            return true;
        }

        // if the current length is not equal to the length of the target string, check the next character
        if (x < 0 || x >= arr.length || y < 0 || y >= arr[0].length) {
            return false;
        }

        if (arr[x][y].equals(targetString.substring(current_len, current_len + 1))) {
            return findString(arr, x + 1, y, current_len + 1, targetString) ||
                    findString(arr, x - 1, y, current_len + 1, targetString) ||
                    findString(arr, x, y + 1, current_len + 1, targetString) ||
                    findString(arr, x, y - 1, current_len + 1, targetString);
        } else {
            return false;
        }
    }
}
