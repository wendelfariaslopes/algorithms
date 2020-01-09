package algorithms.tests.hackerrank;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;



public class PlusMinus {

	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat("0.000000");
	
		int ar[] = {-4, 3, -9, 0, 4, 1};
		double p=0.0,n=0.0,z=0.0;
		
		//df.setRoundingMode(RoundingMode.CEILING);
		for (int i=0; i < ar.length; i++) {
			if(ar[i]==0)
				z+=1;
			if(ar[i]>0)
				p+=1;
			if(ar[i]<0)
				n+=1;
		}
		System.out.println(df.format(p/ar.length));
		System.out.println(df.format(n/ar.length));
		System.out.println(df.format(z/ar.length));

	}

}
