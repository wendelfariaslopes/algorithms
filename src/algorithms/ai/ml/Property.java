package algorithms.ai.ml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class Property {
	
	private static final String PATH="/Users/wendellopes/git/algorithms/src/algorithms/ai/ml/data.properties";

	public static String read(String key) {

		String value = "";

		try (InputStream input = new FileInputStream(PATH)) {
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
	
	public static Map<String,String> dataSerie(){
		Map<String, String> map = null;
		try (InputStream input = new FileInputStream(PATH)) {
			Properties prop = new Properties();
			prop.load(input);
			map = propertiesToMap(prop);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return map;
	}
	
	private static Map<String, String> propertiesToMap(Properties props) {
		Map<String, String> map = new TreeMap<String, String>();
		Enumeration<Object> e = props.keys();
		while (e.hasMoreElements()) {
			String s = (String) e.nextElement();
			map.put(s, props.getProperty(s));
		}
		return map;
	}

}
