package org.anurag.sudokusolver;

public class SudokuSolver {

    public static void copyToIntBoard(char[][] src, int[][] dest) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[0].length; j++) {
                if (src[i][j] != '.') {
                    dest[i][j] = src[i][j] - '0';
                } else {
                    dest[i][j] = 0;
                }
            }
        }
    }

    public static void copyToCharBoard(int[][] src, char[][] dest) {
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[0].length; j++) {
                if (src[i][j] != 0) {
                    dest[i][j] = (char) ((char) Math.abs(src[i][j]) + '0');
                } else {
                    dest[i][j] = '.';
                }
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (char[] chars : board) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf("%c", chars[j]);

                if (j < board[0].length - 1) {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    public boolean isAssignmentValid(int[][]board, int row, int col, int value) {
        // Check if there's a clash in this row
        for (int j = 0; j < board[0].length; j++) {
            if (j == col) {
                continue;
            }

            if (Math.abs(board[row][j]) == Math.abs(value)) {
                // Clash.
                return false;
            }
        }

        // Check if there's a clash in this column
        for (int i = 0; i < board.length; i++) {
            if (i == row) {
                continue;
            }

            if (Math.abs(board[i][col]) == Math.abs(value)) {
                // Clash.
                return false;
            }
        }

        // Check if there's a clash in this 3 x 3 block
        int blockRow = (row / 3) * 3;
        int blockCol = (col / 3) * 3;

        for (int i = blockRow; i < blockRow + 3; i++) {
            for (int j = blockCol; j < blockCol + 3; j++) {
                if (i == row && j == col) {
                    continue;
                }

                if (Math.abs(board[i][j]) == Math.abs(value)) {
                    // Clash.
                    return false;
                }
            }
        }

        return true;
    }

    private boolean backtrack(int[][]board, int row, int col) {
        if (row >= board.length || col >= board[0].length) {
            return true;
        }

        int tryRow = -1, tryCol = -1;

        for (int j = col; j < board[0].length; j++) {
            if (board[row][j] == 0) {
                // Found the next coordinates we can try to change
                tryRow = row;
                tryCol = j;

                break;
            }
        }

        if (tryRow == -1) {
            // Haven't found coordinates in starting row. Let's start exploring the following rows.
            for (int i = row + 1; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 0) {
                        tryRow = i;
                        tryCol = j;

                        break;
                    }
                }

                if (tryRow != -1) {
                    break;
                }
            }
        }

        if (tryRow == -1) {
            // No more candidates found to try. We're done.
            return true;
        }

        // Try here
        for (int i = 1; i <= 9; i++) {
            if (!isAssignmentValid(board, tryRow, tryCol, i)) {
                continue;
            }

            board[tryRow][tryCol] = -i;

            if (tryCol < board[0].length - 1) {
                if (backtrack(board, tryRow, tryCol + 1)) {
                    return true;
                }
            } else {
                if (backtrack(board, tryRow + 1, 0)) {
                    return true;
                }
            }

            board[tryRow][tryCol] = 0;
        }

        // No valid assignment found.
        return false;
    }

    public void solveSudoku(char[][] board) {
        // Should be 9 x 9, but just using board dimensions
        int[][] intBoard = new int[board.length][board[0].length];

        copyToIntBoard(board, intBoard);

        if(backtrack(intBoard, 0, 0)) {
            System.out.println("Solved");
        } else {
            System.out.println("Not solved");
        }

        copyToCharBoard(intBoard, board);
    }

}
