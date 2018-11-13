package lexical;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Levenshtein {

	public static int distance(String a, String b) {
		a = a.toLowerCase();
		b = b.toLowerCase();
		// i == 0
		int[] costs = new int[b.length() + 1];
		for (int j = 0; j < costs.length; j++)
			costs[j] = j;
		for (int i = 1; i <= a.length(); i++) {
			// j == 0; nw = lev(i - 1, j)
			costs[0] = i;
			int nw = i - 1;
			for (int j = 1; j <= b.length(); j++) {
				int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
						a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
				nw = costs[j];
				costs[j] = cj;
			}
		}
		return costs[b.length()];
	}

	public static void main(String[] args) {
		
		String [] words = { "hannah", "hannaH", "Hannah", "HANNAH", "Hannah", "HannaH",
				"Hannah", "hannaH", "Hannah", "HAnnah", "roset", "roset",
				"kitt", "kitten", "saturday", "saturday", "roset", "roset",
				"kiten", "kitten", "saturday", "saturday", "roset", "roset"};
		
		int length = 2 * words.length;
		String [] data = new String[length];
		
		for (int i = 0; i < length; i++) {
			if(i%2==0){
				data[i] = words[i/2];
			}else{
				
				try {
					data[i] = createMD5(words[i/2]);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		
		for (int i = 0; i < data.length; i += 4) {
			System.out.print("distance das Palavras (" + data[i] + ", " + data[i + 2] + ") = " + distance(data[i], data[i + 2]));
			System.out.println(" ---> distance do MD5(" + data[i + 1] + ", " + data[i + 3] + ") = " + distance(data[i+1], data[i + 3]));
		}
	}

	public static void equalToken(String str1, String str2){
		
	
		
	}
	
	public static String createMD5(String str) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		byte[] array = md.digest(str.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
		
		return sb.toString();
	}

 	public static double distance2(String s1, String s2) {
		if (s1.equals(s2)) {
			return 0;
		}

		if (s1.length() == 0) {
			return s2.length();
		}

		if (s2.length() == 0) {
			return s1.length();
		}

		// create two work vectors of integer distances
		int[] v0 = new int[s2.length() + 1];
		int[] v1 = new int[s2.length() + 1];
		int[] vtemp;

		// initialize v0 (the previous row of distances)
		// this row is A[0][i]: edit distance for an empty s
		// the distance is just the number of characters to delete from t
		for (int i = 0; i < v0.length; i++) {
			v0[i] = i;
		}
	
		for (int i = 0; i < s1.length(); i++) {
			// calculate v1 (current row distances) from the previous row v0
			// first element of v1 is A[i+1][0]
			// edit distance is delete (i+1) chars from s to match empty t
			v1[0] = i + 1;

			// use formula to fill in the rest of the row
			for (int j = 0; j < s2.length(); j++) {
				int cost = 1;
				if (s1.charAt(i) == s2.charAt(j)) {
					cost = 0;
				}
				v1[j + 1] = Math.min(v1[j] + 1, // Cost of insertion
						Math.min(v0[j + 1] + 1, // Cost of remove
								v0[j] + cost)); // Cost of substitution
			}
			

			// copy v1 (current row) to v0 (previous row) for next iteration
			// System.arraycopy(v1, 0, v0, 0, v0.length);

			// Flip references to current and previous row
			vtemp = v0;
			v0 = v1;
			v1 = vtemp;

		}

		return v0[s2.length()];
	}
	
	
}