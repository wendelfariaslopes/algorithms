package programs;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import programs.io.Property;

import java.sql.Connection;


public class MySqlConnOverSSH {
	
	private static final String host = "ny2-lia-001.prod.tradingscreen.com"; // SSH Hostname: SSH server hostname
	private static final int SSH_PORT=22;
	private static final String user = Property.read("user"); //SSH Username: Name of the SSH user to connect with
	private static final String password = Property.read("password");//SSH Password: SSH user password to connect to the SSH Tunnel
	
	//private static final String MYSQL_HOST = "db-tsom-1-b"; //ny2-ldb-005.uatprod.tradingscreen.com   IP 10.31.92.132 | ny2-ldb-003b.prod.tradingscreen.com IP 10.31.72.83 PROD 
	private static final String MYSQL_HOST = "10.31.72.83";  // MySQL Hostname: MySQL server host relative to the SSH server.
	private static final String MYSQL_PORT = "3001"; // MySQL Server port: TCP/IP port the MySQL server.
	private static final String MySQL_UserName = Property.read("mysql.username"); 
	private static final String MySQL_PASSWORD = Property.read("mysql.password");
	private static final String MYSQL_DATABASE = "tsom"; // MySQL database name

	/**
	 * Java Program to connect to remote database through SSH using port forwarding
	 */
	public static void main(String[] args) throws SQLException {

		int lport=0;
	    String rhost=MYSQL_HOST;
	    int rport=3306;

	    String dbuserName = "wlopes";
        String dbpassword = "greymemory33";
       // String url = "jdbc:mysql://"+MYSQL_HOST+":"+lport+"/"+MYSQL_DATABASE;
        String url = "jdbc:mariadb://"+MYSQL_HOST+":"+rport+"/"+MYSQL_DATABASE;
        //String driverName="com.mysql.jdbc.Driver";
        Connection conn = null;
        Session session= null;
	    try{
	    	//Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
	    	java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	JSch jsch = new JSch();
	    	session=jsch.getSession(user, host, 22);
	    	session.setPassword(password);
	    	session.setConfig(config);
	    	session.connect();
	    	System.out.println("Connected");
	    	
	    	int assinged_port = session.setPortForwardingL(session.getPort(), session.getHost(), rport);
	        System.out.println(session.getHost()+":"+assinged_port+" -> "+rhost+":"+rport);
	    	System.out.println("Port Forwarded");
	    	
	    	//mysql database connectivity
            //Class.forName(driverName).newInstance();
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, dbuserName, dbpassword);
            
            System.out.println ("Database connection established");
            System.out.println("DONE");
            
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	if(conn != null && !conn.isClosed()){
	    		System.out.println("Closing Database Connection");
	    		conn.close();
	    	}
	    	if(session !=null && session.isConnected()){
	    		System.out.println("Closing SSH Connection");
	    		session.disconnect();
	    	}
	    }
	}
	
	static int lport;
    static String rhost;
    static int rport;
	
	
	public static Session connectSession() {
        Session session = null;
      
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, SSH_PORT);
            lport = 4321;
            rhost = "localhost";
            rport = 3306;
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();

            
            // Create the tunnel through port forwarding.  
            // This is basically instructing jsch session to send 
            // data received from local_port in the local machine to 
            // remote_port of the remote_host
            // assigned_port is the port assigned by jsch for use,
            // it may not always be the same as
            // local_port.
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (Exception e) {
            System.err.print(e);
        }
        return session;
    }
	
	public static void testConnection() {
        Session ses = null;
        try {
            ses = connectSession();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("An example for accessing remote db through ssh!");
        Connection con = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + rhost + ":" + lport + "/";

 
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + MYSQL_DATABASE, MySQL_UserName, MySQL_PASSWORD);
            try {
                String sql = "SELECT name FROM mytable";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rset = ps.executeQuery();
                while (rset.next()) {
                    System.out.println("Id : " + rset.getString("name"));
                }
            } catch (SQLException s) {
                System.out.println("SQL statement is not executed!");
            }
            con.close();
            ses.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
