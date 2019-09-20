package algorithms.math.fibonacci;

public class Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(fib(10));

	}
	
	
	static int fib(int n) {
	    double sqrt_5 = Math.sqrt(5);
	    double phi = (1 + sqrt_5) / 2;

	    double fib_n = Math.pow(phi, n) / sqrt_5;
	    return (int) (fib_n + 0.5);
	}


}
