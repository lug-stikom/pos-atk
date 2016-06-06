package helper;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionHelper {
	private static Connection conn = null;
	
	private static void connect() {
		try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://192.168.1.1/lug_printing?" +
            	                                   "user=printing&password=JkEu4Hq4G5QdUpzF");
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
