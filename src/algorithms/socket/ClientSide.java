package algorithms.socket;

import java.net.*;
import java.io.*;

public class ClientSide implements Runnable {

	public static void main(String[] args) {
		
		Thread t = new Thread(new ClientSide());
		t.start();

		
	}

	public void run() {
		task() ;
	}
	
	private void task() {
		
		int port = Integer.parseInt("6071");
		String serverName = "127.0.0.1";

		try {
			// System.out.println("Connecting to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);

			System.out.println("Connected to " + client.getRemoteSocketAddress());

			for (int i = 0; i < 10; i++) {

				
				
			
				
				OutputStream outToServer = client.getOutputStream();
				DataOutputStream out = new DataOutputStream(outToServer);

				out.writeUTF("Hello from " + client.getLocalSocketAddress());

				InputStream inFromServer = client.getInputStream();
				DataInputStream in = new DataInputStream(inFromServer);

				System.out.println("Server says " + in.readUTF());

			}

			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
