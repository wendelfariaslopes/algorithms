package algorithms.ai.ml;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Image {

	private static final String DIR = "/Users/wendellopes/git/algorithms/src/image";
	
	private static final String DIR_TO_SAVE = "/Users/wendellopes/Downloads/DeepLearningImages";
	
	public static final int SIZE_PATTERN = 200;

	public static void main(String[] args) throws IOException {
		String pathImage = pathChooser(DIR);

	
		String name = JOptionPane.showInputDialog("Choose Name Image");
	
		
		if(saveImage(carregarImagem(pathImage), name)) {
			System.out.println("Image saved "+ name);
		}else {
			System.err.print("Problems when tried to save!");
		}

	}

	private static BufferedImage carregarImagem(String pathImage) {

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
	
	public static boolean saveImage(BufferedImage imagem, String name) {

		boolean status = false;
		
		int w = imagem.getWidth();
		int h = imagem.getHeight();

		double scale = Image.scale(w, h, SIZE_PATTERN);

		int new_w = (int) (w * scale), new_h = (int) (h * scale);

		BufferedImage new_img = new BufferedImage(new_w, new_h, imagem.getType());
		Graphics2D g = new_img.createGraphics();
		g.drawImage(imagem, 0, 0, new_w, new_h, null);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.dispose();

		File file = new File(DIR_TO_SAVE + File.separator + name + ".jpg");
		try {
			ImageIO.write(new_img, "jpg", file);
			status = true;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return status;
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
