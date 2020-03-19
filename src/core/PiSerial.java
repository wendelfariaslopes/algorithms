package core;

public class PiSerial {
	
	private static double f( double x ) {
		return 4.0 / ( 1 + x*x );
	}
	
	public static double calculate( int N ) {

		if ( N==0 ) N = 1000000;
		
		double sum = 0;
		double deltaX = 1.0/N;
		
		//--- iterate ---
		for ( int i=0; i< N; i++ ) 
			sum += f( i * deltaX );
		
		return sum;
	}
}
