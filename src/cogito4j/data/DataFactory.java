package cogito4j.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DataFactory {
	
	private static double[][] X;
	private static double[][] Y;

	// criando dados simulados para testes
	public static Map<Integer,String> createArtificialTrainingDataSet(int samples, int numBytes) {
			
			Map<Integer,String> modelMap = new HashMap<>();
			
			Random r = new Random();

			X = new double[samples][numBytes];
			Y = new double[samples][1];

			for (int m = 0; m < samples; m++) {
				
				if (m % 2 == 0 || m % 5 == 0 || m % 7 == 0) { // todos as posicoes no arrays que sao gatos sao pares ou divididos por 7 ...
									// acao arbitraria.
					// Gerando dados in bytes aleatorios
					for (int n = 0; n < numBytes; n++) {
						if (n >= numBytes / 2) {
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
					modelMap.put(m, "Negative");
				}
			}
			
			return modelMap;
		}

	public static double[][] getX() {
		return X;
	}


	public static void setX(double[][] x) {
		X = x;
	}


	public static double[][] getY() {
		return Y;
	}


	public static void setY(double[][] y) {
		Y = y;
	}
	
	
	

}
