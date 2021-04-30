package ssh.io;


import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SlogDownloadFile  {
	
	private static String NAME_FILE = "tsom_upgrade_mc_VERS_CH-50941-20210420-092844-adhoc.log";
	private static String ENVIRONMENT = "uatdev";

	private static final String PATH_REMOTE = "/data/versions/wiseguy/log/adhoc/";
	private static final String PATH_LOCAL = "C:\\Users\\wlopes\\IdeaProjects\\slog\\src\\main\\resources\\input\\";
	private static final String user = "wlopes";
	private static final String password = "#Enrico#Emmy#912980";
	private static String host= "ny2-lia-001."+ENVIRONMENT+".tradingscreen.com";
	private static final int port = 22;

	public static void main(String[] args) {

		SlogDownloadFile s = new SlogDownloadFile();

		String remote = "/data/versions/wiseguy/log/adhoc/tsom_upgrade_mc_VERS_CH-50941-20210420-092844-adhoc.log";
		String local = PATH_LOCAL + NAME_FILE;

		System.out.println("ENVIRONMENT: " + ENVIRONMENT);
		System.out.println("Log file: " + NAME_FILE);
		System.out.println("Host: " + host);

		System.out.println("Remote Path: " + remote);
		System.out.println("Local Path: " + remote);

		try {
			if (s.downloadFile(remote, local)) {
				System.out.println("Sucess download");
			} else {
				System.out.println("********* Failure Error ***********");
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
			
			sftpChannel.disconnect();

		} catch (SftpException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static boolean downloadFile(String host, String remote, String local) throws JSchException {

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
			
			sftpChannel.disconnect();

		} catch (SftpException e) {
			e.printStackTrace();
		}
		return status;
	}

}
