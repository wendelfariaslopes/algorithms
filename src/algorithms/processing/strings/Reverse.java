package algorithms.processing.strings;

public class Reverse {

	public static void main(String[] args) {
		String word = "Deleveled";

		System.out.println(reverse(word) + " is  palindrome: " + isPalindrome(word));
		System.out.println(reverse(word) + " is  palindrome: " + isPal(word));

	}

	public static String reverse(String word) {
		StringBuilder rw = new StringBuilder(word);
		rw.reverse();
		return rw.toString();
	}

	public static boolean isPalindrome(String word) {
		StringBuilder rw = new StringBuilder(word);
		rw.reverse();
		if (rw.toString().equals(word)) {
			return true;
		} else {
			return false;
		}

	}

	// if length is 0 or 1 then String is palindrome
	public static boolean isPal(String s) { 
		if (s.length() == 0 || s.length() == 1)
			return true;
		
		/*
		 * check for first and last char of String: if they are same then do the same
		 * thing for a substring with first and last char removed. and carry on this
		 * until you string completes or condition fails Function calling itself:
		 * Recursion
		 */
		if (s.charAt(0) == s.charAt(s.length() - 1))
			return isPal(s.substring(1, s.length() - 1));
			
		/*
		 * If program control reaches to this statement it means the String is not
		 * palindrome hence return false.
		 */
		return false;
	}
	


}
