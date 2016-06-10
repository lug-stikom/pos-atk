package bean;


import java.sql.Date;

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
}
