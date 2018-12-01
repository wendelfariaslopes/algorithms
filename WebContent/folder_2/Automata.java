package algorithms.automata;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public class Automata extends Thread {

	private static String SOURCE_CODE = "/Users/wendellopes/eclipse-workspace/algorithms/src/algorithms/automata/Automata.java";
	private static final String ROOT = "/Users/wendellopes/eclipse-workspace/algorithms/WebContent/";
	private static final String[] FOLDERS = { "folder_1/", "folder_2/" };

	public static void main(String[] args) {
		
		Automata a = new Automata();
		
			//SOURCE_CODE = Automata.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"TaxRate.class";
	
			//SOURCE_CODE = Automata.class.getClassLoader().getResource("").getPath();
			System.out.println(SOURCE_CODE);
			
			//listFilesInDirectory(SOURCE_CODE);
			
			//System.out.println(a.getClass().getProtectionDomain().getCodeSource().getLocation().getContent());
		
		a.start();

	}

	@Override
	public void run() {

		while (true) {

			System.out.println(getName() + " is running");
			try {
				process();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}

	private static void process() {

		File source = new File(SOURCE_CODE);
		File dest = null;

		for (int i = 0; i < FOLDERS.length; i++) {

			dest = new File(ROOT+FOLDERS[i] + "Automata.java");

			if (!dest.exists()) {

				try {
					Files.copy(source.toPath(), dest.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
	}
	
	public static void listFilesInDirectory(String dir) {
		
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
	}

}
