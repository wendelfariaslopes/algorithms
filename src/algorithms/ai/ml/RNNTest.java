package algorithms.ai.ml;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;

import Jama.Matrix;

public class RNNTest {

	private static Matrix w1;
	private static Matrix w2;


	public static void main(String[] args) {
		
		Gson g = new Gson();
		
		Random r = new Random();
		
		int samples = 1;
		int numBytes = 20000;
		
		
		double[][] X = new double[samples][numBytes];
		double[][] Y = new double[samples][1];

		// criando dados simulados para testes
		for (int m = 0; m < samples; m ++) {
			
			if(m%2==0 || m%7==0) { //todos as posicoes no arrays que sao gatos sao pares ou divididos por 7 ... acao arbitraria.
				// dados in bytes
				for (int n = 0; n < numBytes; n++) {
					if(n <= 32) {
						X[m][n] = 0.1;	
					}else {
						X[m][n] = 0.99; 
					}
					
				}
				Y[m][0] = 1; // igual ao Cat (valor 1)
				
			}else {
				// dados in bytes
				for (int n = 0; n < numBytes; n++) {
					X[m][n] = r.nextDouble() + 0.01;
				}
				Y[m][0] = 0; // diff Cat (valor 0)
			}
			
//			if(Y[m][0]==1){
//				System.out.println("It is a Cat!");
//			}else {
//				System.out.println("Not a Cat!");
//			}
//			
//			System.out.println(g.toJson(Y[m])+" = "+g.toJson(X[m]));
			
			
		}
		
		learnFunction(X, Y);
		
		
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("--------------------------------------------");
		System.out.println("FINISHED PROGRAM");
		System.out.println("--------------------------------------------");
		
		//learnFunction(X, Y);
		
		

	}
	
	public static void learnFunction(double[][] X, double[][] Y) {
		
		int m = Y.length;
		int nodes = X.length; // hidden layer size

		double[][] W1 = np.random(nodes, X[0].length);
		double[][] b1 = new double[nodes][m];

		double[][] W2 = np.random(1, nodes);
		double[][] b2 = new double[1][m];

		X = np.T(X);
		Y = np.T(Y);

		for (int i = 0; i < 40000; i++) {

			// Implement Forward Propagation to calculate A2 (probabilities)
			// LAYER 1
			double[][] Z1 = np.add(np.dot(W1, X), b1);
			double[][] A1 = np.sigmoid(Z1);
			//double[][] A1 = np.relu(Z1);
			// LAYER 2
			double[][] Z2 = np.add(np.dot(W2, A1), b2);
			double[][] A2 = np.sigmoid(Z2);
			//double[][] A2 = np.relu(Z2);

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

			if (i % 10000 == 0) {
				np.print("==============");
				np.print("Cost = " + cost);
				np.print("Predictions = " + Arrays.deepToString(A2));

				w1 = new Matrix(W1);
				w2 = new Matrix(W2);
				
				w1.print(1, 5);
				w2.print(1, 5);
			}
		}
		
	}

}
