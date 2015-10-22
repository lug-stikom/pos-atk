public class Launcher {
	public static String Log="";
	public static boolean adm=false;
	public static void main(String [] x){
		Database.koneksi();
		Database.confTable();
		FrmLog.mkWindow();
		FrmLog.klik();
		FrmOpt.klik();
		FrmUT.klik();
	}
}