package algorithms.ai.ml;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Jama.Matrix;

public class RNNTest {

	private static double[][] X;
	private static double[][] Y;

	private static Matrix w1;
	private static Matrix w2;
	
	private  double[][]W1;
	private  double[][]W2;

	/*
	 * -Xmx9000m -Xms6000m
	 */

	public static void main(String[] args) {

		int samples = 3;
		int numBytes = 100;

		generatorXY(samples, numBytes);

		System.out.println();
		System.out.println("---------- Start Training -------------");
		System.out.println("");

		Map<String,Double[][]> weightMap = learnFunction(X, Y);

		System.out.println();
		System.out.println("---------- End Training -------------");
		System.out.println("");

		System.out.println();
		System.out.println("---------- Start Tests -------------");
		System.out.println("");

		//testObject(castDoubleToPrimitiveDouble(weightMap.get("W1")), castDoubleToPrimitiveDouble(weightMap.get("W2")), X);
		
		testLearnFunction(X, Y, castDoubleToPrimitiveDouble(weightMap.get("W1")), castDoubleToPrimitiveDouble(weightMap.get("W2"))
				,castDoubleToPrimitiveDouble(weightMap.get("B1")), castDoubleToPrimitiveDouble(weightMap.get("B2")));
		
	
//		int s = 5; // um gato
//	    System.out.println("Exemplar eh "+ Y[s][0]+ " um Cat!");
			
//			for (int train = 0; train < samples; train++) {
//
//				if (train % 2 == 0) {
//					System.out.print("Train for CAT ");
//					testObject(w1.getArray()[train], w2.getArray()[0][train], X[s]);
//	
//				} else {
//					System.out.print("Train NO cat ");
//					testObject(w1.getArray()[train], w2.getArray()[0][train], X[s]);
//	
//				}
//			}

		System.out.println();
		System.out.println("---------- End Tests -------------");
		System.out.println("");

		System.out.println("--------------------------------------------");
		System.out.println("#######------- FINISHED PROGRAM  -------####");
		System.out.println("--------------------------------------------");

	}

	public static Map<String,Double[][]> learnFunction(double[][] X, double[][] Y) {
		
		Map<String,Double[][]> weightMap = new HashMap<>();

		int cycles = 4000;

		int m = Y.length;
		int nodes = X.length; // hidden layer size

		double[][] W1 = np.random(nodes, X[0].length);
		double[][] b1 = new double[nodes][m];

		double[][] W2 = np.random(1, nodes);
		double[][] b2 = new double[1][m];

		X = np.T(X);
		Y = np.T(Y);
	

		for (int i = 0; i < cycles; i++) {

			// Implement Forward Propagation to calculate A2 (probabilities)
			// LAYER 1
			double[][] Z1 = np.add(np.dot(W1, X), b1);
			double[][] A1 = np.sigmoid(Z1);
			// double[][] A1 = np.relu(Z1);
			// LAYER 2
			double[][] Z2 = np.add(np.dot(W2, A1), b2);
			double[][] A2 = np.sigmoid(Z2);
			// double[][] A2 = np.relu(Z2);
			
			if(i==cycles-1) {
				weightMap.put("W1", castPrimitiveDoubleToDouble(W1));
				weightMap.put("W2", castPrimitiveDoubleToDouble(W2));
				weightMap.put("B1", castPrimitiveDoubleToDouble(b1));
				weightMap.put("B2", castPrimitiveDoubleToDouble(b2));
			}

			double cost = np.cross_entropy(m, Y, A2);

			// Back Prop
			// LAYER 2
			double[][] dZ2 = np.subtract(A2, Y);
			double[][] dW2 = np.divide(np.dot(dZ2, np.T(A1)), m);
			double[][] db2 = np.divide(dZ2, m);
			// LAYER 1
			double[][] dZ1 = np.multiply(np.dot(np.T(W2), dZ2), np.subtract(1.0, np.power(A1, 2)));
			double[][] dW1 = np.divide(np.dot(dZ1, np.T(X)), m);
			double[][] db1 = np.divide(dZ1, m);

			// Gradient descent parameter update.
			// Learning Rate lr
			double lr = 0.01;
			W1 = np.subtract(W1, np.multiply(lr, dW1));
			b1 = np.subtract(b1, np.multiply(lr, db1));

			W2 = np.subtract(W2, np.multiply(lr, dW2));
			b2 = np.subtract(b2, np.multiply(lr, db2));

			int c = cycles / 10;
			if (i % c == 0) {
				np.print("============== Print after " + i + " Cycles.");
				np.print("Cost = " + cost);
				np.print("A2 = " + Arrays.deepToString(A2));
			}

		}
		
		return weightMap;
	}
	
	public static void testLearnFunction(double[][] X, double[][] Y, double[][]W1, double[][]W2, double[][] b1, double[][] b2) {
		int m = Y.length;
		int nodes = X.length; // hidden layer size
		
		int cycles = 2;
		X = np.T(X);
		Y = np.T(Y);

		for (int i = 0; i < cycles; i++) {
			
			// Implement Forward Propagation to calculate A2 (probabilities)
						// LAYER 1
						double[][] Z1 = np.add(np.dot(W1, X), b1);
						double[][] A1 = np.sigmoid(Z1);
						// double[][] A1 = np.relu(Z1);
						// LAYER 2
						double[][] Z2 = np.add(np.dot(W2, A1), b2);
						double[][] A2 = np.sigmoid(Z2);
						// double[][] A2 = np.relu(Z2);
						
			
					

			np.print("A2 = " + Arrays.deepToString(A2));
			
		}
	}


	// criando dados simulados para testes
	private static void generatorXY(int samples, int numBytes) {
		Random r = new Random();

		X = new double[samples][numBytes];
		Y = new double[samples][1];

		for (int m = 0; m < samples; m++) {
			if (m % 2 == 0) { // todos as posicoes no arrays que sao gatos sao pares ou divididos por 7 ...
								// acao arbitraria.
				// Gerando dados in bytes aleatorios
				for (int n = 0; n < numBytes; n++) {
					if (n >= numBytes/2) {
						X[m][n] = 0.99;
					} else {
						X[m][n] = 0.000001;
					}
				}
				Y[m][0] = 1; // igual ao Cat (valor 1)
			} else {
				// Gerando dados in bytes aleatorios
				for (int n = 0; n < numBytes; n++) {
					X[m][n] = r.nextDouble() + 0.0001;
				}
				Y[m][0] = 0; // diff Cat (valor 0)
			}
		}
	}

	public static void testObject(double[][] W1, double[][] W2, double[] object) {

		// Criamos aqui uma matrix que para passar por todos os treinos realisados
		double[][] X = new double[W1.length][object.length];
		// Preenche a matrix com o mesmo objeto para passar por cada treino
		for (int i = 0; i < W1.length; i++) {
			X[i] = object;
		}

		X = np.T(X);

		// Implement Forward Propagation to calculate A2 (probabilities)
		// LAYER 1
		double[][] Z1 = np.dot(W1, X);
		double[][] A1 = np.sigmoid(Z1);

//		Matrix m = new Matrix(Z1);
//		m.print(1, 3);

		// LAYER 2
		double[][] Z2 = np.dot(W2, Z1);
		double[][] A2 = np.sigmoid(Z2);

		np.print("Predictions = " + Arrays.deepToString(A2));

	}
	
	public static void testObject(double[][] W1, double[][] W2, double[][] object) {

		// Implement Forward Propagation to calculate A2 (probabilities)
		// LAYER 1
		double[][] Z1 = np.dot(W1, np.T(object));
		double[][] A1 = np.sigmoid(Z1);

		// LAYER 2
		double[][] Z2 = np.dot(W2, Z1);
		double[][] A2 = np.sigmoid(Z2);

		np.print("Predictions = " + Arrays.deepToString(A2));

	}


	public static void testObject(double[] W1, double W2, double[] object) {

		Matrix o = new Matrix(object, 1);
		Matrix oT = o.transpose();

		Matrix w = new Matrix(W1, 1);

		Matrix s = w.times(oT);
		
		double[][] w1 = np.sigmoid(s.getArray()); 
		
		Matrix ww1= new Matrix(w1);

		ww1 = ww1.times(W2);

		double[][] v = np.sigmoid(ww1.getArray());

		Matrix vv = new Matrix(v);

		vv.print(1, 3);

		// oT.print(1, 3);

	}
	
	public static void testObject(double[] W1, double W2, double[][] object) {


		
		double[][] w1 = np.sigmoid(np.T(object)); 
		
		Matrix ww1= new Matrix(w1);

		ww1 = ww1.times(W2);

		double[][] v = np.sigmoid(ww1.getArray());

		Matrix vv = new Matrix(v);

		vv.print(1, 3);

		// oT.print(1, 3);

	}
	
	
	public static Double[][] castPrimitiveDoubleToDouble(double [][] matrix){
		Double[][] matrixDouble = new Double[matrix.length][matrix[0].length];
		for(int i=0; i < matrix.length;i++) {
			for(int j=0; j < matrix[0].length;j++) {
				matrixDouble[i][j] = matrix[i][j];
			}
		}
		return matrixDouble;
	}
	
	public static double[][] castDoubleToPrimitiveDouble(Double [][] matrix){
		double[][] matrixDouble = new double[matrix.length][matrix[0].length];
		for(int i=0; i < matrix.length;i++) {
			for(int j=0; j < matrix[0].length;j++) {
				matrixDouble[i][j] = matrix[i][j];
			}
		}
		return matrixDouble;
	}

}
