package matriz;

public class Matriz2 {
	  public static void main(String[] args) {
	    int n = 6;
	    int i, j, m[][] = new int[n][n];

	    for (i=0; i<n; i++) {
	      for (j=0; j<n; j++) {
	        if (i == j) // diagonal principal
	           m[i][j] = 0;
	        else if ((i+j) == (n-1)) // diagonal secundária
	                m[i][j] = 1;
	// sorteia um número aleatório no intervalo de 2 até 5
	             else m[i][j] = (int)(Math.round(Math.random() * 2) + 3); //
	      }
	    }

	    for (i=0; i<n; i++) {
	      System.out.printf("%da. linha: ", (i+1));
	      for (j=0; j<n; j++) {
	        System.out.printf("%d ", m[i][j]);
	      }
	      System.out.printf("\n");
	    }
	  }
}



