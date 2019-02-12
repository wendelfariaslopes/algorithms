package algorithms.common.testdome;

/**
 * A palindrome is a word that reads the same backward or forward.
 * 
 * Write a function that checks if a given word is a palindrome. Character case
 * should be ignored.
 * 
 * For example, isPalindrome("Deleveled") should return true as character case
 * should be ignored, resulting in "deleveled", which is a palindrome since it
 * reads the same backward and forward.
 * 
 * @author wendellopes
 * 
 * Hint 1: A string's character data can be accessed using the charAt function.
 *
 */

public class Palindrome {
	public static boolean isPalindrome(String word) {
		//throw new UnsupportedOperationException("Waiting to be implemented.");
		StringBuilder sb = new StringBuilder(word.toLowerCase());
		if(sb.reverse().toString().compareToIgnoreCase(word.toLowerCase())==0) // correct method	
		//Hint 1: A string's character data can be accessed using the charAt function.
		//Hint 2: The String.toLowerCase function can be used to return the string in lowercase.
		//if(sb.reverse().toString().equals(sb.toString().toLowerCase()))
			return true;
		return false;
	}

	public static void main(String[] args) {
		System.out.println(Palindrome.isPalindrome("Deleveled"));
	}
}
