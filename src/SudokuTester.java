import Sudoku.Backtracking.SudokuBacktracking;
import Sudoku.Sudoku;

import java.io.IOException;
import java.util.Scanner;

public class SudokuTester {
    private static long start, finish, elapsed;
    private static int[][] grid = {
            {9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0},
    };

    /**
     * This method prints the menu.
     */
    private static int menu(){
        Scanner in = new Scanner(System.in);

        System.out.println("    Sudoku Solver Testing");
        System.out.println();
        System.out.println("  1 - Backtracking Algorithm");
        System.out.println("  0 - Exit");
        System.out.println();
        System.out.print(" Option: ");

        return in.nextInt();
    }

    /**
     * This method prints the time elapsed.
     * @param time The time to print
     */
    private static void printTime(long time){
        System.out.println();
        System.out.println("The algorithm solved the Sudoku in "+time+"ms");
    }

    /**
     * This method clears the screen output.
     */
    private static void clear() {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }

        System.out.print("\033[H\033[2J");
    }

    /**
     * This method print the press Enter to continue message.
     */
    private static void pressENTER() throws IOException {
        System.out.println("Press ENTER to continue.");
        System.in.read();
        clear();
    }

    /**
     * This method checks if the solution of the Sudoku is valid.
     */
    private static void checkValid(Sudoku s) {
        if (s.isValid())
            System.out.println("The Sudoku solution is valid!");
        else
            System.out.println("The Sudoku solution is not valid!");
    }

    /**
     * This solves the Sudoku using the Backtracking Algorithm.
     * @param s The Sudoku to solve
     */
    private static void solveByBacktracking(Sudoku s) throws IOException{
        SudokuBacktracking sb = new SudokuBacktracking(s);
        clear();

        start = System.currentTimeMillis();
        sb.solveByBacktracking();
        finish = System.currentTimeMillis();
        elapsed = finish - start;

        s.setFinalgrid(sb.getGrid());
        s.printGrid();

        printTime(elapsed);
        checkValid(s);
        pressENTER();
    }

    public static void main(String[] args) throws IOException {
        clear();

        Sudoku s = new Sudoku();
        int opt = 0;

        while (opt != -1){
            s.setGrid(grid);
            opt = menu();

            if (opt == 1) {
                solveByBacktracking(s);
            } else if (opt == 0){
                opt = -1;
            }
        }
    }
}
