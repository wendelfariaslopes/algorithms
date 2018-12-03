package algorithms.investment;
import java.text.DecimalFormat;

public class TaxRate {
	
	private static final double DAY = 1;
	private static final double WEEK = 7*DAY;
	private static final double MONTH = 30*DAY;
	private static final double YEAR = 365*DAY;

	public static void main(String[] args) {
		
		DecimalFormat df = new DecimalFormat("0.0000");
		
		double VP = 1000.0;
		double VF = 1157.63;
		double i = 5.0;
		int n = 3;
		
		System.out.println("Calculo do valor presente ="+ df.format(calcVP(VF, i, n)));
		System.out.println("Calculo dos periodos = "+df.format(calcPeriodos(VP, VF, i)));
		System.out.println("Convert Juros "+df.format(convertRates(12.6825, MONTH, DAY)));
		

	}
	
	public static double calcVF(double VP, double i, int n) {
		return VP*Math.pow(1+(i/100), n);
	}
	
	public static double calcVP(double VF, double i, int n) {
		return VF/Math.pow(1+(i/100), n);
	}
	
	public static double calcInterest(double VP, double VF, int n) {
		return Math.pow(VF/VP, 1/n) - 1;
	}
	public static double calcPeriodos(double VP, double VF, double i) {
		return Math.log(VF/VP)/Math.log(1+(i/100));
	}
	
	public static double convertRates(double i, double from, double to) {
		return (Math.pow(1+(i/100), to/from)-1)*100;
	}
	
	
	
	

}
