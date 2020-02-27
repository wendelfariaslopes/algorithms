package algorithms.ai.ml;

import Jama.Matrix;

public class LinearEquation2 {

	public static void main(String[] args) {
		
		double[][] X = {{1,0,0,1,1,1},{1,1,0,1,1,1},{1,1,1,1,1,1},{1,1,1,0,1,0},{1,1,1,0,1,0},{1,1,1,0,1,0}};
		
		double[] Y = {3,4,11,1,0,0};
		
		Matrix x = new Matrix(X);
		Matrix y = new Matrix(Y, X.length);

		Matrix solution = x.solve(y);
		
		solution.print(1, 2);


	}

}
