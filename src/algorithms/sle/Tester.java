package algorithms.sle;
import Jama.Matrix;
public class Tester {

	public static void main(String[] args) {
		double[][] lhsArray = {{3, 2, -1}, {2, -2, 4}, {-1, 0.5, -1}};
		double[] rhsArray = {1, -2, 0};
		
		Matrix lhs = new Matrix(lhsArray);
		Matrix rhs = new Matrix(rhsArray, 3);
		
		Matrix ans = lhs.solve(rhs);
		
		ans.print(0, 1);
	}

}
