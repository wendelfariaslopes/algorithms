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
	private static Matrix B1;
	private static Matrix B2;


	public static void main(String[] args) {
		
		Random r = new Random();
		
		int samples = 20;
		int numBytes = 64;
		
		
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
			
		}
		
		
		// First, let's get the dataset you will work on. Dataset into variables X and
		// Y.
		/*
		 * The general methodology to build a Neural Network is to: 1. Define the neural
		 * network structure 1.1. n_x: the size of the input layer 1.2. n_h: the size of
		 * the hidden layer (set this to 4) 1.3. n_y: the size of the output layer
		 * 
		 * 2. Initialize the model's parameters
		 * 
		 * 3. Loop: 3.1. Implement forward propagation 3.2. Compute loss 3.3. Implement
		 * backward propagation to get the gradients 3.4. Update parameters (gradient
		 * descent)
		 * 
		 * You often build helper functions to compute steps 1-3 and then merge them
		 * into one function we call nn_model(). Once you've built nn_model() and learnt
		 * the right parameters, you can make predictions on new data.
		 * 
		 */

		// X = {{Photo 1},{Photo 2},{Photo 3}, ..., {Photo k}}
		// X = {{210, 192, 21, 250, ..... pixel value}, {210, 192, 21, 250, ..... pixel
		// value} , ..., {pixel values Photo k}}
		// double[][] X = { { 0.1, 0.11, 0.13, 0.12 }, { 0.1, 0.11, 0.12, 0.11 }, { 0.2,
		// 0.21, 0.22, 0.25 },
		// { 0.23, 0.24, 0.25, 0.26 }, { 0.24, 0.25, 0.26, 0.27 }, { 0.14, 0.15, 0.16,
		// 0.17 } };

		// Y = {{is a Cat},{is a Cat},{is NOT cat},... {is a cat}, ..., {is NOT cat} }
		// Y = { {0.15} , {0.19} , {0.97} ,... {value <= 0.5 },..., {value > 0.5}}
		// double[][] Y = { { 0.1 }, { 0.1 }, { 0.2 }, { 0.2 }, { 0.2 }, { 0.1 } };

		// X.length --> number of rows (lines) --> number of examples (samples)
		// X[0].lenght --> number of columns --> number of variables of input in your
		// problem
		// double[][] X = {{0.1, 0.2, 0.3, 0.4}, {0.11,0.19, 0.29, 0.41}};
		// Y.length --> number of rows (lines) --> number of outputs
		// double[][] Y = {{0.5}, {0.5}};

		//double[][] X = { { 0.3, 0.4}, { 0.31, 0.39}, { 0.10, 0.2}};// X --> Object to be analyzed (with only relevant
																		// attributes?)

		//double[][] Y = { { 0.41 }, { 0.41 }, {0.8} };// Y --> Value attributed to the analyzed object

		
		
		
		//Case 1 :
		double a = 0.3, b = 0.8;
		double x1= 0.3, x2 = 0.4;
		
		double y1 = a*x1 + b*x2;
		// y1 = 41
		System.out.print(y1);
		//Case 2 :
		//x1 = 3 x2 = 4
		//y = 41
		//In case 2, we don’t know a and b, but we know the value of y.

		
		// verifyTrainingIO(X, Y);

		int m = Y.length;

		/**
		 * Interpretation: Recursive Neural Network The larger models (with more hidden
		 * units) are able to fit the training set better, until eventually the largest
		 * models overfit the data. The best hidden layer size seems to be around n_h =
		 * 5. Indeed, a value around here seems to fits the data well without also
		 * incurring noticable overfitting. You will also learn later about
		 * regularization, which lets you use very large models (such as n_h = 50)
		 * without much overfitting.
		 */
		int nodes = X.length; // hidden layer size

		// STEP 1 - Initialize the model's parameters
		/*
		 * 
		 * Instructions: Make sure your parameters' sizes are right. Refer to the neural
		 * network figure above if needed. You will initialize the weights matrices with
		 * random values. Use: np.random.randn(a,b) * 0.01 to randomly initialize a
		 * matrix of shape (a,b). You will initialize the bias vectors as zeros. Use:
		 * np.zeros((a,b)) to initialize a matrix of shape (a,b) with zeros.
		 * 
		 */

		double[][] W1 = np.random(nodes, X[0].length);
		double[][] b1 = new double[nodes][m];

		double[][] W2 = np.random(1, nodes);
		double[][] b2 = new double[1][m];

		X = np.T(X);
		Y = np.T(Y);

		/**
		 * Base of Artificial Intelligence (Neural Network and Machine Learning Process
		 * with Recursive Neural Network - RNN) Convulotional Neural Network don't apply
		 * here
		 * 
		 * The Loop
		 * 
		 * Instructions: 1. Look above at the mathematical representation of your
		 * classifier. 2. You can use the function sigmoid(). It is built-in (imported)
		 * in the notebook. 3. You can use the function np.tanh(). It is part of the
		 * numpy library. 4. The steps you have to implement are: 4.1. Retrieve each
		 * parameter from the dictionary "parameters" (which is the output of
		 * initialize_parameters()) by using parameters[".."]. 4.2. Implement Forward
		 * Propagation. Compute Z[1],A[1],Z[2] and A[2] (the vector of all your
		 * predictions on all the examples in the training set). 5. Values needed in the
		 * backpropagation are stored in "cache". The cache will be given as an input to
		 * the backpropagation function.
		 * 
		 */

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

			// Now that you have computed A[2] (in the Python variable "A2"), which contains
			// a[2](i)
			// for every example, you can compute the cost function as follows:
			/*
			 * J = −1/m ∑ (y(i)log(a[2](i))+(1−y(i))log(1−a[2](i)))
			 * 
			 * There are many ways to implement the cross-entropy loss. To help you, we give
			 * you how we would have implemented
			 * 
			 * − ∑y(i)log(a[2](i)):
			 * 
			 * logprobs = np.multiply(np.log(A2),Y) cost = - np.sum(logprobs) # no need to
			 * use a for loop! (you can use either np.multiply() and then np.sum() or
			 * directly np.dot()).
			 * 
			 */
			double cost = np.cross_entropy(m, Y, A2);
			//costs.getData().add(new XYChart.Data(i, cost));

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

			if (i % 4000 == 0) {
				np.print("==============");
				np.print("Cost = " + cost);
				np.print("Predictions = " + Arrays.deepToString(A2));

				w1 = new Matrix(W1);
				B1 = new Matrix(b1);
				w2 = new Matrix(W2);
				B2 = new Matrix(b2);
//
//				sd = Statistic.stddevp(A2[0]);
//				variance = Statistic.var(A2[0]);
//				mean = Statistic.mean(A2[0]);
				System.out.println(Arrays.toString(A2[0]));

			}

		}

//		np.print("Average = " + mean);
//		np.print("Variance = " + variance);
//		np.print("Standard deviation = " + sd);
		// np.print("===== W1 and b1 =====");
		// w1.print(0, 3);
		// B1.print(0, 3);
		// np.print("===== W2 and b2 =====");
		// w2.print(0, 3);
		// B2.print(0, 3);
	//	X = np.T(X);
		// Testes se o treino foi correto armazenado
//		double[][] XF = new double[X.length][X[0].length]; // trocado porque as dimesoes sao da Transposta de X (lembrar
//															// que foi feito X = np.T(X))
//		for (int i = 0; i < X.length; i++) {
//			for (int j = 0; j < X[0].length; j++) {
//				XF[i][j] = 0.1;
//			}
//		}
//
//		System.out.println("");
//		System.out.println("-----------------------------------------------------");
//		System.out.println("Objeto a ser analisado");
//		System.out.println("");

		// np.print("Test arrray = " + Arrays.deepToString(XF));

		// double[][] XF = { { 0.11, 0.19, 0.29, 0.41 }, { 0.11, 0.19, 0.29, 0.41 } };
		//XF = np.T(XF);

		Gson g = new Gson();
		System.out.println(g.toJson(w1, Jama.Matrix.class));
		System.out.println(g.toJson(B1, Jama.Matrix.class));
		System.out.println(g.toJson(w2, Jama.Matrix.class));
		System.out.println(g.toJson(B2, Jama.Matrix.class));

//		System.out.println("");
//
//		// LAYER 1
//		double[][] Z1 = np.add(np.dot(w1.getArray(), XF), B1.getArray());
//		double[][] A1 = np.sigmoid(Z1);
//		// LAYER 2
//		double[][] Z2 = np.add(np.dot(w2.getArray(), A1), B2.getArray());
//		double[][] A2 = np.sigmoid(Z2);
//
		//np.print("Predictions Final = " + Arrays.deepToString(A2));
		//np.print("Predictions Final = " + Arrays.deepToString(A2));
	}

	// STEP 0 - Initialize the model's parameters
	private static void verifyTrainingIO(double[][] X, double[][] Y) {

		int teFromX = X.length;
		int varFromX = X[0].length;
		int teFromY = Y.length;
		int varFromY = Y[0].length;

		if (teFromX != teFromY) {
			System.out.println(
					"Input have " + teFromX + " training examples and Output have " + teFromY + " training examples!");
			throw new UnsupportedOperationException("Not supported by this implementation");

		} else {
			np.print("The shape of X is: " + np.shape(X));
			System.out.println(" --> Input X[colunms] have " + varFromX + " variables or attributes");
			System.out.println(" --> Input X[rows] have " + teFromX + " training examples or objects!");
			np.print("The shape of Y is: " + np.shape(Y));
			System.out.println(" --> Output Y[colunms] have " + varFromY + " variables or attributes");
			System.out.println(" --> Output Y[rows] have " + teFromY + " training examples or objects!");
		}

	}

	// STEP 1 - Initialize the model's parameters
	/**
	 * Instructions: Make sure your parameters' sizes are right. Refer to the neural
	 * network figure above if needed. You will initialize the weights matrices with
	 * random values. Use: np.random.randn(a,b) * 0.01 to randomly initialize a
	 * matrix of shape (a,b). You will initialize the bias vectors as zeros. Use:
	 * np.zeros((a,b)) to initialize a matrix of shape (a,b) with zeros.
	 * 
	 * @param X
	 * @param hiddenLayers
	 * @param Y
	 * @return
	 */
	private Map<String, Double[][]> initializeParameters(double[][] X, int hiddenLayers, double[][] Y) {
		Map<String, Double[][]> parameters = new HashMap<>();
		parameters.put("W1", np.random(hiddenLayers, X[0].length, true));
		parameters.put("b1", new Double[hiddenLayers][Y.length]);
		parameters.put("W2", np.random(1, hiddenLayers, true));
		parameters.put("b2", new Double[1][Y.length]);
		return parameters;
	}

	// STEP 6 - Predict Function
	/*
	 * Using the learned parameters, predicts a class for each example in X
	 */

	// STEP 7 - Accuracy function
	public double[][] accuracy(double[][] predictions, double[][] Y) {
		double[][] r = np.add(np.dot(Y, np.T(predictions)),
				np.dot(np.subtract(1.0, Y), np.subtract(1.0, np.T(predictions))));

		return np.divide(r, Y.length * 100);
	}

	/**
	 * Ration Return
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static double ratioReturn(double first, double second) {
		return (second / first) - 1;
	}
}
