package helper;

import java.sql.Connection;
import java.sql.DriverManager;

import org.active.record.java.db.connections.DatabaseConnectionHandler;

public final class ConnectionHelper extends DatabaseConnectionHandler{
	private static final String HOST = "repo.stikom.edu";
	private static final String DB = "lug_printing";
	private static final String USER = "printing";
	private static final String PASS = "JkEu4Hq4G5QdUpzF";
	private static Connection conn = null;
	
	private static void connect() {
		try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DB + "?" +
            	                                   "user=" + USER + "&password=" + PASS);
        } catch (Exception ex) {
            // handle the error
        	System.out.println(ex.getMessage());
        }
	}
	
	public static Connection getConnection() {
		if (conn == null) {
			connect();
		}

		return conn;
	}
}
