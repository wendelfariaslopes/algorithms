package algorithms.ai.ml.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

public class Image {

	public static final String DIR = ".." + File.separator + "algorithms" + File.separator + "src" + File.separator
			+ "algorithms" + File.separator + "ai" + File.separator + "ml" + File.separator + "image" + File.separator
			+ "example" + File.separator;

	/**
	 * Load image in a buffered image
	 * 
	 * @param pathFromImage
	 * @return
	 */
	public static BufferedImage load(String pathFrom) {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File(pathFrom));
		} catch (MalformedURLException e) {
			throw new AssertionError(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}

	/**
	 * Store image from a buffered image in
	 * 
	 * @param image
	 * @param name
	 * @return
	 */
	public static boolean store(BufferedImage image, String pathTo) {
		boolean status = false;
		try {
			ImageIO.write(image, "jpg", new File(pathTo));
			status = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static boolean store(BufferedImage image, String pathTo, int size) {

		int w = image.getWidth();
		int h = image.getHeight();
		double scale = Image.scale(w, h, size);
		int new_w = (int) (w * scale), new_h = (int) (h * scale);

		BufferedImage new_img = new BufferedImage(new_w, new_h, image.getType());
		Graphics2D g = new_img.createGraphics();
		g.drawImage(image, 0, 0, new_w, new_h, null);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.dispose();

		return store(new_img, pathTo);
	}

	public static BufferedImage crop(BufferedImage image, Rectangle rect) {
		return image.getSubimage(rect.x, rect.y, rect.width, rect.height);
	}

	public static int[] vectorization(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		int[] vector = new int[w * h];
		int n = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				vector[n] = image.getRGB(j, i) == 0xFFFFFFFF ? 0 : 1;
				++n;
			}
		}
		return vector;
	}

	public static BufferedImage vectorToImage(int[] vector, int width, int height) {

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int v = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				int p = (vector[v] << 24) | (vector[v + (width * height)] << 16)
						| (vector[v + (2 * width * height)] << 8) | vector[v + (3 * width * height)];

				image.setRGB(j, i, p);

				++v;
			}
		}

		return image;
	}

	public static double[] imageToVector(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		int layerSize = w * h;

		double[] vector = new double[3 * layerSize];
		int n = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				// get pixel value
				int p = image.getRGB(j, i);
				// get red
				int red = (p >> 16) & 0xff;
				// get green
				int green = (p >> 8) & 0xff;
				// get blue
				int blue = p & 0xff;
				// populate vector
				vector[n] = red;
				vector[n + layerSize] = green;
				vector[n + 2 * layerSize] = blue;
				++n;
			}
		}

		return vector;
	}

	public static BufferedImage threshold(BufferedImage image, int threshould) {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				double lum = Luminance.intensity(color);
				if (lum >= threshould) {
					image.setRGB(i, j, Color.WHITE.getRGB());
				} else {
					image.setRGB(i, j, Color.BLACK.getRGB());
				}
			}
		}
		return image;
}


	private static double scale(int w, int h, int size) {
		double s = 1.0;
		double fator = 1.0;
		if (w >= h) {
			fator = w / size;
			s = 1 / fator;
		} else {
			fator = h / size;
			s = 1 / fator;
		}
		return s;
	}

}
