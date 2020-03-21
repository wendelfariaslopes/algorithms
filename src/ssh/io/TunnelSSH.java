package ssh.io;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class TunnelSSH {
    public static void main(String[] arg) {
        try {
            JSch jsch = new JSch();

            String user = "root";
            String host = "**.**.**.**";
            int port = 5555;
            String privateKey = "C:\\Users\\Test\\AppData\\Roaming\\Skype\\My Skype Received Files\\id_rsa";

            jsch.addIdentity(privateKey);
            //jsch.
            System.out.println("identity added ");

            Session session = jsch.getSession(user, host, port);
            System.out.println("session created.");

            // disabling StrictHostKeyChecking may help to make connection but makes it insecure
            // see http://stackoverflow.com/questions/30178936/jsch-sftp-security-with-session-setconfigstricthostkeychecking-no
            // 
            // java.util.Properties config = new java.util.Properties();
            // config.put("StrictHostKeyChecking", "no");
            // session.setConfig(config);

            session.connect();
            System.out.println("session connected.....");

            Channel channel = session.openChannel("sftp");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect();
            System.out.println("shell channel connected....");

            /*ChannelSftp c = (ChannelSftp) channel;

            String fileName = "test.txt";
            c.put(fileName, "./in/");
            c.exit();
            System.out.println("done");*/

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
