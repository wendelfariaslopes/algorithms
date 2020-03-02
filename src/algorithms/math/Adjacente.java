<<<<<<< Upstream, based on origin/master
<<<<<<< Upstream, based on origin/master
/**
 * 
 */
package algorithms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * The Adjacency Matrix data structure implements the Graph interface. An Adjacency Matrix
 * supports the operations
 * addEdge(i,j), removeEdge(i,j), and hasEdge(i,j) in constant time per operation
 * inEdges(i), and outEdge(i) in O(n) time per operation
 * The space used by an Adjacency Matrix is O(n^2) 
 * @author Wendel F. Lopes
 *
 */
public class Adjacente {
	
	private int m[][]; 
	
	private Adjacente(int[][] m) {
		super();
		this.m = m;
	}

	/**
	 * Add a vertex means adds a conncetion between the object of row i with 
	 * column j of the object (in databases indicates that there is a connection 
	 * between one line ID_1 and other line ID_2)
	 * 
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j){
		this.m[i][j] = 1;
	}
	
	public void removeEdge(int i, int j) {
       this.m[i][j] = 0;
    }
    public boolean hasEdge(int i, int j) {
    	boolean has = false;
    	if(this.m[i][j]==1){
    		has = true;
    	}
        return has;
    }
    
    public List<Integer> outEdges(int i) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int j = 0; j < this.m.length; j++) 
            if (this.m[i][j]==1) edges.add(j);
        return edges;
    }
    public List<Integer> inEdges(int i) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int j = 0; j < this.m.length; j++)
            if (this.m[j][i]==1) edges.add(j);
        return edges;
    }
    
	public int[][] matrizAdjacente(int length){
		
		this.m = new int[length][length]; // matriz quadrada: length linhas X length colunas
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				m[i][j] = 1;
			}
		}
		
		return m;
	}

}
=======
/**
 * 
 */
package validator;

import java.util.ArrayList;
import java.util.List;

/**
 * The Adjacency Matrix data structure implements the Graph interface. An Adjacency Matrix
 * supports the operations
 * addEdge(i,j), removeEdge(i,j), and hasEdge(i,j) in constant time per operation
 * inEdges(i), and outEdge(i) in O(n) time per operation
 * The space used by an Adjacency Matrix is O(n^2) 
 * @author Wendel F. Lopes
 *
 */
public class Adjacente {
	
	private int m[][]; 
	
	private Adjacente(int[][] m) {
		super();
		this.m = m;
	}

	/**
	 * Add a vertex means adds a conncetion between the object of row i with 
	 * column j of the object (in databases indicates that there is a connection 
	 * between one line ID_1 and other line ID_2)
	 * 
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j){
		this.m[i][j] = 1;
	}
	
	public void removeEdge(int i, int j) {
       this.m[i][j] = 0;
    }
    public boolean hasEdge(int i, int j) {
    	boolean has = false;
    	if(this.m[i][j]==1){
    		has = true;
    	}
        return has;
    }
    
    public List<Integer> outEdges(int i) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int j = 0; j < this.m.length; j++) 
            if (this.m[i][j]==1) edges.add(j);
        return edges;
    }
    public List<Integer> inEdges(int i) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int j = 0; j < this.m.length; j++)
            if (this.m[j][i]==1) edges.add(j);
        return edges;
    }
    
	public int[][] matrizAdjacente(int length){
		
		this.m = new int[length][length]; // matriz quadrada: length linhas X length colunas
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				m[i][j] = 1;
			}
		}
		
		return m;
	}

}
>>>>>>> 74ea2df Changes
=======
/**
 * 
 */
package algorithms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * The Adjacency Matrix data structure implements the Graph interface. An Adjacency Matrix
 * supports the operations
 * addEdge(i,j), removeEdge(i,j), and hasEdge(i,j) in constant time per operation
 * inEdges(i), and outEdge(i) in O(n) time per operation
 * The space used by an Adjacency Matrix is O(n^2) 
 * @author Wendel F. Lopes
 *
 */
public class Adjacente {
	
	private int m[][]; 
	
	private Adjacente(int[][] m) {
		super();
		this.m = m;
	}

	/**
	 * Add a vertex means adds a conncetion between the object of row i with 
	 * column j of the object (in databases indicates that there is a connection 
	 * between one line ID_1 and other line ID_2)
	 * 
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j){
		this.m[i][j] = 1;
	}
	
	public void removeEdge(int i, int j) {
       this.m[i][j] = 0;
    }
    public boolean hasEdge(int i, int j) {
    	boolean has = false;
    	if(this.m[i][j]==1){
    		has = true;
    	}
        return has;
    }
    
    public List<Integer> outEdges(int i) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int j = 0; j < this.m.length; j++) 
            if (this.m[i][j]==1) edges.add(j);
        return edges;
    }
    public List<Integer> inEdges(int i) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int j = 0; j < this.m.length; j++)
            if (this.m[j][i]==1) edges.add(j);
        return edges;
    }
    
	public int[][] matrizAdjacente(int length){
		
		this.m = new int[length][length]; // matriz quadrada: length linhas X length colunas
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				m[i][j] = 1;
			}
		}
		
		return m;
	}

}
>>>>>>> 98f0a9b Last changes places
