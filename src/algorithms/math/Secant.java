package algorithms.math;

public class Secant {
	
  public static void main(String argv[]) {
    double del = 1e-6; 
    double dx = 1, x =0, x1=1;
    int n = 20;
    x = secant(n, del, x,x1, dx);
    System.out.println("Root obtained: " + x);
    
  }

  public static double secant(int n, double del,
    double x,double x1, double dx) {
    int k = 0;
    double x2=0;
    while ((Math.abs(dx)>del) && (k<n) && f(x2)!=0) {
      double d = f(x1)-f(x);
       x2 = x1-f(x1)*(x1-x)/d;
      x  = x1;
      x1 = x2;
      dx = x1-x;
      k++;
    }
    if (k==n) System.out.println("Convergence not" +
      " found after " + n + " iterations");
    System.out.println("iterations: " + k);
    return x1;
  }


  public static double f(double x) {
    return 230*x*x*x*x+18*x*x*x+9*x*x-221*x-9;
  }
}
/*Root obtained: -0.04065928831557162
Press any key to continue...*/