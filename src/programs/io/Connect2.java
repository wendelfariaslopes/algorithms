package programs.io;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Connect2 {
	
	private static List<String> lines = new ArrayList<>();

	private static final String SSH_PROD = "ssh.prod";
	private static final String SSH_UAT_PROD = "ssh.uatprod";
	private static final String SSH_UAT_DEV = "ssh.uatdev";
	private static final String user = Property.read("user");
	private static final String password = Property.read("password");

	public static void main(String[] arg) {

		List<String> listHost = new ArrayList<>();
		listHost.add(Property.read(SSH_UAT_PROD));
		// listHost.add("prod-"+Property.read("tsom.19.a"));
		// listHost.add("prod-"+Property.read("tsom.17.a"));

		arg = new String[listHost.size()];

		int k = 0;
		for (String host : listHost) {
			arg[k] = host;
			k++;
		}

		try {

			JSch jsch = new JSch();
			if (arg.length < 1) {
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
				System.out.println("Creating Channel.");
				System.out.println("The session has been established to " + user + "@" + host);
			}
			System.out.println();
			System.out.println();

			String s = sendCommand2(Property.read("grep.failed.to.start"), session);

			System.out.println(s);

			System.out.println();
			System.out.println();

//			ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
//			sftp.connect();

//			ChannelExec exec = (ChannelExec) session.openChannel("exec");
//			exec.setCommand("pwd");
//			exec.connect();

//			System.out.println();
//			System.out.println("----------------- Start Something ----------------");
//			System.out.println();

//			sftp.disconnect();

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

	public static String sendCommand(String command, Session session) {
		StringBuilder outputBuffer = new StringBuilder();

		try {
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			InputStream commandOutput = channel.getInputStream();
			channel.connect();
			int readByte = commandOutput.read();

			while (readByte != 0xffffffff) {
				outputBuffer.append((char) readByte);
				readByte = commandOutput.read();

			}

			channel.disconnect();
		} catch (IOException ioX) {
			System.out.println(ioX.getMessage());
			return null;
		} catch (JSchException jschX) {
			System.out.println(jschX.getMessage());
			return null;
		}

		return outputBuffer.toString();
	}
	
	
	public static String sendCommand2(String command, Session session) {
		StringBuilder outputBuffer = new StringBuilder();

		try {
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			InputStream in = channel.getInputStream();
			channel.connect();

			byte[] tmp = new byte[1024];

			while (true) {

				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;

					//System.out.print(new String(tmp, 0, i));
					
					String line = new String(tmp, 0, i);
					if(!line.isEmpty() && line.trim().length()>2) {
						lines.add("+++  "+line.trim());
					}
					
					
				}

				if (channel.isClosed()) {
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}

				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			
			lines.forEach(l-> System.out.println(l));

			channel.disconnect();
		} catch (IOException ioX) {
			System.out.println(ioX.getMessage());
			return null;
		} catch (JSchException jschX) {
			System.out.println(jschX.getMessage());
			return null;
		}

		return outputBuffer.toString();
	}

	public static String getBefore(String s) {

		String[] split = s.split("failed to start");

		return split[0].trim();

	}
}