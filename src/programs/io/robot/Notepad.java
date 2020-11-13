package programs.io.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;

public class Notepad {
	
	public static void main(String[] args) throws IOException, AWTException, InterruptedException {
		String command = "notepad.exe";
		Runtime run = Runtime.getRuntime();
		run.exec(command);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create an instance of Robot class
		Robot robot = new Robot();

		// Press keys using robot. A gap of
		// of 200 mili seconds is added after
		// every key press
		robot.keyPress(KeyEvent.VK_H);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_L);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_L);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_O);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_SPACE);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_F);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_R);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_O);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_M);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_SPACE);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_G);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_K);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_S);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_F);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_O);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_R);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_G);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_K);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_S);
		
		System.exit(0);
	}
}
