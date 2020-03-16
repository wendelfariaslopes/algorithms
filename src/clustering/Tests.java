package clustering;

import Jama.Matrix;
import algorithms.math.PearsonCorrelation;

public class Tests {

	
	public static void main(String[] args) {
		double[][] arrays = {{1, 3, 5},
				{5, 4, 1},{3, 8, 6}};
		
		int m = arrays.length;
		int n = arrays[0].length;
		
		Matrix m1 = new Matrix(arrays);
		Matrix m2 = m1.transpose();
		
		m1.print(1, 2);
		
		Matrix cov = new Matrix(m,n);
		
		double[][] T = m2.getArray();
	
		PearsonCorrelation.calculeCoefficient(T[0], T[0]);
		PearsonCorrelation.calculeCoefficient(T[0], T[1]);
		PearsonCorrelation.calculeCoefficient(T[0], T[2]);
		
		PearsonCorrelation.calculeCoefficient(T[1], T[1]);
		PearsonCorrelation.calculeCoefficient(T[1], T[2]);
		
		PearsonCorrelation.calculeCoefficient(T[2], T[2]);
		
		
		
		
		
		System.out.println();
		
		
	}
}
