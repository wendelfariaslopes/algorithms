package cogito4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cogito4j.data.DataFactory;

public class RNNTest {

	private static double[][] X;
	private static double[][] Y;

	private double[][] W1;
	private double[][] W2;
	
	public static List<Integer> listPositiveCases;
	
	/*
	 * -Xmx9000m -Xms6000m
	 */

	public static void main(String[] args) {
		
		
		System.out.println("\n-------- Start Training Set -------------\n");


		int samples = 20;
		int numBytes = 100;
		
		
		Map<Integer,String> modelMap = DataFactory.createArtificialTrainingDataSet(samples, numBytes);
		
		X = DataFactory.getX();
		Y = DataFactory.getY();
		
		System.out.println();
		System.out.println("---------- End Training Set -------------");
		System.out.println("");
		

		System.out.println();
		System.out.println("---------- Start Training Set -------------");
		System.out.println("");

		Map<String, Double[][]> weightMap = learnAlgorithm(X, Y);

		System.out.println();
		System.out.println("---------- End Training Set -------------");
		System.out.println("");

		System.out.println();
		System.out.println("---------- Start Tests -------------");
		System.out.println("");
		
		
		//Predictive Model
		
		double[][] w1 = castDoubleToPrimitiveDouble(weightMap.get("W1"));
		double[][] w2 = castDoubleToPrimitiveDouble(weightMap.get("W2"));
		double[][] b1 = castDoubleToPrimitiveDouble(weightMap.get("B1"));
		double[][] b2 = castDoubleToPrimitiveDouble(weightMap.get("B2"));
		
		modelMap.forEach((k,v)->{
			
			if(v.equals("Positive")) {
				
			}
		
		});
		

		testLearnFunction(X, w1,w2,b1,b2);


		System.out.println();
		System.out.println("---------- End Tests -------------");
		System.out.println("");

		System.out.println("--------------------------------------------");
		System.out.println("#######------- FINISHED PROGRAM  -------####");
		System.out.println("--------------------------------------------");

	}

	public static Map<String, Double[][]> learnAlgorithm(double[][] X, double[][] Y) {

		Map<String, Double[][]> weightMap = new HashMap<>();

		int cycles = 4000;

		int m = Y.length;
		int nodes = X.length; // hidden layer size

		double[][] W1 = Matrix.random(nodes, X[0].length);
		double[][] b1 = new double[nodes][m];

		double[][] W2 = Matrix.random(1, nodes);
		double[][] b2 = new double[1][m];

		X = Matrix.T(X);
		Y = Matrix.T(Y);

		for (int i = 0; i < cycles; i++) {

			// Implement Forward Propagation to calculate A2 (probabilities)
			// LAYER 1
			double[][] Z1 = Matrix.add(Matrix.dot(W1, X), b1);
			double[][] A1 = Matrix.sigmoid(Z1);
			// double[][] A1 = Matrix.relu(Z1);
			// LAYER 2
			double[][] Z2 = Matrix.add(Matrix.dot(W2, A1), b2);
			double[][] A2 = Matrix.sigmoid(Z2);
			// double[][] A2 = Matrix.relu(Z2);

			if (i == cycles - 1) {//store learned 
				weightMap.put("W1", castPrimitiveDoubleToDouble(W1));
				weightMap.put("W2", castPrimitiveDoubleToDouble(W2));
				weightMap.put("B1", castPrimitiveDoubleToDouble(b1));
				weightMap.put("B2", castPrimitiveDoubleToDouble(b2));
			}

			double cost = Matrix.cross_entropy(m, Y, A2);

			// Back Prop
			// LAYER 2
			double[][] dZ2 = Matrix.subtract(A2, Y);
			double[][] dW2 = Matrix.divide(Matrix.dot(dZ2, Matrix.T(A1)), m);
			double[][] db2 = Matrix.divide(dZ2, m);
			// LAYER 1
			double[][] dZ1 = Matrix.multiply(Matrix.dot(Matrix.T(W2), dZ2), Matrix.subtract(1.0, Matrix.power(A1, 2)));
			double[][] dW1 = Matrix.divide(Matrix.dot(dZ1, Matrix.T(X)), m);
			double[][] db1 = Matrix.divide(dZ1, m);

			// Gradient descent parameter update.
			// Learning Rate lr
			double lr = 0.01;
			W1 = Matrix.subtract(W1, Matrix.multiply(lr, dW1));
			b1 = Matrix.subtract(b1, Matrix.multiply(lr, db1));

			W2 = Matrix.subtract(W2, Matrix.multiply(lr, dW2));
			b2 = Matrix.subtract(b2, Matrix.multiply(lr, db2));

			int c = cycles / 10;
			if (i % c == 0) {
				Matrix.print("============== Print after " + i + " Cycles.");
				Matrix.print("Cost = " + cost);
				Matrix.print("A2 = " + Arrays.deepToString(A2));
			}

		}

		return weightMap;
	}

	public static void testLearnFunction(double[][] X, double[][] W1, double[][] W2, double[][] b1, double[][] b2) {
		X = Matrix.T(X);
		double[][] Z1 = Matrix.add(Matrix.dot(W1, X), b1);
		double[][] A1 = Matrix.sigmoid(Z1);
		double[][] Z2 = Matrix.add(Matrix.dot(W2, A1), b2);
		double[][] A2 = Matrix.sigmoid(Z2);
		Matrix.print("A2 = " + Arrays.deepToString(A2));
	}

	public static void testLearnFunction(double[] X, double[][] W1, double[][] W2, double[][] b1, double[][] b2) {
		// Criamos aqui uma matrix que para passar por todos os treinos realisados
		double[][] o = new double[W1.length][X.length];
		// Preenche a matrix com o mesmo objeto para passar por cada treino
		for (int i = 0; i < W1.length; i++) {
			o[i] = X;
		}
		o = Matrix.T(o);
		double[][] Z1 = Matrix.add(Matrix.dot(W1, o), b1);
		double[][] A1 = Matrix.sigmoid(Z1);
		double[][] Z2 = Matrix.add(Matrix.dot(W2, A1), b2);
		double[][] A2 = Matrix.sigmoid(Z2);
		Matrix.print("A2 = " + Arrays.deepToString(A2));
	}

	

	public static Double[][] castPrimitiveDoubleToDouble(double[][] matrix) {
		Double[][] matrixDouble = new Double[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrixDouble[i][j] = matrix[i][j];
			}
		}
		return matrixDouble;
	}

	public static double[][] castDoubleToPrimitiveDouble(Double[][] matrix) {
		double[][] matrixDouble = new double[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrixDouble[i][j] = matrix[i][j];
			}
		}
		return matrixDouble;
	}

}
