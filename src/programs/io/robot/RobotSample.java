package programs.io.robot;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Rivet Systems
 */
public class RobotSample {
	
	private static final String PATH = "C:\\Users\\Utilisateur\\git\\algorithms\\src\\programs\\io\\screen\\";

    public static void main(String[] args) throws Exception {
        for(int i=0;i<2;i++){
           captureScreen(PATH+System.currentTimeMillis()+"_"+i+".PNG"); 
        }
    }

    public static void captureScreen(String fileName) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write(image, "png", new File(fileName));

    }
}