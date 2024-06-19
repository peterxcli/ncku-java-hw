package hw6;

import java.util.Scanner;

public class Hw6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boardState = scanner.next();
        scanner.close();

        // Convert the input string to a 2D array for easier manipulation
        char[][] board = new char[3][3];
        int xCount = 0, oCount = 0;
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = boardState.charAt(i);
            if (board[i / 3][i % 3] == 'X')
                xCount++;
            if (board[i / 3][i % 3] == 'O')
                oCount++;
        }

        if (Math.abs(xCount - oCount) > 1 || xCount < oCount) {
            System.out.println("invalid"); // Rule 1 violation
            return;
        }

        boolean xWins = checkWin(board, 'X');
        boolean oWins = checkWin(board, 'O');

        if (xWins && oWins) {
            System.out.println("invalid"); // Rule 2 violation
            return;
        }

        if ((xWins && xCount == oCount) || (oWins && xCount > oCount)) {
            System.out.println("invalid"); // Rule 3 violation
            return;
        }

        System.out.println("valid"); // If none of the above, it's a valid state
    }

    // Utility method to check if a player has won
    private static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }
}
