package programs.io;

import java.io.File;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

public class DownloadFile {
	
	private static final String PATH_SHELL_SCRIPTS="C:\\Users\\wlopes\\eclipse-workspace\\codec\\src\\codec\\shell\\";

	private static final String[] APP_WITHOUT_LIFE_CYCLE = { "complianceserver-1-a@ny2-lab-001.prod.tradingscreen.com",
			"complianceserver-1-b@ny2-lab-002.prod.tradingscreen.com",
			"complianceserver-2-a@to1-lab-001.prod.tradingscreen.com",
			"complianceserver-2-b@to1-lab-002.prod.tradingscreen.com",
			"complianceserver-3-a@hk2-lab-001.prod.tradingscreen.com",
			"complianceserver-3-b@hk2-lab-002.prod.tradingscreen.com",
			"complianceserver-5-a@pa1-lab-003.prod.tradingscreen.com",
			"complianceserver-5-b@pa1-lab-004.prod.tradingscreen.com",
			"portfolios-1-a@ny2-lab-001.prod.tradingscreen.com", "portfolios-1-b@ny2-lab-002.prod.tradingscreen.com",
			"portfolios-2-a@to1-lab-001.prod.tradingscreen.com", "portfolios-2-b@to1-lab-002.prod.tradingscreen.com",
			"portfolios-3-a@hk2-lab-001.prod.tradingscreen.com", "portfolios-3-b@hk2-lab-002.prod.tradingscreen.com",
			"portfolios-5-a@pa1-lab-003.prod.tradingscreen.com", "portfolios-5-b@pa1-lab-004.prod.tradingscreen.com",
			"possrv-3-a@hk2-lab-001.prod.tradingscreen.com", "possrv-3-b@hk2-lab-002.prod.tradingscreen.com",
			"newman-1-a@ny2-lab-001.prod.tradingscreen.com", "newman-1-b@ny2-lab-002.prod.tradingscreen.com",
			"supportportal-1-a@ny2-laa-010.prod.tradingscreen.com",
			"supportportal-1-b@ny2-laa-011.prod.tradingscreen.com",
			"supportportal-2-a@to1-laa-007.prod.tradingscreen.com",
			"supportportal-2-b@to1-laa-008.prod.tradingscreen.com",
			"supportportal-3-a@pa1-laa-008.prod.tradingscreen.com",
			"supportportal-3-b@pa1-laa-009.prod.tradingscreen.com",
			"supportportal-4-a@hk2-laa-005.prod.tradingscreen.com",
			"supportportal-4-b@hk2-laa-006.prod.tradingscreen.com" };

	private static final String DIR_LOG = "/app/core/log";
	private static final String VERIFY_LIFE_CYCLE = "zgrep LIFECYCLE `ls -rt complianceserver-1-b.log*`";

	private static final String user = "wlopes";
	private static final String password = "#Enrico#Emmy#912980";
	//private static final String host = "ny2-lia-001.uatdev.tradingscreen.com"; // UAT-DEV machine for wiseguy
	private static final String host = "ny2-lia-001.uatprod.tradingscreen.com";
	// // UAT-DEV machine for wiseguy
	// private static final String host = "ny2-lia-001.prod.tradingscreen.com"; //
	// PROD machine for wiseguy
	private static final int port = 22;

	public static void main(String[] args) {

		String remote = "/data/versions/wiseguy/log/uatprod/checklog-20191129-153653-cron.log";

		// String remote =
		// "/data/versions/wiseguy/log/prod/status-20191108-195145-adhoc.log"; // My
		// MacBook
		String local = "C:\\Users\\wlopes\\IdeaProjects\\slog\\src\\main\\resources\\input\\checklog-20191129-153653-cron.log"; // local
																																// windows
																																// machine

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

	public static void fileFetch(String host, String user, String keyLocation, String sourceDir, String destDir) {
		JSch jsch = new JSch();
		Session session = null;
		try {
			// set up session
			session = jsch.getSession(user, host);
			// use private key instead of username/password
			session.setConfig("PreferredAuthentications", "publickey,gssapi-with-mic,keyboard-interactive,password");
			jsch.addIdentity(keyLocation);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			// copy remote log file to localhost.
			ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();
			channelSftp.get(sourceDir, destDir);
			channelSftp.exit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.disconnect();
		}
	}

	

}
