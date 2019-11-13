package programs.io;


import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class DownloadFile {
	
	private static final String user = "wlopes";
	private static final String password = "#Enrico#Emmy#912980";
	private static final String host = "ny2-lia-001.prod.tradingscreen.com";
	private static final int port = 22;
	
	
	public static void main(String[] args) {
		
		String remote = "/data/versions/wiseguy/log/prod/status-20191108-195145-adhoc.log";
		String local = "C:\\Users\\wlopes\\IdeaProjects\\slog\\src\\main\\resources\\input\\checklog.log";
		
		DownloadFile df = new DownloadFile();
		
		try {
			if (df.downloadFile(remote, local)) {
				System.out.println("Sucess try to download");
			} else {
				System.out.println("********* Error ***********");
			}
		} catch (JSchException e) {
			e.printStackTrace();
		}

	}

	private boolean downloadFile(String remote, String local) throws JSchException {

		boolean status = false;
		JSch jsch = new JSch();
		Session session = jsch.getSession(user, host, port);
		session.setPassword(password);
		session.setConfig("StrictHostKeyChecking", "no");
		System.out.println("Establishing Connection...");
		session.connect();
		System.out.println("Connection established.");
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


}
