/**
 * 
 */
package image;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//import javax.servlet.ServletContext;




/**
 * @author Wendel F. Lopes
 *
 */
public class ImagePattern {
	
	public static final int SIZE_MINI = 100;
	//public static final int SIZE_THUMB = 200;
	public static final int SIZE_THUMB = 150;
	public static final int SIZE_PATTERN = 600;
		

	public static void saveImage(BufferedImage imagem, String name) throws IOException {
		
		
			String DIR = "/Users/wendellopes/Documents/organon/WebContent/resources/upload";
		   
	    	//BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(image));
	        // BufferedImage imagem = ImageIO.read(new File("/Users/wendellopes/Documents/organon/JavaSource/image/util/IMG_0652.JPG"));
	        //imagem.getSubimage(0, y, w, h)  crop
	        
	        int w = imagem.getWidth();
	        int h = imagem.getHeight();
	        
	        double scale = ImagePattern.scale(w, h, SIZE_PATTERN);
	  
	        int new_w = (int) (w*scale), new_h = (int) (h*scale);
	        
	        BufferedImage new_img = new BufferedImage(new_w, new_h, imagem.getType());
	        Graphics2D g = new_img.createGraphics();
	        g.drawImage(imagem, 0, 0, new_w, new_h, null);
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);  
	        g.dispose();
	        
//	        long time = System.currentTimeMillis();
//	        String name = "image_"+time+".jpg";
//	           
//	        FacesContext facesContext = FacesContext.getCurrentInstance();  
//	        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();  
//	        String path = scontext.getRealPath("/resources/upload/"); 
	        
	        File file = new File(DIR+"/"+"simple_"+name);
	    
	        ImageIO.write(new_img, "JPG", file); 
	     
	  }
	   
	private static double scale(int w, int h, int size){
	    	double s = 1.0;
	    	double fator = 1.0;
	    	if(w >= h){
	    		fator = w/size;
	    		s = 1/fator;
	    	}else{
	    		fator = h/size;
	    		s = 1/fator;
	    	}
	    return s;
	   }
	  
	  	
	 
	    
}
