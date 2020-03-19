package ssh;

import java.sql.*;
import java.util.Hashtable;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHConnection 
{  
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	//external database connection
	static final String DB_URL = "jdbc:mysql://localhost:3399/Firma";
	static final String USER = "user";
	static final String PASS = "dbPW";
	
	//ssh connection
	static final String ssh_user = "ssh-user";
	static final String ssh_server = "webseite.de";
	static final String ssh_pass = "sshPW";
	static final int ssh_port = 22;
	
	static final String rhost = "mysql4.webseite.de";
	static final int rport = 3306;
	static final int lport = 3399;
   
   public static void main(String[] args) 
   {
	   Session session = null;
	   Connection conn = null;
	   Statement stmt = null;
	   
	   String[] resultArrayAbt_Nr;
	   String[] resultArrayAbt_Name;
	   
	   int i = 0;
	   int anz = 0;
	   
	   // SSH Tunnel
	   try 
	   {
		   System.out.println("Connecting to ssh-login...");
		   
		   final JSch jsch = new JSch();
		   
		   session = jsch.getSession(ssh_user, ssh_server, ssh_port);
		   System.out.println("Got session...");
		   
		   final Hashtable<String, String> config = new Hashtable<String, String>();
	       config.put("StrictHostKeyChecking", "no");
	       session.setConfig(config);
	       System.out.println("Session setted ...");
	       
	       System.out.println("Session set ssh-password...");
	       session.setPassword(ssh_pass);

	       session.connect();
	       System.out.println("Session connected...");

	       int assigned_port = session.setPortForwardingL(lport, rhost, rport);
	       System.out.println("Port forwarding was successful...");
	   } 
	   
	   catch (Exception e) 
	   {
	     e.printStackTrace();
	   }
	   
	   // Connection to SQL-Database
	   try
	   {
		   //JDBC driver registration
		   Class.forName(JDBC_DRIVER);

		   //Connection opening
		   System.out.println("Connecting to a selected database...");
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   System.out.println("Connected database successfully...");
		   
		   //STEP 4: Execute a query
		   System.out.println("Creating statement...");
		   stmt = conn.createStatement();
		   System.out.println("Created statement successfully...");
		   String sql;
		   
		   sql = "SELECT Count(*) AS anz FROM abteilung";
		   stmt = conn.createStatement();
		   ResultSet anzResult = stmt.executeQuery(sql);
		   
		   anzResult = stmt.executeQuery(sql);
		   
		   
		   if (anzResult.next())
		   {
		   	anz = anzResult.getInt("anz");
		   }

		   System.out.println ( "");
		   System.out.println ( "Anzahl: " + anz);
		   
		   resultArrayAbt_Name = new String[anz];
		   resultArrayAbt_Nr = new String[anz];
		   
		   
		   
		   sql = "SELECT abt_nr, abt_name FROM abteilung";
		   ResultSet rs = stmt.executeQuery(sql);
		   
		   while(rs.next())
		   {
		         //Retrieve by column name
		         String abt_nr  = rs.getString("abt_nr");
		         String abt_name = rs.getString("abt_name");
		 
		         resultArrayAbt_Nr[i] = abt_nr.toString();
		         resultArrayAbt_Name[i] = abt_name.toString();
		         i++;
		    }
		   
		   //Display values
		   for (int j=0; j< resultArrayAbt_Nr.length; j++)
		   {
		   		System.out.println("Abt_nr.....: " + resultArrayAbt_Nr[j]);
		   		System.out.println("Abt_name...: " + resultArrayAbt_Name[j]);
		   		System.out.println("");
		   }
	   }
	   
	   catch(SQLException se)
	   {
		   //Handle errors for JDBC
		   se.printStackTrace();
	   }
	   
	   catch(Exception e)
	   {
		   //Handle errors for Class.forName
		   e.printStackTrace();
	   }
	   
	   finally
	   {
		   //finally block used to close resources
		   try
		   {
			   if (conn != null)
			   {
				   conn.close();
			   }
		   }
		   
		   catch (SQLException se)
		   {
			   se.printStackTrace();
		   }
		   
	   	}
	    System.out.println("");
	   	System.out.println("Goodbye!");   	
   }
}