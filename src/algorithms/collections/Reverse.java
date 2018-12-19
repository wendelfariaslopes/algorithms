package algorithms.collections;

import java.util.Arrays;

public class Reverse {

	public static void main(String[] args) {
		char[] c = {'h','e','l','l','o'};
		System.out.println(Arrays.toString(c));
		System.out.println(Arrays.toString(reverse(c)));
	}
	
	public static char[] reverse(char[] c) {
		int size = c.length;
		
		char[] rc = new char[size];
		
		for(int i = size-1; i>=0; i--) {
			rc[i] = c[size-1-i];
		}
		
		return rc;
	}

}
