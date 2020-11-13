package programs.io.robot;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Example of AWT Robot to drive a GUI.
 *
 * @author jdalbey
 */
public class RobotTester
{

    GUI gui;     // class to be tested
    Robot robot;

    public static void main(String[] args) throws AWTException
    {
        new RobotTester();
    }

    public RobotTester() throws AWTException
    {
        // make a GUI and set visible
        gui = new GUI();
        gui.setVisible(true);

        robot = new Robot();
        robot.setAutoDelay(2000);
        robot.setAutoWaitForIdle(true);

        robot.delay(1000);

        // Enter input data into text fields
        type("b");
        type(KeyEvent.VK_TAB);
        type("4");
        type(KeyEvent.VK_TAB);
        robot.delay(1000);

        type(KeyEvent.VK_SPACE);  // click the button

        robot.delay(2000);

        System.exit(0);
    }

    private void leftClick()
    {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(200);
    }

    private void type(int i)
    {
        robot.delay(40);
        robot.keyPress(i);
        robot.keyRelease(i);
    }

    private void type(String s)
    {
        byte[] bytes = s.getBytes();
        for (byte b : bytes)
        {
            int code = b;
            // keycode only handles [A-Z] (which is ASCII decimal [65-90])
            if (code > 96 && code < 123)
            {
                code = code - 32;
            }
            robot.delay(40);
            robot.keyPress(code);
            robot.keyRelease(code);
        }
    }
}
