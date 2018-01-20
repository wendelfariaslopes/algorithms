
public class Reines {
	static final int N = 4; // taille damier
	static int[] damier = new int[N];
	   // damier[i]  = position de la dame de la i-eme ligne
	

// les dames i et j ne sont-elles pas en prises ?	
	static boolean compatible(int i, int j) {
			return(damier[i] != damier[j] 
				&& i + damier[i]!= j + damier[j] 
				&& i - damier[i] != j - damier[j]);
	}

// verifier qu'il n'y a pas deux dames en prise ?
	static boolean test() {
		boolean flag = true;
			for (int i = 0; i <N; i++)	for (int j = i+1 ; j < N; j++) 
					flag = (flag && compatible(i,j));
			return(flag);  }

	static boolean testRapide() {
		boolean flag = true;
			for (int i = 0; i <N; i++)	
				for (int j = i+1 ; j < N; j++) 
					if (! compatible(i,j)) { 
						flag = false; break;
						}
			return(flag);
			
	}
	
// verifier que la dame i n'est pas en prise avec des dames des lignes
// au dessus	
	static boolean test(int i) {
		boolean flag = true;
		for (int j = 0 ; j < i; j++) flag = (flag && compatible(i,j));
		return(flag);
	}	

// afficher le damier
	static void affiche() {
		for (int i = 0; i < N; i++)
			System.out.print(damier[i]+" ");
		System.out.println();
	}

// enumeration r�cursive naive
	static void naif(int j){
		if (j >= N && test()) affiche();
		if (j >= N) return;
		for (int i = 0; i<N; i++) {
			damier[j] = i;
			naif(j+1); 
		  }
		return;
	}


	// recherche r�cursive naive
		static boolean naifTest(int j){
			if (j >= N && testRapide()) return(true);
			if (j >= N) return(false);
			for (int i = 0; i<N; i++) {
					damier[j] = i;
					if (naifTest(j+1)) return(true); 
				}
				return(false);
	}

// enumeration recursive elaguee
	static void explore(int j){
		if (j >= N) {
			affiche(); return; }
		for (int i = 0; i<N; i++) {
			damier[j] = i;
			if (test(j)) explore(j+1); 
		  }
		return;
	}

// recherche recursive elaguee
		static boolean exploreTest(int j){
			if (j >= N) return(true);
			for (int i = 0; i<N; i++) {
					damier[j] = i;
					if (test(j) && exploreTest(j+1)) return(true); 
				}
				return(false);
	}


	static public void main(String[] args) {
		naif(0);
// 	    naif(1);
//		if (exploreTest(0)) affiche();
//		if (naifTest(0)) affiche();
		// etc...
	}	

}
