package algorithms.automata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

public class Automata extends Thread {

	private static final String ROOT = "/Users/wendellopes/Documents/test/";
	private static final String[] FOLDERS = { "folder_1/", "folder_2/", "folder_3/" };

	public static void main(String[] args) {
		Automata a = new Automata();
		a.start();
	}

	@Override
	public void run() {

		while (true) {

			try {
				process();
				Thread.sleep(10000);
				setName("ClassLoader:"+this.getContextClassLoader());
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private static void process() {

		File source = null;

		for (int i = 0; i < FOLDERS.length; i++) {

			source = new File(ROOT + FOLDERS[i] + "Automata.jar");
			
			if (source.exists()) {			
				startAutoCopy(source);
			}else {
				selfWriter(ROOT + FOLDERS[i] + "info.txt", "*** Don't existe: "+source.getPath());
			}
		}

	}

	private static void startAutoCopy(File source) {
		File dest = null;

		for (int i = 0; i < FOLDERS.length; i++) {

			dest = new File(ROOT + FOLDERS[i] + "Automata.jar");

			if (!dest.exists()) {

				try {
					Files.copy(source.toPath(), dest.toPath());
					selfWriter(ROOT + FOLDERS[i] + "info.txt", "From: "+source.getPath()+ " to: "+dest.toPath()+ " "+ new Date());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}
	
	private static void selfWriter(String pathFile, String content) {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile, true))) {
			bw.write(content);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
