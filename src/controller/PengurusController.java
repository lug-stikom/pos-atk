package controller;

import model.PengurusModel;
import bean.Pengurus;

public class PengurusController {
	public static Pengurus doLogin(String nim, String pin) {
		if (PengurusModel.verifyPassword(nim, pin)) {
			Pengurus pengurus = PengurusModel.findOne("nim", "=", nim);
			return pengurus;
		}
		
		return null;
	}
}
