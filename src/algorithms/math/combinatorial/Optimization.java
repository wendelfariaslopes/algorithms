package algorithms.math.combinatorial;

import java.util.ArrayList;
import java.util.List;

public class Optimization {
	
	private static final int SECONDS = 1000;
	private static final int MINUTES = 60*SECONDS;
	private static final int HOURS = 60*MINUTES;

	public static boolean isFaisable(int n, int k, int timeToProcessEach) {
		return (combinations(n, k)/timeToProcessEach) < 5*MINUTES ? true:false;
	}

	/**
	 * C (n,k) = n! _________ k! x (n - k)!
	 * 
	 * limits of values 13! > Integer.MAX_VALUE 21! > Long.MAX_VALUE
	 */
	private static int combinations(int n, int k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}

	public static <T> ArrayList<ArrayList<T>> getPermutations(List<T> elements, int k) {
		return getPermutations(elements, k, 0);
	}

	public static <T> ArrayList<ArrayList<T>> getPermutations(List<T> elements, int k, int i) {
		ArrayList<ArrayList<T>> results = new ArrayList<>();
		if (k > 0) {
			int n = elements.size();
			for (int j = i; j <= n - k; j++) {
				T val = elements.get(j);
				ArrayList<ArrayList<T>> tails = getPermutations(elements, k - 1, j + 1);
				for (ArrayList<T> tail : tails) {
					ArrayList<T> result = new ArrayList<>();
					result.add(val);
					result.addAll(tail);
					results.add(result);
				}
			}
		} else {
			results.add(new ArrayList<T>());
		}
		return results;
	}

	private static int factorial(int i) {
		if (i == 1) {
			return 1;
		}
		return i * factorial(i - 1);
	}

}
