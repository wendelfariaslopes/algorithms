package algorithms.math;


public class Bissection {

	public static void main(String argv[]) {
		double x = 0, del = 1e-2, a = 0, b = 2;
		double dx = b - a;
		int k = 0;
		while (Math.abs(dx) > del && k < 10 && f(x) != 0) {
			x = ((a + b) / 2);
			if ((f(a) * f(x)) < 0) {
				b = x;
				dx = b - a;
			} else {
				a = x;
				dx = b - a;
			}
			k++;
			System.out.println("Iteration number: " + k);
			System.out.println("Root obtained: " + x);
			System.out.println("Estimated error: " + dx);
		}
		System.out.println("*****************");
		System.out.println("Iteration number: " + k);
		System.out.println("Root obtained: " + x);
		System.out.println("Estimated error: " + dx);
	}

	public static double f(double x) {
		return Math.exp(x) - 2 * x - 2;
	}
}
/*
 * Iteration number: 1 Root obtained: 1.0 Estimated error: 1.0 Iteration number:
 * 2 Root obtained: 1.5 Estimated error: 0.5 Iteration number: 3 Root obtained:
 * 1.75 Estimated error: 0.25 Iteration number: 4 Root obtained: 1.625 Estimated
 * error: 0.125 Iteration number: 5 Root obtained: 1.6875 Estimated error:
 * 0.0625 Iteration number: 6 Root obtained: 1.65625 Estimated error: 0.03125
 * Iteration number: 7 Root obtained: 1.671875 Estimated error: 0.015625
 * Iteration number: 8 Root obtained: 1.6796875 Estimated error: 0.0078125
 *****************
 * Iteration number: 8 Root obtained: 1.6796875 Estimated error: 0.0078125 Press
 * any key to continue...
 */
