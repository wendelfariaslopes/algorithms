package math;

import java.text.DecimalFormat;

public class PearsonCorrelation {

	public static double calculeCoefficient(double[] x, double[] y) {
		
		
		DecimalFormat df = new DecimalFormat("0.000");

		double maX = 0;
		double maY = 0;
		double r = 0;
		int length = 1;

		if (x.length <= y.length) {
			length = x.length;
		} else {
			length = y.length;
		}

		System.out.println("   X   ,     Y   ");
		for (int i = 0; i < length; i++) {
			System.out.println(df.format(x[i])+"  ,  "+df.format(y[i]));
			maX += x[i];
			maY += y[i];
		}

		maX = maX / length;
		maY = maY / length;
		
		System.out.println("ma X  ,   ma Y");
		System.out.println(df.format(maX)+"  , "+df.format(maY));
		
		
		double [] desvioX = new double[length];
		double [] desvioY = new double[length];
		double dvXdvY = 0;
		
		double [] varX = new double [length];
		double [] varY = new double [length];
		double sdX = 0;
		double sdY = 0;
		
		System.out.println("Diff X  , Diff Y");
		for (int i = 0; i < length; i++) {
			
			desvioX[i] = x[i] - maX;
			desvioY[i] = y[i] - maY;
			varX[i] = Math.pow(desvioX[i], 2);
			varY[i] = Math.pow(desvioY[i], 2);
			sdX +=varX[i];
			sdY +=varY[i];
			
			dvXdvY += desvioX[i] * desvioY[i];
			System.out.println(df.format(desvioX[i])+"  ,  "+df.format(desvioY[i]));
		}
		double c = dvXdvY/length;
		double covariancia = dvXdvY/(length-1);
		sdX = Math.sqrt(sdX/(length-1));
		sdY = Math.sqrt(sdY/(length-1));
		
		double pearson = covariancia/(sdX*sdY);
		
		System.out.println("Pearson Correlation = "+df.format(pearson));
		
		System.out.println("Covar in Pearson Correlation = "+df.format(c));
		
		return r;
	}
	
	public static void main (String [] args){
		
		double[] x = {1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9,2.0,2.1,2.2};//se considerarmos os valores menores faremos uma reducao aos casos conhecidos
		double[] y = { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61};// se consideramos os maiores faremos uma inducao de valores para os menores, baseado na media de crescimento ou decrescimento
		// 1, 2, 3, ... a inducao nos leva ao proximo numero igual a 4
		// entretanto se outro vetor apresenta covariacao
		// o que deveremos acrecentar?
		
		PearsonCorrelation.calculeCoefficient(x, y);
		covariance(x,y);
		
		
	}
	
	public static double covariance(double[] x, double[] y) {
		
		DecimalFormat df = new DecimalFormat("0.000");
		
		double somaX = 0;
		double somaY = 0;
		int length = 1;

		if (x.length <= y.length) {
			length = x.length;
		} else {
			length = y.length;
		}

		// System.out.println(length);

		for (int i = 0; i < length; i++) {
			// System.out.println(x[i] + "," + y[i]);
			somaX += x[i];
			somaY += y[i];
		}
		double maX = somaX / length;
		double maY = somaY / length;

		double somaProduto = 0;
		for (int i = 0; i < length; i++) {
			somaProduto += x[i] * y[i];
		}
		// System.out.println(somaProduto);

		double cov = somaProduto / length - maX * maY;

		System.out.println("Covar Tested = "+df.format(cov));

		return cov;
	}


}
