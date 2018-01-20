package ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPClient;

public class MyFTP {

	public static void main(String[] args) {
		
		String nomeArquivo = null;
		FTPClient ftp = new FTPClient();

		try {
			ftp.connect("10.48.2.252");

			// verifica se conectou com sucesso!
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				
				ftp.login("lopes", "123456");
				
			} else {
				// erro ao se conectar
				ftp.disconnect();
				System.out.println("Conexão recusada");
				System.exit(1);
			}
			
			ftp.changeWorkingDirectory("lopes/");
			//ftp.changeWorkingDirectory("/"); // diretorio raiz
			  // Lista os arquivos do diretorio corrente
			 FTPFile[] files = ftp.listFiles();   
			 for(FTPFile file : files) {
			   System.out.println(file.getName()+" Tamanho: "+file.getSize()+ " Data: "+ file.getTimestamp().DAY_OF_MONTH);
			 }
			 System.out.println("Encontrados " + files.length + " arquivos.");
			 System.out.println("Fim da lista dos arquivos");
			
			 //Vamos fingir que é uma imagem JPEG na pasta presente que deseja copiar para a área de trabalho (em uma máquina windows)
			ftp.setFileType(FTPClient.STREAM_TRANSFER_MODE); // não se esqueça de mudar para o modo binário! ou você vai ter uma imagem embaralhada!
			FileOutputStream localDescarga = new FileOutputStream("C:\\Users\\fnac\\Downloads\\documents-export-2013-05-07.zip");
			ftp.retrieveFile("lopes/documents-export-2013-05-07.zip", localDescarga);
			System.out.println("Fim da upload do arquivo");
			
			// para cada arquivo informado...
			for (int i = 0; i < args.length; i++) {
				// abre um stream com o arquivo a ser enviado
				InputStream is = new FileInputStream(args[i]);
				// pega apenas o nome do arquivo
				int idx = args[i].lastIndexOf(File.separator);
				if (idx < 0)
					idx = 0;
				else
					idx++;
				nomeArquivo = args[i].substring(idx, args[i].length());

				// ajusta o tipo do arquivo a ser enviado
				if (args[i].endsWith(".txt")) {
					ftp.setFileType(FTPClient.ASCII_FILE_TYPE);
				} else if (args[i].endsWith(".jpg")) {
					ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				} else {
					ftp.setFileType(FTPClient.ASCII_FILE_TYPE);
				}
				System.out.println("Enviando arquivo " + nomeArquivo + "...");

				// faz o envio do arquivo
				ftp.storeFile(nomeArquivo, is);
				System.out.println("Arquivo " + nomeArquivo
						+ " enviado com sucesso!");
			}

			ftp.disconnect();
			
			System.out.println("Fim da Execucao!");
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro: " + e);
			System.exit(1);
		}
	}
}