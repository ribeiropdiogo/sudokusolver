package Sudoku;

/**
 * The Sudoku class implements all functions necessary to represent the Sudoku and to check if it is valid.
 *
 * @author  Diogo Ribeiro
 * @version 1.0
 * @since   2020-01-16
 */

public class Sudoku {
    private int[][] finalgrid;       // grid with the solution
    private int[][] grid;                   // grid with the initial solution
    private static final int size = 9;      // grid size
    private static final int empty = 0;     // default empty value

    public Sudoku() {
        this.grid = new int[size][size];
        this.finalgrid = new int[size][size];
    }

    /**
     * This method sets a 2D int grid as the grid.
     * @param grid Contains the grid we want to associate
     */
    public void setGrid(int[][] grid) {
        this.grid = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                this.grid[i][j] = grid[i][j];
    }

    /**
     * This method sets a 2D int grid as the final grid.
     * @param grid Contains the grid we want to associate
     */
    public void setFinalgrid(int[][] grid) {
        this.finalgrid = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                this.finalgrid[i][j] = grid[i][j];
    }

    /**
     * This method gets a 2D int grid as the grid.
     * @return The Sudoko grid
     */
    public int[][] getGrid() {
        return this.grid;
    }

    /**
     * This method processes an int array by setting all elements to 0.
     * @param array Contains the array we want to reset
     */
    private void resetArray(int[] array) {
        for (int z = 0; z < size; z++)
            array[z] = 0;
    }

    /**
     * This method return true if all elements of the array are 1 or false if one or more elements are different from 1.
     * @param array Contains the array we want to check
     * @return Returns true if all elements are 1 or false otherwise.
     */
    private boolean all1s(int[] array) {
        boolean r = true;

        for (int s : array)
            if (s != 1)
                r = false;

        return r;
    }

    /**
     * This method gets the index of the cell in the grid.
     * @param i The row
     * @param j The column
     * @param opt The option row/column
     * @return Returns the index of the cell.
     */
    private int getIndex(int i, int j, String opt) {
        if (opt.equals("row")){
            return this.finalgrid[i][j] - 1;
        } else {
            return this.finalgrid[j][i] - 1;
        }
    }

    /**
     * This method checks if all columns or rows of the matrix contain all numbers from 1 to 9 and if there are no repeated numbers.
     * @return Returns true if all Columns are valid or false otherwise.
     */
    private boolean validRowsColumns(String opt) {
        boolean r = true;
        int[] aux = new int[size];

        resetArray(aux);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int x = getIndex(i,j,opt);
                aux[x]++;
            }

            r = all1s(aux);
            resetArray(aux);
        }

        return r;
    }

    /**
     * This method checks if a subsquare of the matrix (3x3) contains all numbers from 1 to 9 and has no repeated numbers.
     * @param subsquare Contains the subsquare we want to check
     * @return Returns true if the subsquare is valid or false otherwise.
     */
    private boolean validSubSquare(int[][] subsquare) {
        boolean r = true;
        int[] aux = new int[size];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = subsquare[i][j] - 1;
                aux[x]++;
            }
            r = all1s(aux);
        }
        return r;
    }

    /**
     * This method fills the subsquare with the values from the grid.
     * @param i Row
     * @param j Column
     * @param subsquare Subsquare
     */
    private void fillSubSquare(int i, int j, int[][]subsquare) {
        for (int x = 0; x < size / 3; x++)
            for (int y = 0; y < size / 3; y++)
                subsquare[x][y] = finalgrid[i + x][j + y];
    }

    /**
     * This method divides the matrix into subquares (3x3) and checks if all are valid.
     * @return Returns true if all subsquares are valid or false otherwise.
     */
    private boolean validSquare() {
        boolean r = true;
        int[][] aux = new int[size][size];


        for (int i = 0; i < size; i = i + 3)
            for (int j = 0; j < size; j = j + 3) {
                int[][] subsquare = new int[3][3];
                fillSubSquare(i,j,subsquare);
                r = validSubSquare(subsquare);
            }

        return r;
    }

    /**
     * This method checks if the matrix respects all conditions.
     * @return Returns true if the matrix is valid or false otherwise.
     */
    public boolean isValid() {
        return this.validRowsColumns("row") && this.validRowsColumns("column") && this.validSquare();
    }

    /**
     * This method prints the matrix of the Sudoku to the terminal output.
     */
    public void printGrid() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                System.out.print(finalgrid[row][column] + " ");
            }
            System.out.println();
        }
    }
}
