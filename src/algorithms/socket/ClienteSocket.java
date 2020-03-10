package algorithms.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClienteSocket {

	public static void main(String[] args) {
		int port = 5298;
		String IP = "localhost";
		// Caminho do destinho
		//String caminhoDestino = "C:/Temp/Arquivo/";
		String caminhoDestino = "C:/Documents and Settings/wendel.lopes/Desktop/arquivos/";
		try {
			// Abrindo o socket
			Socket MyClient = new Socket(IP, port);
			
			// Cria stream de entrada e saída
			DataInputStream input = new DataInputStream(MyClient.getInputStream());
			DataOutputStream output = new DataOutputStream(MyClient.getOutputStream());
			// Recebe mensagem
		 	System.out.println(input.readUTF());

			String arquivo = JOptionPane.showInputDialog("Entre com o nome do aqruivo");
			// Envia mensagem
			output.writeUTF(arquivo);
			
			ObjectInputStream in = new ObjectInputStream(MyClient.getInputStream());
			String fileName = in.readUTF();
			
			if(fileName != null){
				long size = in.readLong();
				System.out.println("Processando arquivo: " + fileName + " - "+ size + " bytes.");

				// Verifica se tem a pasta de destino criada, caso nao tenha ele cria
				File file = new File(caminhoDestino);
				if(file.exists() == false){
					file.mkdir();
				}
				
				FileOutputStream fos = new FileOutputStream(caminhoDestino + fileName);
				byte[] buf = new byte[4096];
				while (true) {
					int len = in.read(buf);
					if (len == -1)
						break;
	
					fos.write(buf, 0, len);
				}			
				
				fos.flush();
				fos.close();
			}
			System.out.println(input.readUTF());
			MyClient.close();

		} catch (Exception e) {
			System.err.println("CLIENTE ERRO: " + e.toString());
		}

	}

}
