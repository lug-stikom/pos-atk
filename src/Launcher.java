import view.LoginForm;

public class Launcher {
	public static String Log = "";
	public static boolean adm = false;
	public static void main(String [] x){
		LoginForm auth = new LoginForm();
		auth.setVisible(true);
	}
}