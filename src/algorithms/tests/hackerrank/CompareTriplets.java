package algorithms.tests.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompareTriplets {

	public static void main(String[] args) {
		
	}
	
	public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
		int points[] = new int[2];
		
		for(int i = 0; i < a.size(); i++)
			if(a.get(i)>b.get(i)) {
				points[0]+=1;
			
			if(a.get(i)<b.get(i))
				points[0]+=1;
		}
		
		//List<Integer> p = Arrays.asList(points);
		
    }

}
