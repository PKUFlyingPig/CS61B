package hw4.puzzle;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Local tester for the Solver class. You'll need to uncomment
 * in order to run the tests.
 */
public class TestSolver {
    public static class BoardPuzzleSolution {
        final String name;
        final Board board;
        final int numMoves;
        public BoardPuzzleSolution(String n, Board b, int m) {
            name = n;
            board = b;
            numMoves = m;
        }
    }

    public static class WordPuzzleSolution {
        final String start;
        final String goal;
        final int numMoves;
        final List<String> possibleSolution;
        public WordPuzzleSolution(String s, String g, int m, List<String> ps) {
            start = s;
            goal = g;
            numMoves = m;
            possibleSolution = ps;
        }
    }

    /** Takes as input a word puzzle string and returns a WordPuzzle
     *  object. For example, "kept, tent, kept-kent-tent, 13"
     *  would return a WordPuzzle with start = kept, goal = tent,
     *  numMoves = 2, and possible solution {"kept", "kent", "tent"}.
     *  The last value is not used by this class, and indicates
     *  the number of enqueues used by the reference solution to
     *  solve the puzzle. Returns null if String isn't a valid word puzzle.
     */
    public static WordPuzzleSolution stringToWordPuzzle(String wp) {
        try {
            /* skip comments and blank lines */
            if (wp.charAt(0) == '#') {
                return null;
            }
            String[] tokens = wp.split(",");
            String start = tokens[0].replaceAll("\\s+", "");
            String goal = tokens[1].replaceAll("\\s+", "");
            String solutionString = tokens[2].replaceAll("\\s+", "");
            List<String> possibleSolution = Arrays.asList(solutionString.split("-"));
            int numMoves = possibleSolution.size() - 1;
            return new WordPuzzleSolution(start, goal, numMoves, possibleSolution);
        } catch (RuntimeException e) {
            return null;
        }
    }

/* Uncomment once you've written Solver.
    @Test(timeout = 10000)
    public void testWordPuzzles() {
        In in = new In("word_puzzles.txt");
        while (!in.isEmpty()) {
            WordPuzzleSolution wps = stringToWordPuzzle(in.readLine());
            if (wps == null) {
                continue;
            }
            Word w = new Word(wps.start, wps.goal);
            Solver s = new Solver(w);
            String errorMessage = "Wrong number of moves solving "
                                  + wps.start + "->" + wps.goal;

            assertEquals(errorMessage, wps.numMoves, s.moves());
        }
    }
 */

 /* Uncomment everything in this block once you've written Board.
     public static Board readBoard(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board start = new Board(tiles);
        return start;
    }

    @Test(timeout = 1000)
    public void test2x2BoardPuzzles() {
        for (int i = 0; i <= 6; i += 1) {
            String pnum = String.format("%02d", i);
            String puzzleName = "puzzle2x2-" + pnum + ".txt";
            Board b = readBoard(puzzleName);
            int numMoves = i;
            BoardPuzzleSolution bps = new BoardPuzzleSolution(puzzleName, b, numMoves);
            Solver s = new Solver(b);
            assertEquals("Wrong number of moves on " + puzzleName, bps.numMoves, s.moves());
        }
    }

    @Test(timeout = 40000)
    public void test3x3BoardPuzzles() {
        for (int i = 0; i <= 30; i += 1) {
            String pnum = String.format("%02d", i);
            String puzzleName = "puzzle3x3-" + pnum + ".txt";
            Board b = readBoard(puzzleName);
            int numMoves = i;
            BoardPuzzleSolution bps = new BoardPuzzleSolution(puzzleName, b, numMoves);
            Solver s = new Solver(b);
            assertEquals("Wrong number of moves on " + puzzleName, bps.numMoves, s.moves());
        }
    }

    @Test(timeout = 20000)
    public void test4x4BoardPuzzles() {
        for (int i = 0; i <= 30; i += 1) {
            String pnum = String.format("%02d", i);
            String puzzleName = "puzzle4x4-" + pnum + ".txt";
            Board b = readBoard(puzzleName);
            int numMoves = i;
            BoardPuzzleSolution bps = new BoardPuzzleSolution(puzzleName, b, numMoves);
            Solver s = new Solver(b);
            assertEquals("Wrong number of moves on " + puzzleName, bps.numMoves, s.moves());
        }
    }

    @Test(timeout = 20000)
    public void testVariousPuzzles() {
        for (int i = 0; i <= 31; i += 1) {
            String pnum = String.format("%02d", i);
            String puzzleName = "puzzle" + pnum + ".txt";
            Board b = readBoard(puzzleName);
            int numMoves = i;
            BoardPuzzleSolution bps = new BoardPuzzleSolution(puzzleName, b, numMoves);
            Solver s = new Solver(b);
            assertEquals("Wrong number of moves on " + puzzleName, bps.numMoves, s.moves());
        }
    }*/
}
