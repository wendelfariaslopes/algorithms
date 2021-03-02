package algorithms.socket;

import java.net.*;
import java.util.Date;

import javax.swing.JOptionPane;

import java.io.*;

public class ServerSide extends Thread {
	
	private ServerSocket serverSocket;

	public ServerSide(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		int timeOut = Integer.parseInt(JOptionPane.showInputDialog( "Wait request from Client for (seconds):"));
		
		serverSocket.setSoTimeout(timeOut * 1000);
	}

	public void run() {
		while (true) {
			try {
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();

				//System.out.println("Just connected to " + server.getRemoteSocketAddress());
				
				DataInputStream in = new DataInputStream(server.getInputStream());

				System.out.println(in.readUTF());
			
				
				DataOutputStream out = new DataOutputStream(server.getOutputStream());

				out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + " "+new Date());
				
				server.close();

			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public static void main(String[] args) {
		int port = Integer.parseInt("6071");
		try {
			Thread t = new ServerSide(port);
			t.start();
			//t.sleep(5000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
