import org.anurag.SudokuSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuSolverTest {

    @Test
    void testBoard1() {
        char[][] board = new char[][] {
            new char[] {'5','3','.','.','7','.','.','.','.'},
            new char[] {'6','.','.','1','9','5','.','.','.'},
            new char[] {'.','9','8','.','.','.','.','6','.'},
            new char[] {'8','.','.','.','6','.','.','.','3'},
            new char[] {'4','.','.','8','.','3','.','.','1'},
            new char[] {'7','.','.','.','2','.','.','.','6'},
            new char[] {'.','6','.','.','.','.','2','8','.'},
            new char[] {'.','.','.','4','1','9','.','.','5'},
            new char[] {'.','.','.','.','8','.','.','7','9'}
        };

        int[][] intBoard = new int[9][9];

//        SudokuSolver.copyToIntBoard(board, intBoard);
//        SudokuSolver.copyToCharBoard(intBoard, board);

//        SudokuSolver.printBoard(board);

        SudokuSolver sudokuSolver = new SudokuSolver();

        // There's a clash at 0, 4
//        Assertions.assertFalse(sudokuSolver.isAssignmentValid(intBoard, 4, 4, 7));
//        // There's a clash at 4, 8
//        Assertions.assertFalse(sudokuSolver.isAssignmentValid(intBoard, 4, 4, 1));
//        // There's a clash at 7, 8
//        Assertions.assertFalse(sudokuSolver.isAssignmentValid(intBoard, 8, 6, 5));
//        // There's a clash at 8, 7
//        Assertions.assertFalse(sudokuSolver.isAssignmentValid(intBoard, 7, 6, 7));

        sudokuSolver.solveSudoku(board);
        SudokuSolver.printBoard(board);
    }

}
