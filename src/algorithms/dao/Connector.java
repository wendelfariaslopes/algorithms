package algorithms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {
	
	public static Connection getConnectionCrawler() throws SQLException {

		String driver = "org.postgresql.Driver";
        String user   = "postgres";
        String senha  = "654321";
        String url    = "jdbc:postgresql://localhost:5433/db_finance";
		
		Connection conn = null;
		
		  try
	        {
	            Class.forName(driver);
	            conn = (Connection) DriverManager.getConnection(url, user, senha);
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
			
			e.printStackTrace();
		
		}
		try {

			if (rs != null)
				rs.close();
			

		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
		try {

			if (conn != null)
				conn.close();
		

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
