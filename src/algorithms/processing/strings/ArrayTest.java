package algorithms.processing.strings;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class ArrayTest {

	public static void main(String[] args) {
		
		int[] array = {1,2,3,3,2,3,1,1,2,3};
		int[] arrayN = {1,2,3};
		
		Set<Integer> setN = new TreeSet<>();
		for(int i:array)
			setN.add(i);
		
		System.out.println(setN);
		array = Arrays.copyOf(array, setN.size());
		
		System.out.println(array.length);
		
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(arrayN));
		
		for(int i=0;i<array.length;i++) {
			System.out.println(array[i]==arrayN[i]);
		}
		
		
	}

}
