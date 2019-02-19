package algorithms.frauddetection;

import java.math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import math.PearsonCorrelation;

public class BenfordLaw {

	final private double[] dbl = { 30.8, 19.3, 13.0, 9.9, 7.4, 5.9, 5.2, 4.4, 4.1 };

	// return the leading digit of x, assuming x is positive
	public static int leadingDigit(int x) {
		while (x >= 10) {
			x = x / 10;
		}
		return x;
	}
	
	public static double leadingDigit(double x) {
		while (x >= 10) {
			x = x / 10;
		}
		return x;
	}
	


	public static void main(String[] args) {
		
		
		int n = 1000000;
		String[] s = new String[n];
		
		
		//double[] x = {1,2,3,4,5,6};
		//double[] y = {1,2,3,4,5,6};
		
		double[] x = new double[n];
		double[] y = new double[n];
		
		
		for(int r=0; r < s.length; r++) {
			s[r]=Math.random()+"";
			x[r]=Math.random();
			y[r]=-x[r];
		}	

		
//		double[] d = frequency(s);
//		double sum = 0.0;
//		
//		for (int i =1; i<  d.length;i++) {
//			System.out.println(i+") "+d[i]);
//			sum+=d[i];
//		}
//		System.out.println(sum);
		
		

		
		PearsonCorrelation.calculeCoefficient(x, y);
		
	}
	
	public static <T> double[] frequency(T[] samples){
	
		double[] d = new double[10];
		int n = samples.length;
	
		for (int i = 0; i < n; i++) {
			int digit = firstDigitOf(samples[i]+"");
			//System.out.println(digit);
			d[digit]++;
		}
		
		for(int i = 0; i<d.length;i++) {
			d[i]=d[i]/n;
		}
		return d;
	}
	
	// returns the first nonzero digit of a string, 0 if no such digit found
    public static int firstDigitOf(String token) {
        for (char ch : token.toCharArray()) {
            if (ch >= '1' && ch <= '9') {
                return ch - '0';
            }
        }
        return 0;
    }
   

}
