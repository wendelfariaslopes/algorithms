package cogito4j.preprocessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class File {

    public static java.awt.image.BufferedImage read(String pathName){
        BufferedImage image = null;
        try{
            java.io.File f = new java.io.File(pathName);
            image = ImageIO.read(f);
        }catch(IOException e){
            System.out.println(e);
        }
        return image;
    }

    public static boolean write(java.awt.image.BufferedImage image, String pathName){
        boolean status = false;
        try{
            java.io.File f = new java.io.File(pathName);
            ImageIO.write(image, "jpg", f);
            status = true;
        }catch(IOException e){
            System.out.println(e);
        }
        return status;
    }
}
