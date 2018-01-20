package math;

public class PearsonCorrelation {

	public static double calculeCoefficient(double[] x, double[] y) {

		double maX = 0;
		double maY = 0;
		double r = 0;
		int length = 1;

		if (x.length <= y.length) {
			length = x.length;
		} else {
			length = y.length;
		}

		System.out.println(length);
		
		for (int i = 0; i < length; i++) {
			System.out.println(x[i]+","+y[i]);
			maX += x[i];
			maY += y[i];
		}

		maX = maX / length;
		maY = maY / length;
		
		System.out.println(maX);
		System.out.println(maY);
		
		double [] desvioX = new double[length];
		double [] desvioY = new double[length];
		double dvXdvY = 0;
		
		double [] varX = new double [length];
		double [] varY = new double [length];
		double sdX = 0;
		double sdY = 0;
		
		for (int i = 0; i < length; i++) {
			
			desvioX[i] = x[i] - maX;
			desvioY[i] = y[i] - maY;
			varX[i] = Math.pow(desvioX[i], 2);
			varY[i] = Math.pow(desvioY[i], 2);
			sdX +=varX[i];
			sdY +=varY[i];
			
			dvXdvY += desvioX[i] * desvioY[i];
			System.out.println(desvioX[i]+","+desvioY[i]);
		}
		
		double covariancia = dvXdvY/(length-1);
		sdX = Math.sqrt(sdX/(length-1));
		sdY = Math.sqrt(sdY/(length-1));
		
		System.out.println(covariancia/(sdX*sdY));
		
		

		return r;
	}
	
	public static void main (String [] args){
		
		double[] x = {1.1,1.2,1.3,1.4,1.5};
		double[] y = {1.01,1.2,1.3,1.4,1.5};
		
		double pc = PearsonCorrelation.calculeCoefficient(x, y);
		
		System.out.println(pc);
		
	}

}
