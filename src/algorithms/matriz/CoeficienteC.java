package algorithms.matriz;

import javax.swing.JOptionPane;

import Jama.Matrix;

public class CoeficienteC {

	public static void main(String[] args) {

		int linhas = 7; // Numero total de linhas da variavel x
		int grau = 3;	// Tipo de regressao polinominal desejada 2 - linear, 3 - quadratica, 4 - cubica, 5 - 

		double[] xi = new double[linhas];
		double[] yi = new double[linhas];

		double[][] matrizX = new double[linhas][grau];
		double[][] matrizY = new double[linhas][grau];

		for (int k = 0; k < xi.length; k++) {

			xi[k] = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Digite o valor de X[" + (k + 1) + "]"));
			yi[k] = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Digite o valor de Y[" + (k + 1) + "]"));

		}

		for (int i = 0; i < matrizX.length; i++) {
			System.out.println(" X[" + (i + 1) + "] = " + xi[i] + " e Y["
					+ (i + 1) + "] = " + yi[i]);

			for (int coluna = 0; coluna < grau; coluna++) {

				switch (coluna) {
				case 0:
					matrizX[i][coluna] = 1;
					matrizY[i][coluna] = yi[i];
					break;
				case 1:
					matrizX[i][coluna] = Math.pow(xi[i], 1);
					break;
				case 2:
					matrizX[i][coluna] = Math.pow(xi[i], 2);
					;
					break;
				case 3:
					matrizX[i][coluna] = Math.pow(xi[i], 3);
					;
					break;
				case 4:
					matrizX[i][coluna] = Math.pow(xi[i], 4);
					;
					break;
				case 5:
					matrizX[i][coluna] = Math.pow(xi[i], 5);
					;
					break;
				case 6:
					matrizX[i][coluna] = Math.pow(xi[i], 6);
					;
					break;
				case 7:
					matrizX[i][coluna] = Math.pow(xi[i], 7);
					;
					break;
				case 8:
					matrizX[i][coluna] = Math.pow(xi[i], 8);
					;
					break;
				}
			}
		}

		Matrix Y = new Matrix(matrizY);
		Matrix A = new Matrix(matrizX);

		A.print(5, 3);
		Y.print(5, 3);

		Matrix psd = pseudoInversa(A);
		psd.print(5, 3);

		Matrix coef = multiplicarMatriz(psd, Y);
		coef.print(5, 3);

		int coluna = coef.getColumnDimension();
		int linha = coef.getRowDimension();

		double[][] equacao = coef.getArray();

		System.out.println(coluna);
		System.out.println(linha);

		switch (linha) {
		case 2:
			System.out
					.println(FormataDecimal.arredondarParaBaixo(equacao[1][0],
							3)
							+ "x  "
							+ ""
							+ FormataDecimal.arredondarParaBaixo(equacao[0][0],
									3) + "");
			break;
		case 3:
			System.out
					.println(FormataDecimal.arredondarParaBaixo(equacao[2][0],
							3)
							+ "x^2  "
							+ FormataDecimal.arredondarParaBaixo(equacao[1][0],
									3)
							+ "x  "
							+ FormataDecimal.arredondarParaBaixo(equacao[0][0],
									3) + "");
			break;
		case 4:
			System.out
					.println(FormataDecimal.arredondarParaBaixo(equacao[3][0],
							3)
							+ "x^3  "
							+ FormataDecimal.arredondarParaBaixo(equacao[2][0],
									3)
							+ "x^2  "
							+ FormataDecimal.arredondarParaBaixo(equacao[1][0],
									3)
							+ "x  "
							+ FormataDecimal.arredondarParaBaixo(equacao[0][0],
									3) + "");
			break;
		}

		/*
		 * 
		 * double[][] matrizA = { { 1, -1, 1 }, { 1, 1, 1 }, { 1, 2, 4 }, { 1,
		 * 3, 9 }, { 1, 5, 25 } }; // declarando uma matriz de valores
		 * 
		 * double [][] matrizY = {{2,0,0},{1,0,0},{1,0,0},{0,0,0},{3,0,0}};
		 * 
		 * Matrix Y = Matrix.random(5, 3); // Para analisar um problema no
		 * futuro convem introduzir variaveis aleatoria //indicadoras que nos
		 * fornecem um metodo conveniente para converter a probabilidade em
		 * expectativas Matrix Y2 = new Matrix(matrizY); Matrix A = new
		 * Matrix(matrizA); // instancia um objeto do tipo Matrix que recebe a
		 * 'valores' em seu construtor
		 * 
		 * Matrix psd = pseudoInversa(A);
		 * 
		 * psd.print(5, 3);
		 * 
		 * Matrix coef = multiplicarMatriz(psd, Y2); Matrix coef2 =
		 * multiplicarMatriz(psd, Y);
		 * 
		 * coef.print(5, 3); coef2.print(5, 3);
		 */

	}

	public static Matrix multiplicarMatriz(Matrix mA, Matrix mB) {

		double[][] A = mA.getArray();
		double[][] B = mB.getArray();

		int row, column, i; // faz o produto das matrizes

		double aux;

		double c[][] = new double[A.length][B[0].length];

		// multiplicação das matrizes
		for (row = 0; row < c.length; row++) {

			for (column = 0; column < c[row].length; column++) {

				aux = 0;

				for (i = 0; i < A[row].length; i++) {

					try {

						aux = aux + A[row][i] * B[i][column];

					} catch (Exception e) {

						e.printStackTrace();

					}

				}

				c[row][column] = aux;

			}

		}

		Matrix produto = new Matrix(c); // define matriz produto

		return produto;
	}

	public static Matrix multiplicarAtA(Matrix A) {

		A.print(5, 1); // imprime matriz matriz.print(espaço entre as colunas,
						// numero de casas decimais)

		Matrix At = A.transpose(); // transposta da matriz

		At.print(5, 1);

		Matrix multAAt = multiplicarMatriz(At, A);

		multAAt.print(5, 1);

		// Matrix pseudo = multiplicarMatriz(Ai, At);

		return multAAt;
	}

	public static Matrix pseudoInversa(Matrix A) {

		Matrix At = A.transpose(); // transposta da matriz
		Matrix Ai = multiplicarMatriz(At, A).inverse();
		Matrix pseudo = multiplicarMatriz(Ai, At);

		return pseudo;
	}

	public static Matrix calcularMatrizB(Matrix At, Matrix Y) {

		Matrix B = multiplicarMatriz(At, Y);

		return B;
	}

	public static Matrix coeficiente(Matrix A, Matrix Y) {

		Matrix C = A.solve(Y);

		C.print(5, 3);

		return C;
	}

}
