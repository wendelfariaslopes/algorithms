package lexical;

public class Palindrome {

	public static void main(String[] args) {
		String text = "Hannah";
		
		if(isPalindromeUsingStringBuffer(text)) {
			System.out.println("Eh");
		}else {
			System.out.println("Not");
		}

	}
	

	
	public static boolean isPalindromeUsingStringBuilder(String text) {
	    String clean = text.replaceAll("\\s+", "").toLowerCase();
	    StringBuilder plain = new StringBuilder(clean);
	    StringBuilder reverse = plain.reverse();
	    return (reverse.toString()).equals(clean);
	}
	 
	public static boolean isPalindromeUsingStringBuffer(String text) {
	    String clean = text.replaceAll("\\s+", "").toLowerCase();
	    StringBuffer plain = new StringBuffer(clean);
	    StringBuffer reverse = plain.reverse();
	    return (reverse.toString()).equals(clean);
	}
	
	
	public static boolean isPalindrome(String text) {
	    String clean = text.replaceAll("\\s+", "").toLowerCase();
	    int length = clean.length();
	    int forward = 0;
	    int backward = length - 1;
	    while (backward > forward) {
	        char forwardChar = clean.charAt(forward++);
	        char backwardChar = clean.charAt(backward--);
	        if (forwardChar != backwardChar)
	            return false;
	    }
	    return true;
	}
	
	public static boolean isPalindromeReverseTheString(String text) {
	    StringBuilder reverse = new StringBuilder();
	    String clean = text.replaceAll("\\s+", "").toLowerCase();
	    char[] plain = clean.toCharArray();
	    for (int i = plain.length - 1; i >= 0; i--) {
	        reverse.append(plain[i]);
	    }
	    return (reverse.toString()).equals(clean);
	}

}
