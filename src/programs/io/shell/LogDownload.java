package programs.io.shell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import programs.io.Property;

public class LogDownload {

	private static final String PATH_LOCAL_LOG = "C:\\Users\\wlopes\\Documents\\Logs\\";
	private static final String PATH_REMOTE_LOG = "/app/core/log/";

	private static final String user = Property.read("user");
	private static final String password = Property.read("password");

	public static void main(String[] arg) {

		String app = JOptionPane.showInputDialog("Name of app to download logs");

		List<String> listHost = new ArrayList<>();
		listHost.add("ny2-lia-001.uatdev.tradingscreen.com");
		listHost.add(app);

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

			// List all files of the path
			System.out.println();
			System.out.println("List all file(s) of " + app);
			List<String> listAll = (List<String>) listFiles(PATH_REMOTE_LOG, session); // Get all files in this path
			List<String> result = listAll.stream().filter(l -> l.startsWith(app+".log")).collect(Collectors.toList()); 
			System.out.println();
			
			// Download processing
			System.out.println("Start to download file(s)");
			LogDownload df = new LogDownload();
			// If the list is not empty
			for (String fileName : result) {
				String msg = "--- Started download: "+ fileName;
				try {
					if (df.downloadFile(PATH_REMOTE_LOG + fileName, PATH_LOCAL_LOG + fileName, session)) {
						msg += " --> Sucess download!!!";
					} else {
						msg += "********* Error ***********";
					}
				} catch (JSchException e) {
					msg += e.getMessage();
				}
				System.out.println(msg + " --> End download.");
			}
			System.out.println("End to download file(s)");
			System.out.println();

			// Process to disconnect with all sessions
			System.out.println("##### Start disconnection process.");
			for (int i = sessions.length - 1; i >= 0; i--) {
				System.out.println("Disconnect from: " + sessions[i].getHostKeyAlias());
				sessions[i].disconnect();
				System.out.println("Disconnected");
			}
			System.out.println("##### End disconnection process.");

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("----------------- Finished Program ----------------");
	}

	private static List<String> listFiles(String path, Session session) {

		ChannelSftp sftp = null;
		try {
			sftp = (ChannelSftp) session.openChannel("sftp");
			sftp.connect();
		} catch (JSchException e) {
			e.printStackTrace();
		}

		List<String> list = null;

		try {

			sftp.cd(path);
			List entryList = sftp.ls(path);

			list = new ArrayList<>();

			for (int i = 0; i < entryList.size(); i++) {
				LsEntry entry = (LsEntry) entryList.get(i);
				list.add(entry.getFilename());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sftp != null)
				sftp.disconnect();
		}

		return list;
	}

	private boolean downloadFile(String remote, String local, Session session) throws JSchException {

		boolean status = false;
		ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
		sftpChannel.connect();

		try {
			sftpChannel.get(remote, local);
			status = true;
		} catch (SftpException e) {
			e.printStackTrace();
		}
		return status;
	}

}