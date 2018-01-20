package ftp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexao {

	public static Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			
			Class.forName(ConstanteBD.UUS[0]);
			
			conn = (Connection) DriverManager.getConnection(
					ConstanteBD.UUS[1],
					ConstanteBD.UUS[2],
					ConstanteBD.UUS[3]);
			
			System.out.println("Conexao realizada com sucesso.");

		} catch (ClassNotFoundException ex) {
			System.err.print(ex.getMessage());
		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}

		return conn;

	}

	public static void closeConnection(Connection conn, PreparedStatement pst,
			ResultSet rs) {

		try {

			if (pst != null)
				pst.clearParameters();
			pst.close();

		} catch (Exception e) {

		}
		try {

			if (rs != null)
				rs.close();

		} catch (Exception e) {

		}
		try {

			if (conn != null)
				conn.close();

		} catch (Exception e) {

		}

	}
	
}
