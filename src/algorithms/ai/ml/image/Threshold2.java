package algorithms.ai.ml.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Threshold2 {

	public static final String DIR = "../algorithms/src/algorithms/ai/ml/image";

	public static void main(String[] args) {
		int THRESHOLD = 100;
		String filename = "../algorithms/src/algorithms/ai/ml/image/enrico.jpeg";

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.out.println(e);
		}

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				double lum = Luminance.intensity(color);
				if (lum >= THRESHOLD) {
					image.setRGB(i, j, Color.WHITE.getRGB());
				} else {
					image.setRGB(i, j, Color.BLACK.getRGB());
				}
			}
		}

		// store
		File files = new File(DIR + File.separator + "enrico-threshold.jpg");

		try {
			ImageIO.write(image, "jpg", files);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
