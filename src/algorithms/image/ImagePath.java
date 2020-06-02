/**
 * 
 */
<<<<<<< Upstream, based on origin/master
<<<<<<< Upstream, based on origin/master
package algorithms.image;
=======
package image;
>>>>>>> 74ea2df Changes
=======
package algorithms.image;
>>>>>>> e7cfaf3 Changes in place

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Wendel F. Lopes
 *
 */
public class ImagePath {
	
	public static Properties getProp() throws IOException {
		String fileName = "app.properties";
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(fileName);
		
		props.load(file);
		return props;

	}

}
