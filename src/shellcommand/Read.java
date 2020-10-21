package shellcommand;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Read {



	public static void main(String[] args) {

		try (InputStream input = new FileInputStream(
				"C:\\Users\\wlopes\\eclipse-workspace\\organon-command\\src\\organon\\io\\cmd\\prop.properties")) {

			Properties prop = new Properties();

			// load a properties file
			prop.load(input);
			
			String str = prop.getProperty("tsom.file.location");

			// get the property value and print it out
			System.out.println(str);
			System.out.println(str.replace("{?}", "tsom-a-1"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		

	}



}
