package algorithms.ai.ml.image;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChannelSplitter {

	private static final String DIR = "/Users/wendellopes/Downloads/";

	public static void main(String[] args) {

		String original = "enrico.jpeg";
		String newImage = "enrico-teste.jpeg";

		BufferedImage img = readImage(DIR + original);

		int[] v = imageToVector(img);

		System.out.println("Size Vector = " + v.length);

		if (vectorToImage(v,img.getWidth(),img.getHeight(),DIR + newImage)) {
			System.out.println("Okay");
		}

	}

	private static BufferedImage readImage(String path) {
		BufferedImage img = null;
		File f = null;
		// read image
		try {
			f = new File(path);
			img = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println(e);
		}
		return img;
	}

	private static boolean writeBlueImage(BufferedImage img, String path) {

		boolean status = false;
		int width = img.getWidth();
		int height = img.getHeight();

		int[] blueLayer = getBlueLayer(img);
		int[] alphaLayer = getAlphaLayer(img);
		int[] redLayer = getRedLayer(img);
		int[] greenLayer = getGreenLayer(img);

		File f = null;
		int v = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				int p = (alphaLayer[v] << 24) | (redLayer[v] << 16) | (greenLayer[v] << 8) | blueLayer[v];
				// int p = img.getRGB(i,j);
				// get alpha
				// int a = (p>>24) & 0xff;

				// get red
				int r = 0;

				// get green
				int g = 0;

				// get blue
				int b = p & 0xff;
//
//			    //set the pixel value
				// int pixel = (a<<24) | (r<<16) | (g<<8) | b;
				img.setRGB(j, i, p);

				++v;
			}
		}

		// write image
		try {
			f = new File(path);
			ImageIO.write(img, "jpg", f);
			status = true;
		} catch (IOException e) {
			System.out.println(e);
		}
		return status;
	}
	
	
	private static boolean vectorToImage(int[] vector,int width, int height , String path) {

		boolean status = false;

		File f = null;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		int v = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				int p = (vector[v] << 24) | (vector[v+ (width*height)] << 16) | (vector[v+ (2*width*height)] << 8) | vector[v+ (3*width*height)];
				
				img.setRGB(j, i, p);

				++v;
			}
		}

		try {
			f = new File(path);
			ImageIO.write(img, "jpg", f);
			status = true;
		} catch (IOException e) {
			System.out.println(e);
		}
		return status;
	}

	private static boolean store(BufferedImage img, String path) {
		boolean status = false;
		File f = null;
		try {
			f = new File(path);
			ImageIO.write(img, "jpg", f);
			status = true;
		} catch (IOException e) {
			System.out.println(e);
		}
		return status;
	}

	private static int[] getAlphaLayer(BufferedImage img) {

		// get image width and height
		int width = img.getWidth();
		int height = img.getHeight();

		int[] alphaLayer = new int[width * height];
		int v = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// get pixel value
				int p = img.getRGB(j, i);
				// get alpha
				int alpha = (p >> 24) & 0xff;
				alphaLayer[v++] = alpha;
			}
		}
		return alphaLayer;
	}

	public static int[] imageToVector(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		int layerSize = w * h;

		int[] vector = new int[4 * layerSize];
		int n = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				// get pixel value
				int p = img.getRGB(j, i);
				// get alpha
				//int alpha = (p >> 24) & 0xff;
				// get red
				int red = (p >> 16) & 0xff;
				// get green
				int green = (p >> 8) & 0xff;
				// get blue
				int blue = p & 0xff;
				//populate vector
				vector[n] = red;
				vector[n+layerSize] = green;
				vector[n+2*layerSize] = blue;
				++n;
			}
		}

		return vector;
	}

//	public static BufferedImage vectorToImage(int[] vector, int width, int height) {
//		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		bufferedImage.setRGB(0, 0, BufferedImage.TYPE_INT_RGB);
//		return bufferedImage;
//	}

	static void processImage() {

		String file1 = "c://newslider1.jpg";
		String file2 = "c://newslider1.jpg";

		// Load the images
		Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
		Image image2 = Toolkit.getDefaultToolkit().getImage(file2);

		// PixelGrabber pg = new PixelGrabber(new BufferedImage(width, height,
		// imageType), 0, 0, -1, -1, true);

		try {

			PixelGrabber grabImage1Pixels = new PixelGrabber(image1, 0, 0, -1, -1, false);
			PixelGrabber grabImage2Pixels = new PixelGrabber(image2, 0, 0, -1, -1, false);

			int[] image1Data = null;

			if (grabImage1Pixels.grabPixels()) {
				int width = grabImage1Pixels.getWidth();
				int height = grabImage1Pixels.getHeight();
				image1Data = new int[width * height];
				image1Data = (int[]) grabImage1Pixels.getPixels();
			}

			int[] image2Data = null;

			if (grabImage2Pixels.grabPixels()) {
				int width = grabImage2Pixels.getWidth();
				int height = grabImage2Pixels.getHeight();
				image2Data = new int[width * height];
				image2Data = (int[]) grabImage2Pixels.getPixels();
			}

			System.out.println("Pixels equal: " + java.util.Arrays.equals(image1Data, image2Data));

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	private static int[] getRedLayer(BufferedImage img) {

		// get image width and height
		int width = img.getWidth();
		int height = img.getHeight();

		int[] redLayer = new int[width * height];
		int v = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// get pixel value
				int p = img.getRGB(j, i);
				// get red
				int red = (p >> 16) & 0xff;
				redLayer[v++] = red;
			}
		}
		return redLayer;
	}

	private static int[] getGreenLayer(BufferedImage img) {

		// get image width and height
		int width = img.getWidth();
		int height = img.getHeight();

		int[] greenLayer = new int[width * height];
		int v = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// get pixel value
				int p = img.getRGB(j, i);
				// get green
				int green = (p >> 8) & 0xff;
				greenLayer[v++] = green;
			}
		}
		return greenLayer;
	}

	private static int[] getBlueLayer(BufferedImage img) {

		// get image width and height
		int width = img.getWidth();
		int height = img.getHeight();

		int[] blueLayer = new int[width * height];
		int v = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// get pixel value
				int p = img.getRGB(j, i);
				// get blue
				int blue = p & 0xff;
				blueLayer[v++] = blue;

			}
		}
		return blueLayer;
	}

	private int[] rowFlipPixels(int pixels[], int width, int height) {
		int newPixels[] = null;
		if ((width * height) == pixels.length) {
			newPixels = new int[width * height];
			int newIndex = 0;
			for (int y = height - 1; y >= 0; y--)
				for (int x = width - 1; x >= 0; x--)
					newPixels[newIndex++] = pixels[y * width + x];
		}
		return newPixels;
	}

	private int[] colFlipPixels(int pixels[], int width, int height) {
		int newPixels[] = null;
		if ((width * height) == pixels.length) {
			newPixels = new int[width * height];
			int newIndex = 0;
			for (int y = 0; y < height; y++)
				for (int x = width - 1; x >= 0; x--)
					newPixels[newIndex++] = pixels[y * width + x];
		}
		return newPixels;
	}

	private int[] rot90Pixels(int pixels[], int width, int height) {
		int newPixels[] = null;
		if ((width * height) == pixels.length) {
			newPixels = new int[width * height];
			int newIndex = 0;
			for (int x = width - 1; x >= 0; x--)
				for (int y = 0; y < height; y++)
					newPixels[newIndex++] = pixels[y * width + x];
		}
		return newPixels;
	}

}
