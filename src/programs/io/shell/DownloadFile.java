package programs.io.shell;

import java.io.File;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

public class DownloadFile {
	
	private static final String PATH_LOCAL_SHELL_SCRIPTS="C:\\Users\\wlopes\\eclipse-workspace\\codec\\src\\codec\\shell\\";
	private static final String PATH_REMOTE_SERVICE="//data/versions/wiseguy/log/prod/checklog-20191123-102145-cron.log";


	private static final String user = "wlopes";
	private static final String password = "#Enrico#Emmy#912980";
	private static final String host = "ny2-lia-001.prod.tradingscreen.com"; // UAT-DEV machine for wiseguy
	//private static final String host = "ny2-lia-001.uatprod.tradingscreen.com";
	// // UAT-DEV machine for wiseguy
	// private static final String host = "ny2-lia-001.prod.tradingscreen.com"; //
	// PROD machine for wiseguy
	private static final int port = 22;

	public static void main(String[] args) {

		String remote = PATH_REMOTE_SERVICE;
		String local = PATH_LOCAL_SHELL_SCRIPTS+"checklog-20191123-102145-cron.log";

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
