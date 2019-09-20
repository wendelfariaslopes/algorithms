package programs.io;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;

/**
 * A jump box (or bastion host) is a common security setup for servers. It
 * requires users to first SSH to the jump box and then SSH to the remote
 * server. It means the remote server can limit what ports are open to the
 * internet and user access control can be managed on the jump box. 
 *
 * In this example, there is a webapp on port 8080 on the remote server that we want to
 * make available on localhost 8080. Specifically, this is the Apache Tomcat
 * Manager webapp on the remote server, normally not available to the Internet
 * for security reasons (e.g. blocked by forwarding rules or proxy settings).
 * 
 * Another example might be to map 3306 from localhost to a remote MySQL
 * database. This would allow connection to the remove database over the
 * localhost 3306 port. For that scenario, simply replace 8080 with 3306.
 * 
 * The jumpbox should be reachable via:
 *
 * ssh -i ssh-key.pem USERNAME@JUMP_BOX
 *
 * And then from the JUMP_BOX the REMOTE_SERVER should be reachable via:
 *
 * ssh -i ssh-key.pem USERNAME@REMOTE_SERVER
 *
 * Where the `ssh-key.pem` is the ssh key file for each.
 *
 */
public class JumpBox {
	/**In this example localhost 8080 is connected to JUMP_BOX 8080 which is then connected to REMOTE_SERVER 8080.
	 * Using SSH to chain the connections together to create a tunnel using command line parameters looks like this:
	 */
	private static final String COMMAND_LINE_TUNNEL = "ssh -t -L 8080:127.0.0.1:8080 USERNAME@JUMP_BOX ssh -L 8080:127.0.0.1:8080 USERNAME@REMOTE_SERVER";
	
	/**
	 * It is also possible to use keys so that there are no password prompts:
	  	ssh -t -i JUMP_BOX_KEY_FILE.pem -L 8080:127.0.0.1:8080 USERNAME@JUMP_BOX \
		ssh    -i INTERNAL_SERVER_KEY_FILE.pem -L 8080:127.0.0.1:8080 USERNAME@REMOTE_SERVER
	 * 
	 */

	private static final String STRICT_HOST_KEY_CHECKING_KEY = "StrictHostKeyChecking";
	private static final String STRICT_HOST_KEY_CHECKING_VALUE = "no";
	private static final String CHANNEL_TYPE = "shell";

	public static void main(String[] args) throws IOException, AuthenticationException, JSchException {

		// TODO: Catch exceptions and close any open connections, don't leave them open!

		// TODO: Load properties from configuration file

		/*
		 * BEGIN required customization
		 */

		// Properties for tunnel and server
		String sshHost1 = "JUMP_BOX";
		String sshUser1 = "USERNAME";
		String sshHost2 = "REMOTE_SERVER";
		String sshuser2 = "USERNAME";

		// NOTE: Shared key file between sshHost1 and sshHost2, common for providers
		// like AWS.
		String sshKeyFile = "ssh-key.pem";

		// Properties for Tomcat
		String tomcatUsername = "admin";
		String tomcatPassword = "SEEKRIT";
		int tomcatPort = 8080;

		// NOTE: hard coding `example-web` path to match `/tmp/example-web.war` file

		String tomcatUploadUrl = "http://localhost:8080/manager/text/deploy?path=/example-web&update=true";
		String tomcatUploadFile = "/tmp/example-web.war";

		/*
		 * END required customization
		 */

		Session session = null;
		Session[] sessions = new Session[2];

		// Create JSch object and set pem key
		JSch jsch = new JSch();
		jsch.addIdentity(sshKeyFile);
		jsch.setConfig(STRICT_HOST_KEY_CHECKING_KEY, STRICT_HOST_KEY_CHECKING_VALUE);

		// Open first session
		System.out.println("Attempting connection to " + sshUser1 + "@" + sshHost1);
		sessions[0] = session = jsch.getSession(sshUser1, sshHost1, 22);
		session.connect();
		System.out.println("Connected to " + sshUser1 + "@" + sshHost1);

		// Set port forwarding hop 1
		System.out.println("Attempting to start port forwarding");
		int assignedPort = session.setPortForwardingL(0, sshHost2, 22);
		System.out.println("Completed port forwarding");

		// Open second session
		System.out.println("Attempting connection to " + sshuser2 + "@" + sshHost2);

		sessions[1] = session = jsch.getSession(sshuser2, "127.0.0.1", assignedPort);
		session.setHostKeyAlias(sshHost2);
		session.connect();
		System.out.println("Connected to " + sshuser2 + "@" + sshHost2);

		// Set port forwarding hop 2
		System.out.println("Attempting to start port forwarding");
		int assignedPort2 = session.setPortForwardingL(tomcatPort, "127.0.0.1", tomcatPort);
		System.out.println("Completed port forwarding  localhost: " + tomcatPort + " -> 127.0.0.1:" + tomcatPort);

		Channel channel = session.openChannel(CHANNEL_TYPE);
		channel.connect();

		// For Tomcat 7, running the manager webapp.
		//
		// Command line call is:
		//
		// curl -X PUT \
		// --user root:password \
		// -T "example-web.war" \
		// "http://manager:manager@localhost:8080/manager/text/deploy?path=/example-web&update=true"

		logger.debug("Attempting to upload file: " + tomcatUploadFile);
		logger.debug("Attempting to upload to:   " + tomcatUploadUrl);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(tomcatUploadUrl);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(tomcatUsername, tomcatPassword);
		httpPut.addHeader(new BasicScheme().authenticate(credentials, httpPut, null));
		File file = new File(tomcatUploadFile);
		builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, tomcatUploadFile);
		HttpEntity multipart = builder.build();
		httpPut.setEntity(multipart);

		// Execute http post request
		CloseableHttpResponse response = httpclient.execute(httpPut);

		// Evaluate response
		logger.debug("Request status:" + response.getStatusLine());
		if (response.getStatusLine().getStatusCode() != 200) {
			response.close();
			httpclient.close();

			logger.error("Request was not 200 OK, ");
			logger.debug("Failed to upload file: " + tomcatUploadFile);
			logger.debug("Failed to upload to:   " + tomcatUploadUrl);
		} else {
			response.close();
			httpclient.close();

			logger.debug("Completed uploading file: " + tomcatUploadFile);
			logger.debug("Completed uploading to:   " + tomcatUploadUrl);
		}

		// Close tunnel
		System.out.println("Closing tunnels");
		for (int i = sessions.length - 1; i >= 0; i--) {
			System.out.println("Closing " + sessions[i].getUserName() + "@" + sessions[i].getHost());
			sessions[i].disconnect();
		}

		return;
	}
}
