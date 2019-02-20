package algorithms.math;

import algorithms.io.StdDraw;
import algorithms.io.StdRandom;

/**
 * 
 * Brownian produces a function graph that approximates a simple example of
 * fractional Brownian motion known as Brownian bridge. You can think of this
 * graph as a random walk that connects the two points (x0, y0) and (x1, y1),
 * controlled by a few parameters. The implementation is based on the midpoint
 * displacement method, which is a recursive plan for drawing the plot within
 * the x-interval [x0, x1]. The base case (when the size of the interval is
 * smaller than a given tolerance) is to draw a straight line connecting the two
 * endpoints. The reduction case is to divide the interval into two halves,
 * proceeding as follows:
 * 
 * 1) Compute the midpoint (xm, ym) of the interval.
 * 
 * 2) Add to the y-coordinate ym of the midpoint a random value δ, drawn from
 * the Gaussian distribution with mean 0 and a given variance.
 * 
 * 3) Recur on the subintervals, dividing the variance by a given scaling factor S (smoothness).
 * 
 * The shape of the curve is controlled by two parameters: the volatility
 * (initial value of the variance) controls the distance the graph strays from
 * the straight line connecting the points, and the Hurst exponent controls the
 * smoothness of the curve.
 * 
 * @author wendellopes
 *
 */
public class Brownian {

	// midpoint displacement method
	public static void curve(double x0, double y0, double x1, double y1, double var, double s) {
		// stop if interval is sufficiently small
		if (Math.abs(x1 - x0) < 0.01) {
			StdDraw.line(x0, y0, x1, y1);
			return;
		}

		double xm = (x0 + x1) / 2;
		double ym = (y0 + y1) / 2;
		ym = ym + StdRandom.gaussian(0, Math.sqrt(var));
		curve(x0, y0, xm, ym, var / s, s);
		curve(xm, ym, x1, y1, var / s, s);
	}

	public static void main(String[] args) {

		double hurstExponent = Double.parseDouble("0.05");
		double smoothness = Math.pow(2, 2 * hurstExponent);
		double var = 0.01;
		double x0 = 0.0;
		double y0 = 0.5;
		double x1 = 1.0;
		double y1 = 0.5;
		
		curve(x0, y0, x1, y1, var, smoothness);
	}
}
