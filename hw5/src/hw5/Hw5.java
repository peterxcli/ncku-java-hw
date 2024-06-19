package hw5;

import java.util.Scanner;

public class Hw5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        // create a 2D array with char type
        String[][] arr = new String[n][m];
        Boolean[][] visited = new Boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = Boolean.FALSE; // Initialize to false instead of null
            }
        }

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
                visited[i][j] = true;
                result = findString(arr, visited, i, j, 0, targetString);
                visited[i][j] = false;
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

    public static boolean findString(String[][] arr, Boolean[][] visited, int x, int y, int current_len,
            String targetString) {
        // if the current length is equal to the length of the target string, print the
        // result
        // visited[x][y] = true;
        if (current_len == targetString.length()) {
            // visited[x][y] = false;
            return true;
        }

        // if the current length is not equal to the length of the target string, check
        // the next character
        if (x < 0 || x >= arr.length || y < 0 || y >= arr[0].length) {
            // visited[x][y] = false;
            return false;
        }
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        if (arr[x][y].equals(targetString.substring(current_len, current_len + 1)) == false) {
            // visited[x][y] = false;
            return false;
        }
        for (int[] direction : directions) {
            int new_x = x + direction[0];
            int new_y = y + direction[1];
            if (new_x >= 0 && new_x < arr.length && new_y >= 0 && new_y < arr[0].length) {
                visited[new_x][new_y] = true;
                Boolean ret = findString(arr, visited, new_x, new_y, current_len + 1, targetString);
                visited[new_x][new_y] = false;
                if (ret.equals(true)) {
                    return true;
                }
            }
        }
        return false;
    }
}
