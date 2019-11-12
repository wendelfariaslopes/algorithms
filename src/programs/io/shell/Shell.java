package programs.io.shell;

import com.jcraft.jsch.*;
import javax.swing.*;

public class Shell {
	
	private static final String user = "wlopes";
	private static final String password = "#Enrico#Emmy#912980";
	private static final String host = "ny2-lia-001.prod.tradingscreen.com";
	private static final int port = 22;
	
	public static void main(String[] arg) {

		try {
			JSch jsch = new JSch();

			Session session = jsch.getSession(user, host, port);
			session.setPassword(password);

			UserInfo ui = new MyUserInfo() {
				public void showMessage(String message) {
					JOptionPane.showMessageDialog(null, message);
				}

				public boolean promptYesNo(String message) {
					Object[] options = { "yes", "no" };
					int foo = JOptionPane.showOptionDialog(null, message, "Warning", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					return foo == 0;
				}

			};

			session.setUserInfo(ui);
			session.connect(30000); // making a connection with timeout.

			Channel channel = session.openChannel("shell");
				channel.setInputStream(System.in);
				channel.setOutputStream(System.out);
			
			channel.connect(3 * 1000);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static abstract class MyUserInfo implements UserInfo, UIKeyboardInteractive {
		public String getPassword() {
			return null;
		}

		public boolean promptYesNo(String str) {
			return false;
		}

		public String getPassphrase() {
			return null;
		}

		public boolean promptPassphrase(String message) {
			return false;
		}

		public boolean promptPassword(String message) {
			return false;
		}

		public void showMessage(String message) {
		}

		public String[] promptKeyboardInteractive(String destination, String name, String instruction, String[] prompt,
				boolean[] echo) {
			return null;
		}
	}
}
