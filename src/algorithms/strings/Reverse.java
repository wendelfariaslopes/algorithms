package algorithms.strings;

public class Reverse {

	public static void main(String[] args) {
		String word = "hanna";
		
		System.out.println(reverse(word)+ " is  palindrome: "+ isPalindrome(word));

	}
	
	public static String reverse(String word) {
		StringBuilder rw = new StringBuilder(word);
		rw.reverse();
		return rw.toString();
	}
	
	public static boolean isPalindrome(String word) {
		StringBuilder rw = new StringBuilder(word);
		rw.reverse();
		if(rw.toString().equals(word)) {
			return true;
		}else {
			return false;
		}
			
	}

}
