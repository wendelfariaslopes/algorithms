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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class IO {


	private static final String FILE_TYPE = ".properties";
	// User's current working directory
	private static final String DIR = System.getProperty("user.dir") + File.separator + "Files" + File.separator;
	
	public static void main (String[] args) {
		
	
		if(createDir(DIR)) {
			System.out.println("Create directory: " + DIR);
		} else {
			System.out.println("Didn't create directory: "+ DIR);
		}
		
		Map<String,String> map = new HashMap<>();
		
		for(int i=1; i < 11; i++) {
			map.put(IO.class.getSimpleName()+"."+i, i+"");
		}
		
		
		if(propWrite(IO.class.getSimpleName(), map)) {
			System.out.println("Yes" );
		}else {
			System.out.println("NO" );
		}
		
		System.out.println(propRead(IO.class.getSimpleName(), IO.class.getSimpleName()+".4"));
	
		
	}

	public static boolean propWrite(String fileName, Map<String, String> map) {

		try (OutputStream output = new FileOutputStream(DIR + fileName + FILE_TYPE)) {
			Properties prop = new Properties();
			// set the properties value
			map.forEach((k, v) -> {
				prop.setProperty(k, v);
			});
			// save properties to project root folder
			prop.store(output, null);
			return true;
		} catch (IOException io) {
			io.printStackTrace();
			return false;
		}
	}

	public static String propRead(String fileName, String key) {

		String value = "";

		try (InputStream input = new FileInputStream(DIR + fileName + FILE_TYPE)) {
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

	public static boolean createDir(String nameDir) {
		boolean status = false;
		Path path = Paths.get(DIR);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
				status = true;
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		return status;
	}

}
