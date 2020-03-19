package algorithms.math;

/**
 * um caminho autoevitante é uma sequência de movimentos em um retículo que não
 * visita o mesmo ponto mais de uma vez. Este é um caso especial da noção de
 * caminho da teoria dos grafos.
 * 
 * In mathematics, a self-avoiding walk (SAW) is a sequence of moves on a
 * lattice (a lattice path) that does not visit the same point more than once.
 * This is a special case of the algorithms.graph theoretical notion of a path.
 * 
 * @author wendellopes
 *
 */
public class SelfAvoidingWalk {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); // lattice size
		int trials = Integer.parseInt(args[1]); // number of trials
		int deadEnds = 0; // trials resulting in a dead end

		// simulate trials self-avoiding walks
		for (int t = 0; t < trials; t++) {
			boolean[][] a = new boolean[n][n]; // intersections visited
			int x = n / 2, y = n / 2; // current position

			// repeatedly take a random step, unless you've already escaped
			while (x > 0 && x < n - 1 && y > 0 && y < n - 1) {

				// dead-end, so break out of loop
				if (a[x - 1][y] && a[x + 1][y] && a[x][y - 1] && a[x][y + 1]) {
					deadEnds++;
					break;
				}

				// mark (x, y) as visited
				a[x][y] = true;

				// take a random step to unvisited neighbor
				double r = Math.random();
				if (r < 0.25) {
					if (!a[x + 1][y])
						x++;
				} else if (r < 0.50) {
					if (!a[x - 1][y])
						x--;
				} else if (r < 0.75) {
					if (!a[x][y + 1])
						y++;
				} else if (r < 1.00) {
					if (!a[x][y - 1])
						y--;
				}
			}
		}

		System.out.println(100 * deadEnds / trials + "% dead ends");
	}
}