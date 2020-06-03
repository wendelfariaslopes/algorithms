
package algorithms.math;

import java.math.BigInteger;

/**
 * @author Wendel F. Lopes
 */
public class AnaliseCombinatoria {
	
	public void permutation(String sequence){
		
		int l = sequence.length();
		
		numberPermutation(l);
		
		if (l < 13) {
			
			for (int i = 0; i < l; i++) {
				
				for (int j = 0; j < l; j++) {
					
					for (int k = 0; k < l; k++) {
						
						System.out.println(i+" - " + sequence.charAt(i)+ sequence.charAt(j)+ sequence.charAt(k));
					
					}
					
				}
			}
			
		} else {
			
			System.out.println("The number of caracteres is big that accepted");
			
		}
	
	}
	
	private void numberPermutation(int length){
		int p = 1;
		for (int i = length; i > 1; i--) {
			p *= i;
			System.out.print(""+i+"*" );
		}
		System.out.println("");
		System.out.print(length +"! = "+p);
		System.out.println("");
	}
	
	public static void main (String[]args){
		
		AnaliseCombinatoria ac = new AnaliseCombinatoria();
		
		ac.permutation("123A");
		
	}
	
	

}
