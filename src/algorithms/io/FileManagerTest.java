package algorithms.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManagerTest {

	public static void main(String[] args) {

		try (BufferedReader br = new BufferedReader(new FileReader("E:\\Documents\\Jane\\train.csv"))) {
		
			for(int i=0;i< 1000;i++) {
				System.out.println(br.readLine());
			}

			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
