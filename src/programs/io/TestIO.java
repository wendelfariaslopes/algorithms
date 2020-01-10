package programs.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class TestIO {
	
	private static final String CURRENT_PATH = System.getProperty("user.dir") + File.separator;
	private static final String DIRECTORY = "Files";
	
	private static final String PATH_FILE = CURRENT_PATH + DIRECTORY + File.separator;
	
	public static void main(String[] args) {
		
		if(createDir(DIRECTORY)) {
			System.out.println("Criado Directory");
		}else {
			System.out.println("Nao Criado Directory");
		}
	}
	
	public static String read(String nameFile, String key) {

		String value = "";

		try (InputStream input = new FileInputStream(PATH_FILE + nameFile)) {
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
	
	public static boolean propertiesWriter(String nameFile) {

		try (OutputStream output = new FileOutputStream(PATH_FILE + nameFile + ".properties")) {

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
	
	public static boolean createDir(String nameDir) {

		boolean status = false;
		Path path = Paths.get(CURRENT_PATH + nameDir);
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                status = true;
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
        return status;
	}

}
