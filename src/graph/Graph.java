package graph;

public class Graph {

	public static void main(String[] args) {
		
		String vertices[] = {"A","B","C","D","E","F"};// identifica objeto abstrato tipos
		int arestas[][] = {{0,1},{0,4},{1,4},{1,2},{2,3},{3,4},{3,5}};// identifica o no relativo ao array valores dados.
		int unid[] = new int [2*arestas.length];
		
		int ma[][] = new int[vertices.length][vertices.length];
		
		System.out.println("desmembrar em um array unidimensional");
		int a = 0;
		for(int k = 0; k < arestas.length; k++){
			for(int j = 0; j < arestas[k].length; j++){
				System.out.print(arestas[k][j]);
				unid[a] = arestas[k][j];
				a++;
			}
		}
		
		int l = 0;
		int c = 0;
		for(int i = 0; i < arestas.length; i++){
	
			c= unid[2*i+1];
			l = unid[2*i];
			ma[l][c]=1;
			//System.out.println(ma[l][c]);
		}

		
		System.out.println("\nMatriz Adjacente");
	
		System.out.print("   ");
		for(int i = 0; i<vertices.length; i++){
			System.out.print(" "+vertices[i]);
		}
		System.out.println("\n  -------------");
		for(int i = 0; i < ma.length; i++){
			System.out.print(vertices[i]+" |");
			for(int j = 0; j < ma.length; j++){
				System.out.print(" "+ma[i][j]);
			}
			System.out.println("");
		}
//		
	
	}

}
