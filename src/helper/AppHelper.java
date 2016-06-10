package helper;

import bean.Pengurus;

public final class AppHelper {
	private static Pengurus sesion;

	public static Pengurus getSesion() {
		return sesion;
	}

	public static void setSesion(Pengurus sesion) {
		AppHelper.sesion = sesion;
	}
	
}
