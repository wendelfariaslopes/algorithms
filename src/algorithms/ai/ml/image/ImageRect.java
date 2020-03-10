package algorithms.ai.ml.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Rect;

public class ImageRect {

	public static final String DIR = "../algorithms/src/algorithms/ai/ml/image";

	public static final String IMAGE = "../algorithms/src/algorithms/ai/ml/image/enrico-threshold.jpg";

	public static void main(String[] args) {

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(IMAGE));
		} catch (IOException e) {
			System.out.println(e);
		}

		int w = image.getWidth();
		int h = image.getHeight();

		// Rectangle Red
		int w_red = w / 2;
		int h_red = h / 2;
		int x_red = (w - w_red) / 2; // centered
		int y_red = (h - h_red) / 2; // centered

		// Rectangle Green
		int w_green = w / 2;
		int h_green = h / 2;
		int x_green = x_red + 20;
		int y_green = y_red + 20;
		
		BufferedImage imageRed = image.getSubimage(x_red, y_red, w_red, h_red);
		BufferedImage imageGreen = image.getSubimage(x_green, y_green, w_green, h_green);
		
		

//		Graphics2D redRect = image.createGraphics();
//		redRect.setColor(Color.RED);
//		redRect.drawRect(x_red, y_red, w / 2, h / 2);
//		redRect.dispose();
//
//		Graphics2D greenRect = image.createGraphics();
//		greenRect.setColor(Color.GREEN);
//		greenRect.drawRect(x_green, y_green, w / 2, h / 2);
//		greenRect.dispose();


		// store
		//File file = new File(DIR + File.separator + "enrico-enquadrado.jpg");
		File fileGreen = new File(DIR + File.separator + "enrico-enquadrado-green.jpg");
		File fileRed = new File(DIR + File.separator + "enrico-enquadrado-red.jpg");
		try {
			//ImageIO.write(image, "jpg", file);
			ImageIO.write(imageGreen, "jpg", fileGreen);
			ImageIO.write(imageRed, "jpg", fileRed);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height) {
		return bufferedImage.getSubimage(x, y, width, height);
	}

}
