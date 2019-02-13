package algorithms.tests.hackerrank;

import java.util.HashMap;
import java.util.Map;

public class FindPairs {

	public static void main(String[] args) {
		int[] ar = {10, 20, 20, 10, 10, 30, 50, 10, 20,10, 20, 20, 10, 10, 30, 50, 10, 20};
		
		Map<Integer,Integer> pairs = new HashMap<>();
		int n = ar.length;
		for(int i=0;i<n;i++) {
			pairs.put(ar[i], 0);
		}	
		
		for(int i=0;i<n;i++) {
			for(Integer k: pairs.keySet()) {
				if(ar[i]==k) {
					pairs.put(k, pairs.get(k)+1);
				}
			}
		}
		int p = 0;
		for(Integer k: pairs.keySet()) {
			p+=pairs.get(k)/2;
		}


	}
	
	 

}
