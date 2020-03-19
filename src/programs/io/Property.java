package programs.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {
	
	private static final String PROPERTIES_PATH="..\\algorithms\\src\\programs\\io\\services.properties";

	public static String read(String key) {

		String value = "";

		try (InputStream input = new FileInputStream(PROPERTIES_PATH)) {
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			return prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return value;
	}

}
