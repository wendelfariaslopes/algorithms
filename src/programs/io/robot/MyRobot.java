package programs.io.robot;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MyRobot {

	public static void main(String[] args) throws AWTException {

		Robot robot = new Robot();

		try {

			// get the position of mouse
			Point p = MouseInfo.getPointerInfo().getLocation();
			robot.delay(200);
			clickRigth(robot, (int) p.getX() - 30, (int) p.getY() - 30);
			
			for(int i=0; i < 5; i++) {
				robot.delay(1000);
				type(robot, KeyEvent.VK_UP);
			}
			
			
			System.exit(0);

		} catch (AWTException e) {

			e.printStackTrace();
		}

	}

	public static void clickLeft(Robot bot, int x, int y) throws AWTException {
		bot.mouseMove(x, y);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	public static void clickRigth(Robot bot, int x, int y) throws AWTException {

		bot.mouseMove(x, y);
		bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
	}

	private static void type(Robot robot, int i) {
		robot.delay(40);
		robot.keyPress(i);
		robot.keyRelease(i);
	}
	
	private static Dimension screenSize() {
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	     Rectangle screenRectangle = new Rectangle(screenSize);
	    return screenSize; 
	}
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}

}
