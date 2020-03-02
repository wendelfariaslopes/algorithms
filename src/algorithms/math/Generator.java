<<<<<<< Upstream, based on origin/master
package algorithms.math;
=======
package math.random;
>>>>>>> 74ea2df Changes

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Generator {
	
	public static double betweenMinMax(double min,double max) {
		return min + Math.random() * (max - min);
	}

	public static int betweenMinMax(int min, int max) {
		Random r = new Random();
		return min + r.nextInt(max - min + 1);
	}
	
	public static IntStream betweenMinMax(int quantity, int min, int max) {
		Random r = new Random();
		return r.ints(quantity, min, max);
	}
	
	public static DoubleStream betweenMinMax(int quantity, double min, double max) {
		Random r = new Random();
		return r.doubles(quantity, min, max);
	}
	
	public static IntStream gaussian(double std, int mean, int sampleSize) {

		Random r = new Random();
		int[] arrayInt = new int[sampleSize];
		for(int i = 0; i < sampleSize; i++) {
			double val = r.nextGaussian() * std + mean;
			int millisDelay = (int) Math.round(val);
			arrayInt[i]=millisDelay;
		}
		 return IntStream.of(arrayInt);
	}
	
	public static void main(String [] args) {
		
		Random r = new Random();
		int sampleSize = 10000;
		/*
		 * The nextGaussian() method returns random numbers with a mean of 0 and a standard deviation of 1.
		 */
		double std = 10;
		int mean = 10;
		List<Integer> list = new ArrayList<>();
		
		List<Integer> list68 = new ArrayList<>();
		List<Integer> list95 = new ArrayList<>();
		List<Integer> list99 = new ArrayList<>();
		
		
		
		 IntStream is = gaussian(std, mean, sampleSize);
		 is.forEach(x -> {list.add(x);});
		 
		 long n = list.size();
		 
		 list.forEach(x -> {
			 if(x > (mean - std) && x <= (mean + std))
				 list68.add(x);
			 if(x >= (mean - 2*std) && x < (mean + 2*std))
				 list95.add(x);
			 if(x > (mean - 3*std) && x <= (mean + 3*std))
				 list99.add(x);
			
		 });
		
		 DecimalFormat df = new DecimalFormat("0.0000");
		 
		 double a68 = ((double) list68.size())/n;
		 double a95 = ((double)list95.size())/n;
		 double a99 = ((double)list99.size())/n;
		 
		 System.out.println("68% prob: "+df.format(a68));
		 System.out.println("95% prob: "+df.format(a95));
		 System.out.println("99% prob: "+df.format(a99));
		
		
	}
	
	

}
	
