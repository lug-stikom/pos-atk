import java.io.*;
import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Database {
	static Connection c=null;
	static Statement stmt = null;
	static String sql=null;
	static File tmp =new File(".tmp");
	
	public static void koneksi(){
		try{
			Class.forName("org.sqlite.JDBC");
			File f=new File("pos.enc");
			if(!f.exists()){
				tmp.mkdir();
				c=DriverManager.getConnection("jdbc:sqlite:.tmp/pos.db");
			}else{
				tmp.mkdir();
				FileDecryptor.Dekrip("pos.enc", "tes",".tmp/pos.db");
				c=DriverManager.getConnection("jdbc:sqlite:.tmp/pos.db");
			}
			c.setAutoCommit(false);
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	
	public static void tutup(){
		try{
			c.close();
			File del=new File(".tmp/pos.db");
			if(del.exists()){
				FileEncryptor.Enkrip(".tmp/pos.db", "tes","pos.enc");
				del.delete();
				tmp.delete();
			}
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}

	public static void confTable(){
		try{
			
			stmt = c.createStatement();
		    sql = "CREATE TABLE IF NOT EXISTS M_USER " +
	                   "(NIM            CHAR(11)  PRIMARY KEY  NOT NULL," +
	                   " PASS           TEXT                   NOT NULL, " + 
	                   " NAME           TEXT                   NOT NULL, " + 
	                   " STATUS         INT(1)                 NOT NULL)"; 
		    stmt.executeUpdate(sql);
		    
		    sql = "CREATE TABLE IF NOT EXISTS M_BARANG " +
	                   "(ID             INTEGER  PRIMARY KEY   AUTOINCREMENT   NOT NULL," +
	                   " NAMA           TEXT                                   NOT NULL, " + 
	                   " HARGA_BELI     REAL	                               NOT NULL, " +
	                   " HARGA_JUAL     REAL	                               NOT NULL)";
		    stmt.executeUpdate(sql);

		    sql = "CREATE TABLE IF NOT EXISTS USER " +
	                   "(ID            INTEGER   PRIMARY KEY   AUTOINCREMENT   NOT NULL," +
	                   " NIM           CHAR(11)                                NOT NULL, " + 
	                   " WAKTU         DATETIME                                NOT NULL)";
		    stmt.executeUpdate(sql);

		    sql = "CREATE TABLE IF NOT EXISTS TRX " +
	                   "(ID            INTEGER   PRIMARY KEY   AUTOINCREMENT   NOT NULL," +
	                   " NIM           CHAR(11)                                NOT NULL, " +
	                   " WAKTU         DATETIME                                NOT NULL)";
		    stmt.executeUpdate(sql);

		    sql = "CREATE TABLE IF NOT EXISTS LIST_TRX " +
	                   "(ID_T           INTEGER    NOT NULL," +
	                   " ID_B           INTEGER    NOT NULL, " + 
	                   " QTY            INTEGER    NOT NULL)";
		    stmt.executeUpdate(sql);

		    stmt.close();
		    c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	
	public static void listUser(JTable tbl,DefaultTableModel dtm){
		for(int i=dtm.getRowCount()-1;i>-1;i--){
			dtm.removeRow(i);
		}

		try{
			stmt = c.createStatement();
			Statement stmt2 = c.createStatement();
			int n=1;
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM M_USER WHERE NIM <> '"+Launcher.Log+"';" );
		      while ( rs.next() ) {
			      ResultSet rs2 = stmt2.executeQuery( "SELECT COUNT(*) AS TOTAL FROM USER WHERE NIM='"+rs.getString("NIM")+"';" );
			      String stat;
			      if(rs.getInt("STATUS")==0){
			    	  stat="Tidak";
			      }else{
			    	  stat="YA";
			      }
			      
			      if(rs2.next()){
			    	  dtm.addRow(new Object[]{n,
			    			  rs.getString("NIM"),
			    			  rs.getString("NAME"),
			    			  stat,
			    			  rs2.getString("TOTAL")});
			      }else{
			    	  dtm.addRow(new Object[]{n,
			    			  rs.getString("NIM"),
			    			  rs.getString("NAME"),
			    			  stat,
			    			  "0"});
			      }
			      rs2.close();
		    	  n++;
		      }
		      
	    	  tbl.setModel(dtm);
	    	  stmt2.close();
		      rs.close();
	    	  stmt.close();
	    	  c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	
	public static void tbhUser(String nim, String pass, String nama, int stat){
		try{
			stmt = c.createStatement();
			boolean ada;
		      
			ResultSet rs = stmt.executeQuery( "SELECT * FROM M_USER WHERE NIM='"+nim+"';" );
			if(rs.next()) {
				ada=true;
			}else{
				ada=false;
			}

			if(ada==false){
				sql = "INSERT INTO M_USER (NIM,PASS,NAME,STATUS) " +
			          "VALUES ('"+nim+"', '"+pass+"', '"+nama+"', "+stat+");";
			    stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "User Berhasil ditambah");
			}else{
				JOptionPane.showMessageDialog(null, "NIM sudah terdaftar. Hubungi admin");
			}
		      
		    rs.close();
		    stmt.close();
		    c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}

	public static void edtUser(String nim, String pass, String nama, int stat){
		try{
			stmt = c.createStatement();
		    
			if(!pass.equals("")){
				sql = "UPDATE M_USER set PASS='"+pass+"' WHERE NIM='"+nim+"';";
			    stmt.executeUpdate(sql);
			}
			
			if(!nama.equals("")){
				sql = "UPDATE M_USER set NAME='"+nama+"' WHERE NIM='"+nim+"';";
			    stmt.executeUpdate(sql);
			}
			
			if(stat!=-1){
				sql = "UPDATE M_USER set STATUS='"+stat+"' WHERE NIM='"+nim+"';";
			    stmt.executeUpdate(sql);
			}

		    JOptionPane.showMessageDialog(null, "User Berhasil diubah");

		    stmt.close();
		    c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}

	public static void hpsUser(String nim){
		try{
			stmt = c.createStatement();
		    
			stmt.executeUpdate("DELETE FROM USER WHERE NIM='"+nim+"';" );
			stmt.executeUpdate("DELETE FROM M_USER WHERE NIM='"+nim+"';" );

			JOptionPane.showMessageDialog(null, "User Berhasil dihapus");
		    
		    stmt.close();
		    c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}

	public static void listBarang(JTable tbl,DefaultTableModel dtm){
		for(int i=dtm.getRowCount()-1;i>-1;i--){
			dtm.removeRow(i);
		}

		try{
			stmt = c.createStatement();
			int n=1;
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM M_BARANG;" );
		      while ( rs.next() ) {
		    	  dtm.addRow(new Object[]{n,
		    			  rs.getString("NAMA"),
		    			  rs.getString("HARGA_BELI").substring(0, rs.getString("HARGA_BELI").length()-2),
		    			  rs.getString("HARGA_JUAL").substring(0, rs.getString("HARGA_JUAL").length()-2)});
		    	  n++;
		      }
		      
		      rs.close();
	    	  stmt.close();
	    	  c.commit();
	    	  tbl.setModel(dtm);
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}

	public static String hBarang(String nama){
		String result = null;
		try{
			stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM M_BARANG WHERE NAMA='"+nama+"';" );
	    	  while( rs.next() ){
	    		  result=rs.getString("HARGA_JUAL");
	    	  }
		      rs.close();
	    	  stmt.close();
	    	  c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return result;
	}
	
	public static String [] listBarang(){
		String [] result = null;
		try{
			stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM M_BARANG;" );
		      int size=0;
	    	  while( rs.next() ){
	    		  size++;
	    	  }
		      rs.close();

		      rs = stmt.executeQuery( "SELECT * FROM M_BARANG;" );
    		  result=new String[size+1];
	    	  result[0]="-Silahkan Pilih-";

	    	  while ( rs.next() ) {
		    	  result[rs.getRow()]=rs.getString("NAMA");
		      }
		      
		      rs.close();
	    	  stmt.close();
	    	  c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return result;
	}

	public static void tbhBarang(String nama, int hBeli, int hJual){
		try{
			stmt = c.createStatement();
			boolean ada;
		      
			ResultSet rs = stmt.executeQuery( "SELECT * FROM M_BARANG WHERE NAMA='"+nama+"';" );
			if(rs.next()) {
				ada=true;
			}else{
				ada=false;
			}

			if(ada==false){
				sql = "INSERT INTO M_BARANG (NAMA,HARGA_BELI,HARGA_JUAL) " +
			          "VALUES ('"+nama+"', "+hBeli+", "+hJual+");";
			    stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Barang Berhasil ditambah");
			}else{
				JOptionPane.showMessageDialog(null, "Nama sudah terdaftar. Hubungi admin");
			}
		      
		    rs.close();
		    stmt.close();
		    c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}

	public static void edtBarang(String nama, String namaN, int hBeli, int hJual){
		try{
			stmt = c.createStatement();
			int id=-1;
			ResultSet rs = stmt.executeQuery( "SELECT ID FROM M_BARANG WHERE NAMA='"+nama+"';" );
			if(rs.next()){
				id=rs.getInt("ID");
			}
		    
			if(!namaN.equals("")){
				sql = "UPDATE M_BARANG set NAMA='"+namaN+"' WHERE ID="+id+";";
			    stmt.executeUpdate(sql);
			}
			
			if(hBeli!=-1){
				sql = "UPDATE M_BARANG set HARGA_BELI="+hBeli+" WHERE ID="+id+";";
			    stmt.executeUpdate(sql);
			}
			
			if(hJual!=-1){
				sql = "UPDATE M_BARANG set HARGA_JUAL="+hJual+" WHERE ID="+id+";";
			    stmt.executeUpdate(sql);
			}

		    JOptionPane.showMessageDialog(null, "Barang Berhasil diubah");

		    stmt.close();
		    c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}

	public static void hpsBarang(String nama){
		try{
			stmt = c.createStatement();
		    
			stmt.executeUpdate("DELETE FROM M_BARANG WHERE NAMA='"+nama+"';" );

			JOptionPane.showMessageDialog(null, "Barang Berhasil dihapus");
		    
		    stmt.close();
		    c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	
	public static void trx(JTable tbl, String wkt, String nim){
		try{
			stmt = c.createStatement();
			
			sql = "INSERT INTO TRX (NIM,WAKTU) " +
			          "VALUES ('"+nim+"', '"+wkt+"');";
			stmt.executeUpdate(sql);
			String nama;
			String id_t = null;
			String id_b;
			int qty;

			ResultSet rs = stmt.executeQuery( "SELECT * FROM TRX WHERE WAKTU='"+wkt+"' AND NIM='"+nim+"';" );
			if(rs.next()) {
				id_t=rs.getString("ID");
			}
			rs.close();

			for(int i=0;i<=tbl.getRowCount()-1;i++){
				nama=tbl.getValueAt(i, 1).toString();
				qty=(int)Double.parseDouble(tbl.getValueAt(i, 3).toString());
				rs = stmt.executeQuery( "SELECT * FROM M_BARANG WHERE NAMA='"+nama+"';" );
				if(rs.next()) {
					id_b=rs.getString("ID");
					sql = "INSERT INTO LIST_TRX (ID_T,ID_B,QTY) " +
					          "VALUES ("+id_t+", "+id_b+", "+qty+");";
				    stmt.executeUpdate(sql);
				}
			}
			JOptionPane.showMessageDialog(null, "Data transaksi telah disimpan dalam database");
		      
		    rs.close();
		    stmt.close();
		    c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	
	public static void recLog(String nim, String waktu){
		try{
			stmt = c.createStatement();
		      
		    sql = "INSERT INTO USER (NIM,WAKTU) " +
		          "VALUES ('"+nim+"', '"+waktu+"');"; 
		    stmt.executeUpdate(sql);
		      
		    stmt.close();
		    c.commit();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
	
	public static boolean cekLog(String usr, String pass){
		boolean sukses=false;
		try{
			stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM M_USER WHERE NIM='"+usr+"' AND PASS='"+pass+"';" );
		      if(rs.next()) {
		    	  sukses=true;
		      }

		      rs.close();
		      stmt.close();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return sukses;
	}
	
	public static boolean cekAdm(String usr){
		boolean sukses=false;
		try{
			stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM M_USER WHERE NIM='"+usr+"';" );
	    	  int ad=rs.getInt("status");
	    	  if(ad==1){
	    		  sukses=true;
	    	  }

		      rs.close();
		      stmt.close();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return sukses;
	}
}
