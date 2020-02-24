package algorithms.ai.ml.image;

public class TestM {

	public static void main(String[] args) {
		int h = 5;
		int w = 3;
		
		int [][] m = new int[h][w];
		  int v = 0;
		for(int i=0; i < m.length; i++) {
			for(int j=0; j < m[0].length; j++) {
				m[i][j] = v++;
			}
		}
		
		
		for(int i=0; i < m.length; i++) {
			for(int j=0; j < m[0].length; j++) {
				System.out.print(m[i][j]+", ");
			}
		}
		
	}
}
