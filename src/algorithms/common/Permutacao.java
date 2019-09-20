package algorithms.common;
/**
 * 
 */


import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 * @author Wendel F. Lopes
 *
 */
public class Permutacao {
	
	private int[] N ;
    private int n ;
    private String[] entrada ;
    private int[] enterInt;
    private int r ;
	
	public static void main(String[] args) {
		
		String[] str =  {"uva", "pera", "laranja", "manga", "goiaba"};
		int[] intArray = {1,2,3,4,5};
	    
		String[] saida ;
		int[] exitInt;

	    Permutacao p = new Permutacao(str, 2) ;

	    while (p.hasNext()) {
	        saida = p.next();

	        for ( String e : saida ) {
	            System.out.print(e + ",") ;
	        }
	        System.out.println() ;
	    }
	    
	    
	   // Permutacao p = new Permutacao(intArray, 2) ;
	    
	}
	
	
    public Permutacao(String[] entrada, int r) {
        this.r = r ;
        this.n = entrada.length ;
        this.N = new int[r+1] ;
        this.entrada = entrada ;
        
    }
    
    public Permutacao(int[] entrada, int r) {
        this.r = r ;
        this.n = entrada.length ;
        this.N = new int[r+1] ;
        this.enterInt = entrada ;
    }
 
    public boolean hasNext() {
        return this.N[this.r] == 0 ;
    }
 
    public String[] next() {
        
    	String[] saida = new String[this.r] ;
        int i, j ;
 
        for(i=0, j=this.r-1; i < this.r; i++) {
            saida[j] = entrada[this.N[i]] ;
            j-=1 ;
        }
 
        this.N[0] += 1 ;
 
        for(i=0; i < this.r; i++) {
            if(this.N[i] == this.n) {
                this.N[i] = 0;
                this.N[i+1] += 1 ;
            }
        }
        return saida ;
    }
    
    public int[] nextInt() {
        
    	int[] saida = new int[this.r] ;
        int i, j ;
 
        for(i=0, j=this.r-1; i < this.r; i++) {
            saida[j] = enterInt[this.N[i]] ;
            j-=1 ;
        }
 
        this.N[0] += 1 ;
 
        for(i=0; i < this.r; i++) {
            if(this.N[i] == this.n) {
                this.N[i] = 0;
                this.N[i+1] += 1 ;
            }
        }
        return saida ;
    }


}
