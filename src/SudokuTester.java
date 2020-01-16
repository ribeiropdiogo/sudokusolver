import java.io.IOException;
import java.util.Scanner;

public class SudokuTester {
    private static long start, finish, elapsed;

    public static int menu(){
        Scanner in = new Scanner(System.in);

        System.out.println("    Sudoku Solver Testing");
        System.out.println();
        System.out.println("  1 - Backtracking Algorithm");
        System.out.println("  0 - Exit");
        System.out.println();
        System.out.print(" Option: ");

        int num = in.nextInt();

        return num;
    }

    public static void printTime(long time){
        System.out.println("The algorithm solved the Sudoku in "+time+"ms");
    }

    public static void clear() throws IOException {
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

    public static void main(String[] args) throws IOException {
        int opt = 0;
        int[][] grid = {
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

        Sudoku s = new Sudoku();

        while (opt != -1){
            s.setGrid(grid);
            SudokuSolver ss = new SudokuSolver(s);
            opt = menu();

            if (opt == 1) {
                clear();

                start = System.currentTimeMillis();
                ss.solveByBacktracking();
                finish = System.currentTimeMillis();
                elapsed = finish - start;

                s.setFinalgrid(ss.getGrid());
                s.printGrid();

                System.out.println();
                printTime(elapsed);

                if (s.isValid())
                    System.out.println("Grid is valid");
                else
                    System.out.println("Grid is not valid");

                System.in.read();
                clear();
            } else if (opt == 0){
                opt = -1;
            }
        }
    }
}
