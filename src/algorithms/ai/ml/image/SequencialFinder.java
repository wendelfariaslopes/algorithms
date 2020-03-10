package algorithms.ai.ml.image;

public class SequencialFinder {

	public static void main(String[] args) {
		byte[] big = new byte[] { 1, 2, 3, 0, 4, 5, 6, 7, 1, 8, 9, 0, 0, 1, 2, 3, 4, 2, 3, 0, 4, 5, 6, 7, 0, 3, 0, 4, 5,
				6, 7, 0, 3, 0, 4, 5, 6, 12, 24, 22, 7, 0, 8, 9, 0, 0, 1, 7, 0, 6, 0, 8, 9, 0, 0, 1, 7, 1, 8, 9, 0, 0, 1, 7, 0, 9, 9,
				0, 0, 1, 7, 0, 8, 9, 0, 0, 1, 7, 0, 8, 9, 0, 0, 1, 7, 0, 8, 9, 0, 0, 1, 7, 0, 8, 9, 0, 0, 1 };
		
		
		
		byte[] small = new byte[] { 7, 0, 8, 9, 0, 0, 1 };
		
		
		
		int samples = 100;
		
		int[] bigInt = new int[samples];
		int[] smallInt = new int[] { 9,9,9,9,9,9,9 };
		int inceptionIn = 57;
		
		for(int i = 0; i < samples; i++) {
			if(i == inceptionIn) {
				for(int j=0; j< smallInt.length;j++) {
					bigInt[i]= smallInt[j];
					++i;
				}
			}
		}
		
		//System.out.println(Arrays.toString(bigInt));
		
		//System.out.println(indexOf(big, small));
		System.out.println(indexOf(bigInt, smallInt));



	}

	public static int indexOf(byte[] big, byte[] small) {
		for (int i = 0; i < big.length - small.length + 1; ++i) {
			boolean found = true;
			for (int j = 0; j < small.length; ++j) {
				if (big[i + j] != small[j]) {
					found = false;
					break;
				}
			}
			if (found)
				return i;
		}
		return -1;
	}
	
	public static int indexOf(int[] big, int[] small) {
		for (int i = 0; i < big.length - small.length + 1; ++i) {
			boolean found = true;
			for (int j = 0; j < small.length; ++j) {
				if (big[i + j] != small[j]) {
					found = false;
					break;
				}
			}
			if (found)
				return i;
		}
		return -1;
	}
	
	
}
