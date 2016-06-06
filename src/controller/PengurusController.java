package controller;

import helper.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Pengurus;

public class PengurusController {
	public static void doLogin(String nim, String pin) {
//		ArrayList<Pengurus> p = Pengurus.find("nim", "=", "1341010008");
//		for (Pengurus ps : p) {
		Pengurus pengurus = Pengurus.findOne("nim", "=", nim);
		if (pengurus != null) {
			System.out.println(pengurus.getNama());
		}
//		}
	}
}
