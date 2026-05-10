import java.util.Scanner;

public class SudokuSolver {

    static final int SIZE = 9;

    // Function to print Sudoku board
    static void printBoard(int[][] board) {

        System.out.println("\nSolved Sudoku Puzzle:\n");

        for (int row = 0; row < SIZE; row++) {

            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }

            System.out.println();
        }
    }

    // Check if number is safe in row
    static boolean isRowSafe(int[][] board, int row, int num) {

        for (int col = 0; col < SIZE; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }

        return true;
    }

    // Check if number is safe in column
    static boolean isColSafe(int[][] board, int col, int num) {

        for (int row = 0; row < SIZE; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }

        return true;
    }

    // Check if number is safe in 3x3 box
    static boolean isBoxSafe(int[][] board,
                             int boxStartRow,
                             int boxStartCol,
                             int num) {

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                if (board[row + boxStartRow][col + boxStartCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Check if safe to place number
    static boolean isSafe(int[][] board,
                          int row,
                          int col,
                          int num) {

        return isRowSafe(board, row, num)
                && isColSafe(board, col, num)
                && isBoxSafe(board,
                row - row % 3,
                col - col % 3,
                num);
    }

    // Solve Sudoku using Backtracking
    static boolean solveSudoku(int[][] board) {

        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find empty cell
        for (int i = 0; i < SIZE; i++) {

            for (int j = 0; j < SIZE; j++) {

                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }

            if (!isEmpty) {
                break;
            }
        }

        // No empty cell left
        if (isEmpty) {
            return true;
        }

        // Try numbers 1 to 9
        for (int num = 1; num <= 9; num++) {

            if (isSafe(board, row, col, num)) {

                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true;
                }

                // Backtrack
                board[row][col] = 0;
            }
        }

        return false;
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] board = new int[9][9];

        System.out.println("Enter Sudoku Puzzle (use 0 for empty cells):");

        // Input Sudoku puzzle
        for (int row = 0; row < SIZE; row++) {

            for (int col = 0; col < SIZE; col++) {

                board[row][col] = sc.nextInt();
            }
        }

        // Solve Sudoku
        if (solveSudoku(board)) {
            printBoard(board);
        } else {
            System.out.println("No Solution Exists!");
        }

        sc.close();
    }
}