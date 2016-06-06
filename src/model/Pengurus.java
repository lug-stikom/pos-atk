package model;

import helper.ConnectionHelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class Pengurus {
	private String nim, nama;
	public enum JenisKelamin { PRIA, WANITA };
	private JenisKelamin jenis_kelamin;
	private Date created_at;
	
	public Pengurus(String nim, String nama, JenisKelamin jenis_kelamin, Date created_at){
		this.nim = nim;
		this.nama = nama;
		this.jenis_kelamin = jenis_kelamin;
		this.created_at = created_at;
	}

	public String getNim() {
		return nim;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public JenisKelamin getJenis_kelamin() {
		return jenis_kelamin;
	}

	public Date getCreated_at() {
		return created_at;
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
		    	JenisKelamin jenis_kelamin = JenisKelamin.WANITA;
		    	if (jk.equals("L")) {
			    	jenis_kelamin = JenisKelamin.PRIA;
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
