package algorithms.ai.ml.image;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Image {

	private static final String DIR = "/Users/wendellopes/git/algorithms/src/image";

	private static final String DIR_TO_SAVE = "/Users/wendellopes/Downloads/DeepLearningImages";
	
	private static final String DIR_TRAINING = "/Users/wendellopes/Downloads/cats/training";

	public static final int SIZE_100_PIXEL = 50;
	
	//Vectorization


	public static void main(String[] args) throws IOException {
		
		//ETL processing images
//		final String DIR_FROM_LOAD = "/Users/wendellopes/Downloads/cats/original";
//		
//		List<String> listOriginalImage = FileManager.listFiles(DIR_FROM_LOAD);
//		
//		
//		for(String nameFile: listOriginalImage) {
//			BufferedImage image = load(DIR_FROM_LOAD + File.separator + nameFile);
//			
//			if(image!=null) {
//			
//				if(store(image, nameFile, SIZE_100_PIXEL)) {
//					System.out.println("ETL for: "+nameFile+ " Complete!");
//				}
//			}
//		}
		
		//Vectorization
		
		
		
		
//		String pathImage = pathChooser(DIR);
//
//		String name = JOptionPane.showInputDialog("Choose Name Image");
//
//		if (saveImage(load(pathImage), name)) {
//			System.out.println("Image saved " + name);
//		} else {
//			System.err.print("Problems when tried to save!");
//		}

	}

	private static BufferedImage load(String pathImage) {
		File file = new File(pathImage);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (MalformedURLException e) {
			throw new AssertionError(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}

	public static boolean store(BufferedImage image, String name) {
		boolean status = false;
		File file = new File(DIR_TRAINING + File.separator + name);
		try {
			ImageIO.write(image, "jpg", file);
			status = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static boolean store(BufferedImage imagem, String name, int size) {

		boolean status = false;

		int w = imagem.getWidth();
		int h = imagem.getHeight();

		double scale = Image.scale(w, h, size);

		int new_w = (int) (w * scale), new_h = (int) (h * scale);

		BufferedImage new_img = new BufferedImage(new_w, new_h, imagem.getType());
		Graphics2D g = new_img.createGraphics();
		g.drawImage(imagem, 0, 0, new_w, new_h, null);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.dispose();

		File file = new File(DIR_TRAINING + File.separator + name);
		try {
			ImageIO.write(new_img, "jpg", file);
			status = true;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return status;
	}

	private static String pathChooser(String directory) {

		File f = new File(directory);

		JFileChooser jfc = new JFileChooser(f);

		String fileName = "";

		jfc.setDialogTitle("Choose one file: ");
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.setAcceptAllFileFilterUsed(false);
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			fileName = selectedFile.getAbsolutePath();
		}

		return fileName;
	}


	public static BufferedImage crop(BufferedImage src, Rectangle rect) {
		BufferedImage dest = src.getSubimage(0, 0, rect.width, rect.height);
		return dest;
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
