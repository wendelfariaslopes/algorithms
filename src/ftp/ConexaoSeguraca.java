package ftp;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConexaoSeguraca {
	
	
	public static Connection getConnection() throws SQLException {
		
		Connection conn = null;
		
		try {

			Context ctx = new InitialContext();

			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/infobrasDS");

			conn = ds.getConnection();

		} catch (SQLException sqle) {

			
		} catch (Exception e) {
		
			e.printStackTrace();

		}

		return conn;

	}
	
	
	public static Connection getConnection(String sql) throws SQLException {
		
		Connection conn = null;
		
		try {

			Context ctx = new InitialContext();
			
			StringB.mudaString(sql);
			
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/infobrasDS");

			conn = ds.getConnection();

		} catch (SQLException sqle) {

			

		} catch (Exception e) {
			
			
			e.printStackTrace();

		}

		return conn;

	}
	
	public static Connection getConnection2() throws SQLException {
		
		String driver = "org.postgresql.Driver";
        String user   = "postgres";
        String senha  = "123456";
        String url    = "jdbc:postgresql://localhost:5432/db_infobras";
		
		Connection conn = null;
		
		  try
	        {
	            Class.forName(driver);
	       
	            conn = (Connection) DriverManager.getConnection(url, user, senha);

	            System.out.println("Conex�o realizada com sucesso.");

	        }
	        catch (ClassNotFoundException ex)
	        {
	            System.err.print(ex.getMessage());
	        } 
	        catch (SQLException e)
	        {
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
