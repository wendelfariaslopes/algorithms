package programs.io.robot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;

import javax.swing.JFrame;

/**
 * Methods used :
 * 
 * getPointerInfo() : 
 * 1. Returns a PointerInfo instance that represents the current location of the mouse pointer. 
 * 2. getLocation() : returns a point instance that represents location
 * 3. createScreenCapture(Rectangle r) : captures a part of screen which is within the rectangle r. 
 * 4. drawImage(Image i, int x, int y, ImageObserver o) : draws an image at x, y position on the screen specifying an image observer 
 * 5. drawImage(Image i, int x, int y,,int w, int h, ImageObserver o) : draws an image at x, y position and specified width and height on the screen specifying an image observer
 * 
 * @author Utilisateur
 *
 */
public class Magnify extends JFrame {

	// object
	static Magnify m;

	// image
	Image i;

	// default constrcutor
	Magnify() {
		// create a frame
		super("Magnify");

		// set size of frame
		setSize(400, 400);
		show();

		// function to magnify the image
		work();
	}

	// main function
	public static void main(String args[]) {

		// object of class
		m = new Magnify();
	}

	public void work() {
		try {
			// create a robot
			Robot r = new Robot();

			// while the frame is visible
			while (isVisible()) {
				// get the position of mouse
				Point p = MouseInfo.getPointerInfo().getLocation();

				// create a screen capture around the mouse pointer
				i = r.createScreenCapture(new Rectangle((int) p.getX() - 30, (int) p.getY() - 30, 150, 150));

				// repaint the conatiner
				repaint();
			}
			// exit the program
			System.exit(0);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	// paint function
	public void paint(Graphics g) {

		// draw the image
		g.drawImage(i, 0, 0, 400, 400, this);
	}
}
