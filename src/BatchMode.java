import Sudoku.Backtracking.SudokuBacktracking;
import Sudoku.Sudoku;

import java.io.IOException;


public class BatchMode {
    private static int algorithms = 1;
    private static long[] results = new long[algorithms];
    private static long[] aggregatedTime = new long[algorithms];
    private static int tests = 100;
    private static long start, finish, elapsed;
    private static Sudoku sudoku;
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

    public BatchMode(int num){
        this.tests = num;
    }

    private static void cleanStats(){
        for (int i = 0; i < algorithms; i++)
            aggregatedTime[i] = 0;
    }

    private static void solveByBacktracking() {
        SudokuBacktracking sb = new SudokuBacktracking(sudoku);

        start = System.currentTimeMillis();
        sb.solveByBacktracking();
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        sudoku.setFinalgrid(sb.getGrid());

        if (sudoku.isValid()){
            aggregatedTime[0] += elapsed;
        }
    }

    public static void run(){
        for (int i = 0; i < tests; i++){
            sudoku = new Sudoku();
            sudoku.setGrid(grid);
            solveByBacktracking();
        }
    }

    public static void averageTime(){
        for (int i = 0; i < algorithms; i++)
            results[i] = aggregatedTime[i]/tests;
    }

    public static void printResult(){
        System.out.println("   Results:");
        System.out.println();
        System.out.println(" Backtracking Algorithm: " + results[0] + " ms");
    }

    public static void execute(){
        try {
            cleanStats();
            System.out.println("Running Batch Mode");
            run();
            System.out.println("Batch Mode Finished");
            System.out.println("Calculating average execution time");
            averageTime();
            System.out.println();
            printResult();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       execute();
    }
}
