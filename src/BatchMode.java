import Sudoku.Backtracking.SudokuBacktracking;
import Sudoku.Sudoku;

import java.io.IOException;


public class BatchMode {
    private static int algorithms = 1;
    private static long[] results = new long[algorithms];
    private static long[] aggregatedTime = new long[algorithms];
    private static int tests = 100;
    private static int size = 9;
    private static long start, finish, elapsed;
    private static Sudoku sudoku;
    private static int[][] grid = new int[size][size];

    public BatchMode(int num, int[][] grid){
        this.tests = num;
        this.grid = grid;
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
