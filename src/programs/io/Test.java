package programs.io;

import static org.junit.Assert.assertEquals;

public class Test {
	
	
	private static final String SSH_PROD="ssh.prod";
	private static final String user = Property.read("user");
	private static final String password = Property.read("password");

	public static void main(String[] args) {
		System.out.println("sendCommand");

		/**
		 * YOU MUST CHANGE THE FOLLOWING FILE_NAME: A FILE IN THE DIRECTORY USER: LOGIN
		 * USER NAME PASSWORD: PASSWORD FOR THAT USER HOST: IP ADDRESS OF THE SSH SERVER
		 **/
		String command = "pwd";
		String connectionIP = Property.read(SSH_PROD);
		SSHManager instance = new SSHManager(user, password, connectionIP, "");
		String errorMessage = instance.connect();

		if (errorMessage != null) {
			System.out.println(errorMessage);

		}

		String expResult = "FILE_NAME\n";
		// call sendCommand for each command and the output
		// (without prompts) is returned
		String result = instance.sendCommand(command);
		// close only after all commands are sent
		instance.close();
		assertEquals(expResult, result);

	}

}
