package algorithms.text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FindWords {
	
	private static final String PATH = "C:\\Users\\wlopes\\Downloads\\log\\";
	private static final String FILE_TO_READ = "tsom-31-a.txt";
	private static final String FILE_TO_WRITE = "tsom-31-a-extract.txt";

	public static void main(String[] args) {
	
		String[] items = { "21030413-3742-488b-9138-93a2e89734ee-7", "6640" };
		read(items);
		System.out.println("Finished");

	}

	public static void read(String[] items) {
		try {

			BufferedReader b = new BufferedReader(new FileReader(new File(PATH + FILE_TO_READ)));
			String line = "";
			System.out.println("Reading file using Buffered Reader");

			while ((line = b.readLine()) != null) {
				
				if(containsWords(line, items)) {
					System.out.println(line);
					write(line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void write(String line) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(PATH + FILE_TO_WRITE), true);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		for (int i = 0; i < 10; i++) {
			bw.write(line);
			bw.newLine();
		}
	 
		bw.close();
	}

	public static boolean containsWords(String inputString, String[] items) {
		boolean found = true;
		for (String item : items) {
			if (!inputString.contains(item)) {
				found = false;
				break;
			}
		}
		return found;
	}

}
