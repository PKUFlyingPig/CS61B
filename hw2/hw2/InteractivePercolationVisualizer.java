/******************************************************************************
 *  Compilation:  javac InteractivePercolationVisualizer.java
 *  Execution:    java InteractivePercolationVisualizer N
 *  Dependencies: PercolationVisualizer.java Percolation.java
 *
 *  This program takes the grid size N as a command-line argument.
 *  Then, the user repeatedly clicks sites to open with the mouse.
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black.
 *
 ******************************************************************************/
package hw2;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class InteractivePercolationVisualizer {
    /*private static final int DELAY = 20;

    public static void main(String[] args) {
        // N-by-N percolation system (read from command-line, default = 10)
        int N = 10;
        if (args.length == 1) {
            N = Integer.parseInt(args[0]);
        }

        // turn on animation mode
        StdDraw.show(0);

        // repeatedly open site specified my mouse click and draw resulting system
        StdOut.println(N);

        Percolation perc = new Percolation(N);
        PercolationVisualizer.draw(perc, N);
        StdDraw.show(DELAY);
        while (true) {

            // detected mouse click
            if (StdDraw.mousePressed()) {

                // screen coordinates
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();

                // convert to row i, column j
                int i = (int) (N - Math.floor(y) - 1);
                int j = (int) (Math.floor(x));

                // open site (i, j) provided it's in bounds
                if (i >= 0 && i < N && j >= 0 && j < N) {
                    if (!perc.isOpen(i, j)) {
                        StdOut.println(i + " " + j);
                    }
                    perc.open(i, j);
                }

                // draw N-by-N percolation system
                PercolationVisualizer.draw(perc, N);
            }
            StdDraw.show(DELAY);
        }
    }*/
}
