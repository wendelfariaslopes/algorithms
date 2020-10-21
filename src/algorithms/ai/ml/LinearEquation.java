package algorithms.ai.ml;

import java.util.Random;

import Jama.Matrix;


public class LinearEquation {
	
	private static final String[] var = { "x", "y", "z", "r", "s", "t", "u", "w", "a", "b", "c", "d", "e", "f" };

	public static void main(String[] args) {

		Random r = new Random();

//		double[][] X= { { 2, -2, 1 ,2}, { 1, 3, -2, 1 }, { 3, -1, -1, 2 }, { 3, -1, -1, 4 } };
//		double[] Y = { -3, 1, 2 , 4};

		// { "x", "y", "z", "r", "s"};
		double[] solutions = { 0.3, 0.2, 0.8, 0.5, 0.7, 0.4, 0.11, 0.13, 0.6, 0.9, 0.23 };

		int length = solutions.length;
		
		int col = length; // As colunas sao os neurons com seus respectivos pesos.
		int row = length; // The rows correspondem ao numero de experiencias 

		double[][] X = new double[row][col];
		double[] Y = new double[row];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				//X[i][j] = r.nextInt(5) + 1;
				X[i][j] = r.nextDouble()+0.0001;
				Y[i] += X[i][j] * solutions[j];
			}
		}
		
		Matrix x = new Matrix(X);
		x.print(1, 2);

		calculate(X, Y);

	}

	public static void calculate(double[][] X, double[] Y) {
		print(X, Y); // print equation system
		// Creating Matrix Objects with arrays
		Matrix x = new Matrix(X);
		Matrix y = new Matrix(Y, X[0].length);
		// Calculate Solved Matrix
		printSolution(x.solve(y)); // and print solutions
	}

	public static void print(double[][] X, double[] Y) {

		int numEquations = X.length;

		System.out.println("--------- " + numEquations + " Equation(s) System ----------");


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

		for (int row = 0; row < matrix.getRowDimension(); row++) {
			System.out.println(var[row] + " = " + Math.round(matrix.get(row, 0)));
		}

	}
	
	public static void printSolution(Matrix matrix) {

		for (int row = 0; row < matrix.getRowDimension(); row++) {
			System.out.println(var[row] + " = " + matrix.get(row, 0));
		}

	}
}
