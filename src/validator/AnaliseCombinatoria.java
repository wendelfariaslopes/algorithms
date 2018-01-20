/**
 * 
 */
package validator;

import java.math.BigInteger;

/**
 * @author Wendel F. Lopes
 */
public class AnaliseCombinatoria {
	
	public void permutation(String sequence){
		
		int l = sequence.length();
		
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
		
		ac.permutation("AA");
		
	}
	
	
	/*
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			long start = System.currentTimeMillis();
			fat(new BigInteger("100000"));
			long end = System.currentTimeMillis();
			System.out.println("Took " + (end - start) + "ms!");
		}
	}

	public static BigInteger fat(final BigInteger n) {

		BigInteger f = BigInteger.ONE;
		BigInteger i = BigInteger.ONE;
		while (i.compareTo(n) < 0) {
			i = i.add(BigInteger.ONE);
			f = f.multiply(i);
		}
		return f;
	}
	
	*/

}
