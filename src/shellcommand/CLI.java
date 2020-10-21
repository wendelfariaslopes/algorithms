package shellcommand;

import java.io.IOException;
import java.io.InputStream;

/**
 * A command-line interface (CLI) is a means of interacting with a computer
 * program where the user (or client) issues commands to the program in the form
 * of successive lines of text (command lines).
 * 
 * @author wlopes
 *
 */
public class CLI implements Runnable {

	public static final String LIST_DIRECTORY_DOS = "";
	public static final String LIST_DIRECTORY_SHELL = "";
	static Process process = null;

	private static InputStream StreamGobbler() {
		
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		String homeDirectory = System.getProperty("user.home");
		
		
		if (isWindows) {
		    try {
				process = Runtime.getRuntime()
				  .exec(String.format("cmd.exe /c dir %s", homeDirectory));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		    try {
				process = Runtime.getRuntime()
				  .exec(String.format("sh -c ls %s", homeDirectory));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		return process.getInputStream();
		
	}

	@Override
	public void run() {
		
		
		int exitCode;
		try {
			exitCode = process.waitFor();
			assert exitCode == 0;
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}

}
