package shellcommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class ExecuteShellCommand {

	public static void main(String[] args) {
		
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		String homeDirectory = System.getProperty("user.home");
		String currentDir = System.getProperty("user.dir");
		String javaClassPath = System.getProperty("java.class.path");
		String javaHome = System.getProperty("java.home");
		
		String title = new String();
		
		if (isWindows) {
			title = "Execute DOS command";
		}else {
			title = "Execute shell command";
		}
		String cmd = JOptionPane.showInputDialog(title);
	

		try {
			
			System.out.println("Executing command: " + cmd);
			Process p = Runtime.getRuntime().exec(String.format(cmd, currentDir));
			
			int result = p.waitFor();
			System.out.println("Process exit code: " + result);

			printerOutput(p.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void printerOutput(InputStream is) {
		
		
		System.out.println();
		System.out.println("Result:");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = "";
		try {
			
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}