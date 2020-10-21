package shellcommand;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class IP {

	private static String OS = System.getProperty("os.name").toLowerCase();

	public static void main(String args[]) throws Exception {

		System.out.println(OS);

		if (isWindows()) {
			System.out.println("This is Windows");
		} else if (isMac()) {
			System.out.println("This is Mac");
		} else if (isUnix()) {
			System.out.println("This is Unix or Linux");
		} else if (isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}

		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println("IP Address:- " + inetAddress.getHostAddress());
		System.out.println("Host Name:- " + inetAddress.getHostName());
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println("IP Address:- " + localHost.getHostAddress());
		InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
		System.out.println("Host Name:- " + loopbackAddress);

		Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
		while (e.hasMoreElements()) {
			NetworkInterface n = (NetworkInterface) e.nextElement();
			Enumeration<InetAddress> ee = n.getInetAddresses();
			while (ee.hasMoreElements()) {
				InetAddress i = (InetAddress) ee.nextElement();
				System.out.println(i.getHostAddress());

			}
		}

		/**
		 * This way works well when there are multiple network interfaces. It always
		 * returns the preferred outbound IP. The destination 8.8.8.8 is not needed to
		 * be reachable. Connect on a UDP socket has the following effect: it sets the
		 * destination for Send/Recv, discards all packets from other addresses, and -
		 * which is what we use - transfers the socket into "connected" state, settings
		 * its appropriate fields. This includes checking the existence of the route to
		 * the destination according to the system's routing table and setting the local
		 * endpoint accordingly. The last part seems to be undocumented officially but
		 * it looks like an integral trait of Berkeley sockets API (a side effect of UDP
		 * "connected" state) that works reliably in both Windows and Linux across
		 * versions and distributions.
		 * 
		 * this method will give the local address that would be used to connect to the
		 * specified remote host. There is no real connection established, hence the
		 * specified remote ip can be unreachable.
		 * 
		 */

		String ip = "";

		try (final DatagramSocket socket = new DatagramSocket()) {

			if (isMac()) {
				socket.connect(new InetSocketAddress("google.com", 80));
				ip = socket.getLocalAddress().getHostAddress();
			} else {
				socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
				ip = socket.getLocalAddress().getHostAddress();

			}

			System.out.println("MY ip " + ip);
		}

	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}

}
