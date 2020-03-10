package algorithms.ai.ml.image;

import algorithms.io.StdDraw;
import algorithms.io.StdOut;

/******************************************************************************
 *  Compilation:  javac RandomWalk.java
 *  Execution:    java RandomWalk n
 *  Dependencies: StdDraw.java
 *
 *  % java RandomWalk 20
 *  total steps = 300
 *
 *  % java RandomWalk 50
 *  total steps = 2630
 *
 *  Simulates a 2D random walk and plots the trajectory.
 *
 *  Remarks: works best if n is a divisor of 600.
 *
 ******************************************************************************/

public class RandomWalk { 

    public static void main(String[] args) {
        int n = Integer.parseInt("10");
        StdDraw.setXscale(-n, +n);
        StdDraw.setYscale(-n, +n);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();

        int x = 0, y = 0;
        int steps = 0;
        while (Math.abs(x) < n && Math.abs(y) < n) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(x, y, 0.45);
            double r = Math.random();
            if      (r < 0.25) x--;
            else if (r < 0.50) x++;
            else if (r < 0.75) y--;
            else if (r < 1.00) y++;
            steps++;
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledSquare(x, y, 0.45);
            StdDraw.show();
            Timer.pause(40);
        }
        StdOut.println("Total steps = " + steps);
    }

}

