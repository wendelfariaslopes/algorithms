package programs.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author wlopes
 */

public class TailLog implements Runnable {

	private boolean debug = false;

	private int tailRunEveryNSeconds = 2000;
	private long lastKnownPosition = 0;
	private boolean shouldIRun = true;
	private File tailFile = null;
	private static int tailCounter = 0;

	public TailLog(String myFile, int myInterval) {
		tailFile = new File(myFile);
		this.tailRunEveryNSeconds = myInterval;
	}

	private void printLine(String message) {
		System.out.println(message);
	}

	public void stopRunning() {
		shouldIRun = false;
	}

	public void run() {
		try {
			while (shouldIRun) {
				Thread.sleep(tailRunEveryNSeconds);
				long fileLength = tailFile.length();
				if (fileLength > lastKnownPosition) {

// Reading and writing file
					RandomAccessFile readWriteFileAccess = new RandomAccessFile(tailFile, "rw");
					readWriteFileAccess.seek(lastKnownPosition);
					String tailLine = null;
					while ((tailLine = readWriteFileAccess.readLine()) != null) {
						this.printLine(tailLine);
						tailCounter++;
					}
					lastKnownPosition = readWriteFileAccess.getFilePointer();
					readWriteFileAccess.close();
				} else {
					if (debug)
						this.printLine("Hmm.. Couldn't found new line after line # " + tailCounter);
				}
			}
		} catch (Exception e) {
			stopRunning();
		}
		if (debug)
			this.printLine("Exit the program...");
	}

	/**
	 * Use appendData method to add new line to file, so above tailer method can
	 * print the same in Eclipse Console
	 * 
	 * @param filePath
	 * @param shouldIRun
	 * @param tailRunEveryNSeconds
	 */
	private static void appendData(String filePath, boolean shouldIRun, int tailRunEveryNSeconds) {
		FileWriter fileWritter;

		try {
			while (shouldIRun) {
				Thread.sleep(tailRunEveryNSeconds);
				fileWritter = new FileWriter(filePath, true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

				String data = "\nTail.log file content: " + LocalTime.now();
				bufferWritter.write(data);
				bufferWritter.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String argv[]) {

		ExecutorService tailExecutor = Executors.newFixedThreadPool(4);

// Replace username with your real value
// For windows provide different path like: c:\\temp\\tail.log
		//String filePath = "/Users/wlopes/Downloads/tail.log";
		String filePath = "/Users/wendellopes/Downloads/tail.log";
		TailLog tail_tailF = new TailLog(filePath, 2000);

// Start running log file tailer on tail.log file
		tailExecutor.execute(tail_tailF);

// Start pumping data to file tail.log file
		appendData(filePath, true, 5000);

	}

}