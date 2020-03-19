package ssh.io.shell;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.ChannelSftp.LsEntrySelector;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import ssh.io.Property;

public class Connect2 {

	private static List<String> lines = new ArrayList<>();

	private static final String PATH_LOCAL_SHELL_SCRIPTS = "C:\\Users\\wlopes\\eclipse-workspace\\codec\\src\\codec\\shell\\";
	private static final String PATH_REMOTE_SERVICE = "/data/versions/ts/current/bin/";

	private static final String user = Property.read("user");
	private static final String password = Property.read("password");

	public static void main(String[] arg) {

		String remote = PATH_REMOTE_SERVICE + "services.UATPROD.cf";
		String local = PATH_LOCAL_SHELL_SCRIPTS + "servixxxxxces.UATPROD.cf";

		List<String> listHost = new ArrayList<>();
		listHost.add("ny2-lia-001.uatdev.tradingscreen.com");
		listHost.add("ny2-lab-007.uatdev.tradingscreen.com");

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
			
//			List<String> list = (List<String>) getListFiles2(PATH_REMOTE_SERVICE, session);
//			
//			list.forEach(l -> System.out.println(l));
			
			System.out.println();
			Connect2 df = new Connect2();

			try {
				if (df.downloadFile(remote, local, session)) {
					System.out.println("Sucess try to download");
				} else {
					System.out.println("********* Error ***********");
				}
			} catch (JSchException e) {
				e.printStackTrace();
			}

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

	private static List<String> getListFiles(String path, Session session) throws JSchException {

		System.out.println("Creating SFTP Channel.");
		ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
		sftp.connect();
		System.out.println("SFTP Channel created.");
		System.out.println();

		List<String> list = new Vector<String>();

		try {

			LsEntrySelector selector = new LsEntrySelector() {
				public int select(LsEntry entry) {
					final String filename = entry.getFilename();
					if (filename.equals(".") || filename.equals("..")) {
						return CONTINUE;
					}
					
					list.add(entry.getFilename());
					
//					if (entry.getAttrs().isLink()) {
//						//filelist.addElement(filename);
//						filelist.add(filename);
//					} else if (entry.getAttrs().isDir()) {
//						// if (keepDirectory(filename)) {
//						//filelist.addElement(entry.getFilename());
//						filelist.add(entry.getFilename());
//						// }
//					} else {
//						// if (keepFile(filename)) {
//						//filelist.addElement(entry.getFilename());
//						filelist.add(entry.getFilename());
//						// }
//					}
					return CONTINUE;
				}
			};

			sftp.ls(path, selector);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}
	
	
	private static List<String> getListFiles2(String path, Session session)  {

		System.out.println("Creating SFTP Channel.");
		ChannelSftp sftp = null;
		try {
			sftp = (ChannelSftp) session.openChannel("sftp");
			sftp.connect();
			System.out.println("SFTP Channel created.");
			System.out.println();	
		} catch (JSchException e) {
			e.printStackTrace();
		}
		
		List<String> list = null;

		try {

			sftp.cd(path);
			List entryList = sftp.ls(path);
			
			list = new ArrayList<>();
			
	        for(int i=0; i< entryList.size();i++){
	            LsEntry entry = (LsEntry) entryList.get(i);
	            list.add(entry.getFilename());
	        }

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
            if(sftp != null) sftp.disconnect();
        }
		
		return list;
	}

	private boolean downloadFile(String remote, String local, Session session) throws JSchException {

		boolean status = false;
		System.out.println("Creating SFTP Channel.");
		ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
		sftpChannel.connect();
		System.out.println("SFTP Channel created.");
		System.out.println();

		try {

			System.out.println("----------------- Started download ----------------");
			sftpChannel.get(remote, local);
			status = true;
			System.out.println("----------------- Finished download ----------------");
			System.out.println();

		} catch (SftpException e) {
			e.printStackTrace();
		}
		return status;
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

					// System.out.print(new String(tmp, 0, i));

					String line = new String(tmp, 0, i);
					if (!line.isEmpty() && line.trim().length() > 2) {
						lines.add("+++  " + line.trim());
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

			lines.forEach(l -> System.out.println(l));

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