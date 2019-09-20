package programs.io;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.junit.Test;

public class IO {

	private static final String P = "/Users/wendellopes/eclipse-workspace/algorithms/src/programs/io/test/command-lines.properties";


	//User's current working directory
	private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");

	public static boolean propertiesWriter(String path) {

		if (!path.endsWith(".properties")) {
			path += ".properties";
		}
		
		File temp;
		try {
			temp = File.createTempFile("myTempFile", ".txt");

			boolean exists = temp.exists();

			System.out.println("Temp file exists : " + exists);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (OutputStream output = new FileOutputStream(path)) {

			Properties prop = new Properties();

			// set the properties value
			prop.setProperty("db.url", "localhost");
			prop.setProperty("db.user", "mkyong");
			prop.setProperty("db.password", "password");

			// save properties to project root folder
			prop.store(output, null);

			System.out.println(prop);

			return true;

		} catch (IOException io) {
			io.printStackTrace();
			return false;
		}

	}

	@Test
	public void writer_tester() {
		System.out.println();

		assertTrue(propertiesWriter(P));
	}

}
