/**
 * The SudokuSolver class implements solves Sudoku using different algorithms and uses some measures for performance.
 *
 * @author  Diogo Ribeiro
 * @version 1.0
 * @since   2020-01-16
 */

public class SudokuSolver {
    private Sudoku s;
    private int[][] grid;
    private final static int size = 9;

    public SudokuSolver(Sudoku s) {
        this.s = s;
        this.grid = s.getGrid();
    }

    public int[][] getGrid() {
        return this.grid;
    }

    /**
     * This method clear the grid of the solver.
     */
    public void clear(){
        this.grid = new int[size][size];
    }

    /**
     * This method checks if  the row contains the number.
     * @param row The row to check
     * @param number The number to check
     * @return True if the row contains the number and false otherwise
     */
    private boolean isInRow(int row, int number) {
        for (int i = 0; i < size; i++)
            if (grid[row][i] == number)
                return true;

        return false;
    }

    /**
     * This method checks if  the row contains the number.
     * @param column The row to check
     * @param number The number to check
     * @return True if the row contains the number and false otherwise
     */
    private boolean isInCol(int column, int number) {
        for (int i = 0; i < size; i++)
            if (grid[i][column] == number)
                return true;

        return false;
    }

    /**
     * This method checks if the subsquare contains the number.
     * @param row The row were the subsquare begins
     * @param column The column where the subsquare begins
     * @param number The number to check
     * @return True if the subsquare contains the number and false otherwise
     */
    private boolean isInSubsquare(int row, int column, int number) {
        int r = row - row % 3;
        int c = column - column % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (grid[i][j] == number)
                    return true;

        return false;
    }

    /**
     * This method checks if the number added is a valid addition.
     * @param row The row were the number is added
     * @param column The column where the number is added
     * @param number The number to check
     * @return True if the added number is valid and false otherwise
     */
    private boolean isValid(int row, int column, int number) {
        return !isInRow(row, number) && !isInCol(column, number) && !isInSubsquare(row, column, number);
    }

    /**
     * This method checks if the number added is a valid addition.
     * @param row The row were the number is added
     * @param column The column where the number is added
     * @return True if the added number is valid and false otherwise
     */
    private boolean tryAllPossible(int row, int column) {
        for (int number = 1; number <= size; number++) {
            if (isValid(row, column, number)) {
                // If the the number is valid we add it to the grid
                grid[row][column] = number;

                if (solveByBacktracking()) {
                    // The next step is to call recursively the Backtracking algorithm
                    return true;
                } else {
                    // In case of the number generating an impossible solution we replace it by 0
                    grid[row][column] = 0;
                }
            }
        }
        return false;
    }

    /**
     * This method solves the Sudoko using the backtracking algorithm.
     * @return True if the added number is valid and false otherwise
     */
    public boolean solveByBacktracking() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                // The first step is to search for an empty cell
                if (grid[row][column] == 0) {
                    // The second step is to try all possible numbers
                    return tryAllPossible(row,column);
                }
            }
        }
        return true;
    }

}
