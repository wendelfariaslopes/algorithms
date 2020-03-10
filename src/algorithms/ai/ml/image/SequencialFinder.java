package algorithms.ai.ml.image;

import cogito4j.Metrics;

public class SequencialFinder {

	public static void main(String[] args) {

		int samples = 100000;

		double[] X = new double[samples];
		double[] small = { 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7, 8, 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7,
				8, 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7, 8, 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7, 8,
				1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7, 8, 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7,
				8, 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7, 8, 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7, 8,
				1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7, 8, 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7,
				8, 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7, 8, 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7, 8};
		int inceptionIn = 70000;

		for (int i = 0; i < samples; i++) {
			if (i == inceptionIn) {
				for (int j = 0; j < small.length; j++) {
					X[i] = small[j];
					++i;
				}
			}
		}

		System.out.println(indexOf(X, small));

		double[] x = { 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7,
				8, 1, 2, 3, 4, 4, 6, 7, 8, 9, 9, 9, 9, 7, 8, 7, 8, 1, 2, 3};// se considerarmos os valores menores faremos uma reducao aos casos conhecidos

		double percentSimilarity = 0.95;

		System.out.println(getSimilarityOf(X, x, percentSimilarity));

	}

	public static int indexOf(byte[] big, byte[] small) {
		for (int i = 0; i < big.length - small.length + 1; ++i) {
			boolean found = true;
			for (int j = 0; j < small.length; ++j) {
				if (big[i + j] != small[j]) {
					found = false;
					break;
				}
			}
			if (found)
				return i;
		}
		return -1;
	}

	public static int indexOf(int[] big, int[] small) {
		for (int i = 0; i < big.length - small.length + 1; ++i) {
			boolean found = true;
			for (int j = 0; j < small.length; ++j) {
				if (big[i + j] != small[j]) {
					found = false;
					break;
				}
			}
			if (found)
				return i;
		}
		return -1;
	}

	public static int indexOf(double[] big, double[] small) {
		for (int i = 0; i < big.length - small.length + 1; ++i) {
			boolean found = true;
			for (int j = 0; j < small.length; ++j) {
				if (big[i + j] != small[j]) {
					found = false;
					break;
				}
			}
			if (found)
				return i;
		}
		return -1;
	}

	public static int getSimilarityOf(double[] big, double[] small, double percentSimilarity) {

		for (int i = 0; i < big.length; ++i) {

			double[] blockToTest = new double[small.length];
			for (int j = 0; j < small.length; ++j) {
				blockToTest[j] = big[i + j];
			}

			// System.out.println(Arrays.toString(blockToTest));
			if (Metrics.pearson(blockToTest, small) >= percentSimilarity) {
				return i;
			}
		}
		return -1;
	}

}
