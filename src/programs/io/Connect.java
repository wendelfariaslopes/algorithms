package programs.io;

import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Connect {

	private static final String user = "wlopes";
	private static final String password = "#Enrico#Emmy#912980";

	public static void main(String[] arg) {

		List<String> listHost = new ArrayList<>();
		listHost.add("ny2-lia-001.prod.tradingscreen.com");
		listHost.add("prod-tsom-19-a");

		arg = new String[listHost.size()];

		int k = 0;
		for (String host : listHost) {
			arg[k] = host;
			k++;
		}

		try {
			JSch jsch = new JSch();
			if (arg.length <= 1) {
				System.out.println("This program expects more arguments.");
				System.exit(-1);
			}

			Session session = null;
			Session[] sessions = new Session[arg.length];

			String host = arg[0];
			sessions[0] = session = jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			System.out.println("The session has been established to " + user + "@" + host);

			for (int i = 1; i < arg.length; i++) {
				host = arg[i];
				int assinged_port = session.setPortForwardingL(0, host, 22);
				System.out.println("portforwarding: " + "localhost:" + assinged_port + " -> " + host + ":" + 22);
				sessions[i] = session = jsch.getSession(user, "127.0.0.1", assinged_port);

				session.setPassword(password);
				session.setConfig("StrictHostKeyChecking", "no");
				System.out.println("Establishing Connection...");
				session.setHostKeyAlias(host);
				session.connect();
				System.out.println("Connection established.");
				System.out.println("Creating SFTP Channel.");
				System.out.println("The session has been established to " + user + "@" + host);
			}

			ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
			sftp.connect();

			System.out.println();
			System.out.println("----------------- Start Something ----------------");
			System.out.println();

			sftp.disconnect();

			for (int i = sessions.length - 1; i >= 0; i--) {
				System.out.println("Disconnect from: " + sessions[i].getHostKeyAlias());
				sessions[i].disconnect();
				System.out.println("Disconnected ----------------");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("----------------- Finished Program ----------------");
	}

}