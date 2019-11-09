package algorithms.io.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class Grep {
	
	public static void main(String args[]) throws Exception {

		FileReader fr = new FileReader(pathChooser());
		BufferedReader br = new BufferedReader(fr);
		
		String wordToFind = JOptionPane.showInputDialog("Search words:");

		String s = null;
		int linecount = 0;
	
		while ((s = br.readLine()) != null) {

			linecount++;
			int indexfound = s.indexOf(wordToFind);

			if (indexfound > -1) {
				System.out.println("Line size: "+ s.length());
				System.out.println("Word '" + wordToFind + "' was found at position: " + indexfound + " on line " + linecount);
			}
			
			if(s.contains(wordToFind)) {
				System.out.println(s);
				System.out.println("Line size: "+ s.length());
				System.out.println("Word '" + wordToFind + "' was found at position: " + indexfound + " on line " + linecount);
			}
		}
		fr.close();
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