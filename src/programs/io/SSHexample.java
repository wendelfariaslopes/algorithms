package programs.io;

import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SSHexample {

	private static final String user = "wlopes";
	private static final String password = "#Enrico#Emmy#912980";
	private static final String host = "ny2-lia-001.prod.tradingscreen.com";
	private static final int port = 22;
	
	
	private static final String TSOM_LOG_ARCHIVE = "/opt/versions/prodlogs/";
	private static final String[] FOLDERS = {"current","prodlogs","prodlogs.0","prodlogs.1","prodlogs.2","prodlogs.3"};
	private static final String LOG_FOLDER = "log";

	public static void main(String[] args) {
		
//		String localNameFile = "checklog.log";
//		String remoteNameFile = "checklog-20190914-130124-cron.log";
		
//		String remote = "/data/versions/wiseguy/log/prod/checklog-20190914-130124-cron.log";
//		String local = "/Users/wendellopes/IdeaProjects/slog/src/main/resources/input/" + localNameFile;
		
		// cd /app/versions/prodlogs/prodlogs.3/log
		// ls -lrt tsom.19.a.log*
		// tsom.19.a.log.15.gz
		
		String remotePath = "/app/versions/prodlogs/prodlogs.3/log/";
		String remoteFileName = "tsom.19.a.log.15.gz";
		
		String localPath = "C:\\Users\\wlopes\\Downloads\\Logs\\";
		String localFileName = remoteFileName;
		
		
		String remote = remotePath + remoteFileName;
		String local = localPath + localFileName;

		SSHexample ssh = new SSHexample();
		
		try {
			if (ssh.downloadFile(remote, local)) {
				System.out.println("Sucess in download");
			} else {
				System.out.println("********* Error ");
			}
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ChannelSftp sftp = SSH();
		try {
			sftp.connect();
		} catch (JSchException e) {
			e.printStackTrace();
		}

		Vector fileList;
		try {
			fileList = sftp.ls("/");
			fileList.forEach(f -> {

				LsEntry entry = (LsEntry) f;
				System.out.println(entry.getFilename());

			});

		} catch (SftpException e) {
			e.printStackTrace();
		}

//		try {
//			JSch jsch = new JSch();
//			Session session = jsch.getSession(user, host, port);
//			session.setPassword(password);
//			session.setConfig("StrictHostKeyChecking", "no");
//			System.out.println("Establishing Connection...");
//			session.connect();
//			System.out.println("Connection established.");
//			System.out.println("Creating SFTP Channel.");
//			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
//			sftpChannel.connect();
//			
//			
//			System.out.println("SFTP Channel created.");

//			
//			InputStream out = null;
//			out = sftpChannel.get(remoteFile);
//			BufferedReader br = new BufferedReader(new InputStreamReader(out));
//			
//			
//			String line;
//			int numberLine = 0;
//			
//			while ((line = br.readLine()) != null && numberLine < 100) {
//				System.out.println(line);
//				numberLine++;
//			}
//			
//			br.close();
//			sftpChannel.disconnect();
//			session.disconnect();
//			
//		} catch (JSchException | SftpException | IOException e) {
//			System.out.println(e);
//		}
	}

	private boolean downloadFile(String remote, String local) throws JSchException {

//		JSch jsch = new JSch();
//		Session session = jsch.getSession(user, host);
//		session.setPassword(password);
//		session.connect();
//
//		ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
//		sftpChannel.connect();

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

	private static ChannelSftp SSH() {

		JSch jsch = new JSch();
		Session session;
		ChannelSftp sftpChannel = null;
		try {
			session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing Connection...");
			session.connect();
			System.out.println("Connection established.");
			System.out.println("Creating SFTP Channel.");
			sftpChannel = (ChannelSftp) session.openChannel("sftp");
		} catch (JSchException e) {
			e.printStackTrace();
		}

		System.out.println("SFTP Channel created.");
		System.out.println();

		return sftpChannel;
	}

	private void methods1() throws JSchException {

		JSch jsch = new JSch();
		Session session = jsch.getSession(user, host);
		session.setPassword(password);
		session.connect();

		ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
		sftpChannel.connect();

		try {
			sftpChannel.put("C:/source/local/path/file.zip", "/target/remote/path/file.zip");
		} catch (SftpException e) {
			e.printStackTrace();
		}

		try {
			sftpChannel.get("/source/remote/path/file.zip", "C:/target/local/path/file.zip");
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

}
