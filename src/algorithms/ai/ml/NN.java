package algorithms.ai.ml;

import java.util.Arrays;

import Jama.Matrix;

public class NN {
	
	private static Matrix w1;
	private static Matrix w2;
	private static Matrix B1;
	private static Matrix B2;
  
    public static void main(String[] args) {
  
        double[][] X = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[][] Y = {{0}, {1}, {1}, {0}};

        int m = 4;
        int nodes = 100;

        X = np.T(X);
        Y = np.T(Y);

        double[][] W1 = np.random(nodes, 2);
        double[][] b1 = new double[nodes][m];

        double[][] W2 = np.random(1, nodes);
        double[][] b2 = new double[1][m];

        for (int i = 0; i < 4000; i++) {
            
        	// Foward Prop
            // LAYER 1
            double[][] Z1 = np.add(np.dot(W1, X), b1);
            double[][] A1 = np.sigmoid(Z1);
            //LAYER 2
            double[][] Z2 = np.add(np.dot(W2, A1), b2);
            double[][] A2 = np.sigmoid(Z2);

            double cost = np.cross_entropy(m, Y, A2);
            //costs.getData().add(new XYChart.Data(i, cost));
         
         // Back Prop
            //LAYER 2
            double[][] dZ2 = np.subtract(A2, Y);
            double[][] dW2 = np.divide(np.dot(dZ2, np.T(A1)), m);
            double[][] db2 = np.divide(dZ2, m);
            //LAYER 1
            double[][] dZ1 = np.multiply(np.dot(np.T(W2), dZ2), np.subtract(1.0, np.power(A1, 2)));
            double[][] dW1 = np.divide(np.dot(dZ1, np.T(X)), m);
            double[][] db1 = np.divide(dZ1, m);

            // G.D
            W1 = np.subtract(W1, np.multiply(0.01, dW1));
            b1 = np.subtract(b1, np.multiply(0.01, db1));

            W2 = np.subtract(W2, np.multiply(0.01, dW2));
            b2 = np.subtract(b2, np.multiply(0.01, db2));

            if (i % 400 == 0) {
                np.print("==============");
                np.print("Cost = " + cost);
                np.print("Predictions = " + Arrays.deepToString(A2));
                

                w1 = new Matrix(W1);
                B1 = new Matrix(b1);
                w2 = new Matrix(W2);
                B2 = new Matrix(b2);
              
            }
            
        }
        
//        np.print("===== W1 and b1 =====");
//        w1.print(0, 3);
//        B1.print(0, 3);
//        np.print("===== W2 and b2 =====");
//        w2.print(0, 3);
//        B2.print(0, 3);
        
        double[][] XF = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        XF = np.T(XF);
        
        // LAYER 1
        double[][] Z1 = np.add(np.dot(w1.getArray(), XF), B1.getArray());
        double[][] A1 = np.sigmoid(Z1);
        //LAYER 2
        double[][] Z2 = np.add(np.dot(w2.getArray(), A1), B2.getArray());
        double[][] A2 = np.sigmoid(Z2);
        
        np.print("Predictions Final = " + Arrays.deepToString(A2));
    }
}
