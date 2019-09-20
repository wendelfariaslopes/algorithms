package algorithms.io;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;


public class LoggerFile {

	private static Logger jul = Logger.getLogger(LoggerFile.class.getName()); //jul java util logging
		
	public static final int KILOBYTE_SIZE = 1024;
	//A specific size for each file and append set to true
	public static final int MAX_FILE_SIZE = 10*KILOBYTE_SIZE;
	//
	public static final int NUMBER_LOG_FILES = 10;
	

	public static void main(String[] args) {

		try {

			// Create an instance of FileHandler with 5 logging files sequences.
			//FileHandler handler = new FileHandler("/Users/wendellopes/Downloads/Logger/sample.log", MAX_FILE_SIZE, NUMBER_LOG_FILES, true);
			FileHandler handlerHtml = new FileHandler("/Users/wendellopes/Downloads/Logger/sample.html");
			
			//Formatter HTML
			Formatter HtmlFormatter =  new LoggerHtmlFormatter();

			handlerHtml.setFormatter(HtmlFormatter);
			//handler.setFormatter(new SimpleFormatter());

			jul.addHandler(handlerHtml);
			//logger.addHandler(handler);
	

			jul.setUseParentHandlers(false);

		} catch (IOException e) {

			jul.warning("Failed to initialize logger handler.");
			

		}
		
		for(int i=0; i< 100; i++ ) {
			//slf4j.warn("WARNING");
			jul.info("Logging info message = "+i);
			jul.warning("Logging warn message = "+i);
			jul.severe("Logging Severe message = "+i);
		}

	}
}