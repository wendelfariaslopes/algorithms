package algorithms.io.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class StringFinder {

	public static void main(String[] args) {
		double count = 0, countBuffer = 0, countLine = 0;
		String lineNumber = "";
		String filePath = pathChooser();
		BufferedReader br;
		String inputSearch = "jumps";
		String line = "";

		try {
			br = new BufferedReader(new FileReader(filePath));
			try {
				while ((line = br.readLine()) != null) {
					countLine++;
					// System.out.println(line);
					String[] words = line.split(" ");

					for (String word : words) {
						if (word.equals(inputSearch)) {
							count++;
							countBuffer++;
						}
					}

					if (countBuffer > 0) {
						countBuffer = 0;
						lineNumber += countLine + ",";
					}

				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Times found at--" + count);
		System.out.println("Word found at--" + lineNumber);
	}

	public static String pathChooser() {

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		String fileName = System.getProperty("user.dir") + File.separator;

		jfc.setDialogTitle("Choose one file: ");
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.setAcceptAllFileFilterUsed(false);
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			fileName = selectedFile.getAbsolutePath();
		}
		return fileName;
	}

}
