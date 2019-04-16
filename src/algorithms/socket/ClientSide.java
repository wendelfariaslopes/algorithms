package algorithms.socket;

import java.net.*;
import java.io.*;

public class ClientSide {

	public static void main(String[] args) {
		
		int port = Integer.parseInt("6071");
		try {
			Thread t = new ServerSide(port);
		
			t.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();

		}
		
		String serverName = "127.0.0.1";
		//int port = Integer.parseInt(args[1]);
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);

			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			out.writeUTF("Hello from " + client.getLocalSocketAddress());
		
			
			
			
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);

			System.out.println("Server says " + in.readUTF());
			
			
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
