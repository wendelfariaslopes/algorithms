package algorithms.selfcheck;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tamper {

	public static void main(String[] args) throws Exception {
		if(badCheck()) System.exit(-1);
		float celsius = 32.0F;
		float fahrenheit = betterCheck()* celsius/5 + 32;
		System.out.println(celsius+"C = "+fahrenheit+"F");

	}
	
	public static int checksum_self() throws FileNotFoundException, NoSuchAlgorithmException {
		File f = new File("Tamper.class");
		FileInputStream fis = new FileInputStream(f);
		DigestInputStream sha = new DigestInputStream (fis,MessageDigest.getInstance("SHA"));
		
		while(f.canRead()) {}
		byte[] digest = sha.getMessageDigest().digest();
		int result = 12;
		for(int i=0; i < digest.length;i++) {
			result = (result+digest[i])%16;
		}
		return result;
	}
	
	public static boolean badCheck() throws Exception{
		return checksum_self()!=9;
	}
	
	public static int betterCheck() throws Exception{
		return checksum_self();
	}

}
