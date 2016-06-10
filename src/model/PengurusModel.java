package model;

import helper.ConnectionHelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Pengurus;

public final class PengurusModel {

	public static boolean verifyPassword(String nim, String password) {
		Connection conn = ConnectionHelper.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		boolean loginSuccess = false;
		
		try {
		    stmt = conn.createStatement();
		    String query = "SELECT COUNT(*) AS rowcount FROM Login WHERE `nim`='" + nim + "' AND `password`=MD5('" + password + "')";
		    rs = stmt.executeQuery(query);
		    rs.next();

		    // Now do something with the ResultSet ....
		    if (rs.getInt("rowcount") > 0) {
		    	loginSuccess = true;
		    }
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
		
		return loginSuccess;
	}
	
	public static Pengurus findOne(String key, String corespond,String filter) {
		ArrayList<Pengurus> list = find(key, corespond, filter);
		if (list.size() == 0) {
			return null;
		}
		
		return list.get(0);
	}

	public static ArrayList<Pengurus> find() {
		return find("", "like");
	}
	
	public static ArrayList<Pengurus> find(String key, String corespond,String... filter) {
		Connection conn = ConnectionHelper.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Pengurus> arrayPengurus = new ArrayList<Pengurus>();

		
		try {
		    stmt = conn.createStatement();
		    String query = "SELECT * FROM Pengurus";
		    if (!key.isEmpty() && filter.length > 0) {
			    query += " WHERE";
		    	for (String data : filter) {
				    query += " `" + key + "` " + corespond + " '" + data + "'";
		    	}
		    }
		    rs = stmt.executeQuery(query);

		    // Now do something with the ResultSet ....
		    while (rs.next()) {
		    	String nim = rs.getString("nim");
		    	String nama = rs.getString("nama");
		    	String jk = rs.getString("nim");
		    	Pengurus.JenisKelamin jenis_kelamin = Pengurus.JenisKelamin.WANITA;
		    	if (jk.equals("L")) {
			    	jenis_kelamin = Pengurus.JenisKelamin.PRIA;
		    	}
		    	Date created_at = new Date(rs.getLong("nim"));
		    	
		    	Pengurus p = new Pengurus(nim, nama, jenis_kelamin, created_at);
		    	arrayPengurus.add(p);
		    }
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
		return arrayPengurus;
	}

}
