package algorithms.ai.ml;

import java.util.Random;

import Jama.Matrix;

public class LinearEquation {

	public static void main(String[] args) {
		
		Random r = new Random();
		
//		double[][] X= { { 2, -2, 1 ,2}, { 1, 3, -2, 1 }, { 3, -1, -1, 2 }, { 3, -1, -1, 4 } };
//		double[] Y = { -3, 1, 2 , 4};
		
		// { "x", "y", "z", "r", "s"};
		double[] solutions = { 3, 2, 8, 5, 7 };
		
		int length = solutions.length;
		
		double[][] X= new double[length][length];
		double[] Y = new double[length];
		
		for(int i =0; i< length; i++) {
			for(int j =0; j < length; j++) {
				X[i][j] = r.nextInt(5) + 1;
				Y[i]+= X[i][j]* solutions[j];
			}
		}
		
		calculate(X, Y);

	}

	public static void calculate(double[][] X, double[] Y) {
		print(X, Y); // print equation system
		// Creating Matrix Objects with arrays
		Matrix x = new Matrix(X);
		Matrix y = new Matrix(Y, X[0].length);
		// Calculate Solved Matrix
		print(x.solve(y)); // and print solutions
	}

	public static void print(double[][] X, double[] Y) {

		int numEquations = X.length;

		System.out.println("--------- " + numEquations + " Equation(s) System ----------");

		String[] var = { "x", "y", "z", "r", "s", "t", "u", "w" };

		for (int m = 0; m < X.length; m++) {
			for (int n = 0; n < X[0].length; n++) {
				if (n == (X[0].length - 1)) {
					System.out.print(Math.round(X[m][n]) + var[n] + " = " + Y[m]);
				} else {
					System.out.print(Math.round(X[m][n]) + var[n] + " + ");
				}
			}
			System.out.println();
		}
		System.out.println("--------------------------------------------------");
	}

	public static void print(Matrix matrix) {
		String[] var = { "x", "y", "z", "r", "s", "t", "u", "w" };
		for (int row = 0; row < matrix.getRowDimension(); row++) {
			System.out.println(var[row] + " = " + Math.round(matrix.get(row, 0)));
		}

	}
}
