package ftp;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class CargaPostgresFTP {
	
	private static final String CARGA_BD = "COPY paises FROM 'c:\\/Temp\\/paises.csv' DELIMITERS ';';";
	
	public boolean carregarBD() {
		
		Connection conn = null;
		PreparedStatement pst = null;

		boolean status = false;

		try {

			conn = Conexao.getConnection();

			pst = conn.prepareStatement(CARGA_BD);
			
			System.out.println("Execucao de retorno: "+pst.execute());
			System.out.println("Tempo Saida: "+pst.getQueryTimeout());
			
			status = true;

		} catch (Exception e) {
			System.out.println(e);
			status = false;

		} finally {
			Conexao.closeConnection(conn, pst, null);
		}

		return status;
	}
	
	public static void main (String []args){
		
		CargaPostgresFTP carga = new CargaPostgresFTP();
		
		if (carga.carregarBD()) {
			System.out.println("Carga efetuada com Sucesso!");
		} else {
			System.out.println("Erro ao tentar carregar o Banco de Dados!");
		}
		
		
	}

}
