package algorithms.scientometrics;

import java.util.Map;

/**
 * 
 * Scientometrics is the study of measuring and analysing science, technology and innovation.
 * 
 *  The h-index is an author-level metric that attempts to measure both the productivity and citation impact of the publications of a scientist or scholar. 
 *  h-index(f) = max min(f(i),i)
 *  Example: one researcher whit 5 publications [A,B,C,D,E] with [10,8,5,4,3]
 *  f(A)=10, f(B)=8, f(C)=5, f(D)=4, f(E)=3　→ h-index=4
 *  
 *  The h-index serves as an alternative to more traditional journal impact factor metrics in the evaluation of the impact of the work of a particular researcher.
 *  
 * 
 * @author wendellopes
 *
 */
public class PriceModel {

	public static int h_index(Map<String,Integer> citations){
		
		int h = 0;
		int p = 0; // number of publications
		for (String c : citations.keySet()) {
			p++;
		}
		
		return h;
	}
	
	
}
