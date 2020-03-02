<<<<<<< Upstream, based on origin/master
package algorithms.image;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * 
 * 
 * @author Wendel F. Lopes
 *
 */

public class ImagemUtil {
//
//    public static byte[] geraImagemThumb(byte[] imagemOriginal)
//			throws InterruptedException, IOException {
//
//		Image imagem = Toolkit.getDefaultToolkit().createImage(imagemOriginal);
//		MediaTracker mediaTracker = new MediaTracker(new Container());
//		mediaTracker.addImage(imagem, 0);
//		mediaTracker.waitForID(0);
//
//		// define a qualidade da imagem
//		int qualidade = 100; // 100%
//
//		// define a largura e altura do thumbnail
//		int largura = 300;
//		int altura = 200;
//		double thumbRatio = (double) largura / (double) altura;
//		int larguraImagem = imagem.getWidth(null);
//		int alturaImagem = imagem.getHeight(null);
//		double imageRatio = (double) larguraImagem / (double) alturaImagem;
//
//		if (thumbRatio < imageRatio) {
//			altura = (int) (largura / imageRatio);
//		} else {
//			largura = (int) (altura * imageRatio);
//		}
//		// Desenha a imagem original para o thumbnail e
//		// redimensiona para o novo tamanho
//
//		BufferedImage thumbImage = new BufferedImage(largura, altura,
//				BufferedImage.TYPE_INT_RGB);
//		Graphics2D graphics2D = thumbImage.createGraphics();
//		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//		graphics2D.drawImage(imagem, 0, 0, largura, altura, null);
//
//		// Salva a nova imagem
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
//		param.setQuality((float) qualidade / 100.0f, false);
//		encoder.setJPEGEncodeParam(param);
//		encoder.encode(thumbImage);
//		byte[] imagemThumb = out.toByteArray();
//		out.flush();
//		out.close();
//
//
//		return imagemThumb;
//	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
    
//    public static byte[] geraImagemPadrao(byte[] imagemOriginal)
//			throws InterruptedException, IOException {
//
//		Image imagem = Toolkit.getDefaultToolkit().createImage(imagemOriginal);
//		MediaTracker mediaTracker = new MediaTracker(new Container());
//		mediaTracker.addImage(imagem, 0);
//		mediaTracker.waitForID(0);
//
//		// define a qualidade da imagem
//		int qualidade = 100; // 100%
//
//		// define a largura e altura do thumbnail
//		int largura = 750;
//		int altura = 600;
//		double padraoRatio = (double) largura / (double) altura;
//		int larguraImagem = imagem.getWidth(null);
//		int alturaImagem = imagem.getHeight(null);
//		double imageRatio = (double) larguraImagem / (double) alturaImagem;
//
//		if (padraoRatio < imageRatio) {
//			altura = (int) (largura / imageRatio);
//		} else {
//			largura = (int) (altura * imageRatio);
//		}
//		// Desenha a imagem original para o thumbnail e
//		// redimensiona para o novo tamanho
//
//		BufferedImage padraoImage = new BufferedImage(largura, altura,
//				BufferedImage.TYPE_INT_RGB);
//		Graphics2D graphics2D = padraoImage.createGraphics();
//		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//		graphics2D.drawImage(imagem, 0, 0, largura, altura, null);
//
//		// Salva a nova imagem
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(padraoImage);
//		param.setQuality((float) qualidade / 100.0f, false);
//		encoder.setJPEGEncodeParam(param);
//		encoder.encode(padraoImage);
//		byte[] imagemPadrao = out.toByteArray();
//		out.flush();
//		out.close();
//
//		return imagemPadrao;
//	}
=======
package image;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * 
 * 
 * @author Wendel F. Lopes
 *
 */

public class ImagemUtil {
  
    public static byte[] geraImagemThumb(byte[] imagemOriginal)
			throws InterruptedException, IOException {
		
		Image imagem = Toolkit.getDefaultToolkit().createImage(imagemOriginal);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(imagem, 0);
		mediaTracker.waitForID(0);

		// define a qualidade da imagem
		int qualidade = 100; // 100%

		// define a largura e altura do thumbnail
		int largura = 300;
		int altura = 200;
		double thumbRatio = (double) largura / (double) altura;
		int larguraImagem = imagem.getWidth(null);
		int alturaImagem = imagem.getHeight(null);
		double imageRatio = (double) larguraImagem / (double) alturaImagem;
		
		if (thumbRatio < imageRatio) {
			altura = (int) (largura / imageRatio);
		} else {
			largura = (int) (altura * imageRatio);
		}
		// Desenha a imagem original para o thumbnail e
		// redimensiona para o novo tamanho

		BufferedImage thumbImage = new BufferedImage(largura, altura,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(imagem, 0, 0, largura, altura, null);

		// Salva a nova imagem
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
		param.setQuality((float) qualidade / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(thumbImage);
		byte[] imagemThumb = out.toByteArray();
		out.flush();
		out.close();
		

		return imagemThumb;
	}
    
    public static byte[] geraImagemPadrao(byte[] imagemOriginal)
			throws InterruptedException, IOException {
		
		Image imagem = Toolkit.getDefaultToolkit().createImage(imagemOriginal);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(imagem, 0);
		mediaTracker.waitForID(0);

		// define a qualidade da imagem
		int qualidade = 100; // 100%
		
		// define a largura e altura do thumbnail
		int largura = 750;
		int altura = 600;
		double padraoRatio = (double) largura / (double) altura;
		int larguraImagem = imagem.getWidth(null);
		int alturaImagem = imagem.getHeight(null);
		double imageRatio = (double) larguraImagem / (double) alturaImagem;
		
		if (padraoRatio < imageRatio) {
			altura = (int) (largura / imageRatio);
		} else {
			largura = (int) (altura * imageRatio);
		}
		// Desenha a imagem original para o thumbnail e
		// redimensiona para o novo tamanho

		BufferedImage padraoImage = new BufferedImage(largura, altura,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = padraoImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(imagem, 0, 0, largura, altura, null);

		// Salva a nova imagem
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(padraoImage);
		param.setQuality((float) qualidade / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(padraoImage);
		byte[] imagemPadrao = out.toByteArray();
		out.flush();
		out.close();

		return imagemPadrao;
	}
>>>>>>> 74ea2df Changes

	
}
