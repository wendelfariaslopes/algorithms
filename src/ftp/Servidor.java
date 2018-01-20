package ftp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 5298;
		try {
			// Abrindo o socket
			ServerSocket ss = new ServerSocket(port);
			
			// Caminho que vai procurar o arquivo passado pelo cliente
			String caminho = "C:/Documents and Settings/wendel.lopes/Meus documentos/";
			//File f = new File("C:/Documents and Settings/wendel.lopes/Meus documentos/telaStatus.jar");
			while (true) {
				System.out.println("Esperando por arquivos.");
				Socket socket = ss.accept();
				
				DataInputStream input = new DataInputStream(socket.getInputStream());
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());
				// Envia mensagem
				output.writeUTF("Bem-vindo, você está conectado.");				
				
				String arquivo = input.readUTF();
				System.out.println("Arquivo :" + arquivo);
				File file = new File(caminho + arquivo);
				
				if(file.exists()){
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					System.out.println("Transferindo o arquivo: " + file.getName());
					out.writeUTF(file.getName());
					out.writeLong(file.length());
					FileInputStream filein = new FileInputStream(file);
					byte[] buf = new byte[4096];

					while (true) {
						int len = filein.read(buf);
						if (len == -1)
							break;
						out.write(buf, 0, len);
					}
					out.close();
					output.writeUTF("Arquivo enviado:");
					
				}else{
					output.writeUTF("Não existe o arquivo!");
				}
				ss.close();
			}
		} catch (Exception e) {
			System.err.println("SERVIDOR ERRO: " + e.toString());
		}

	}

}
