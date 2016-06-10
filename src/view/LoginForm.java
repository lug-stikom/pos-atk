package view;

import helper.AppHelper;
import helper.UIHelper;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import bean.Pengurus;
import controller.PengurusController;

public final class LoginForm extends Frame {
	private static LoginForm mInstance;
	private Label lblNim, lblPassword;
	private TextField txtNim, txtPassword;
	private Button btnLogin;
	
	public LoginForm() {
		// TODO Auto-generated constructor stub
		mInstance = this;
		initUI();
		initFunction();
	}

	public static synchronized LoginForm getInstance() {
		return mInstance;
	}
	
	private void initUI() {
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 12);

		//init label NIM
		lblNim = new Label("NIM");
		lblNim.setSize(100, 16);
		lblNim.setLocation(10, 10);
		lblNim.setFont(f);
		this.add(lblNim);
		
		//init label Password
		lblPassword = new Label("Password");
		lblPassword.setSize(100, 16);
		lblPassword.setFont(f);
		UIHelper.bottomOf(lblNim, lblPassword, 10);
		this.add(lblPassword);

		//init text NIM
		txtNim = new TextField();
		txtNim.setSize(120, 21);
		txtNim.setFont(f2);
		UIHelper.rightOf(lblNim, txtNim, 10);
		txtNim.setLocation(txtNim.getX(), txtNim.getY() - 5);
		this.add(txtNim);
		
		//init text Password
		txtPassword = new TextField();
		txtPassword.setEchoChar('*');
		txtPassword.setSize(120, 21);
		txtPassword.setFont(f2);
		UIHelper.bottomOf(txtNim, txtPassword, 5);
		this.add(txtPassword);

		//init text Password
		btnLogin = new Button("Login");
		btnLogin.setFont(f2);
		btnLogin.setSize(txtPassword.getX() + txtPassword.getWidth() - 9, 25);
		UIHelper.bottomOf(lblPassword, btnLogin, 10);
		this.add(btnLogin);

		// init window
		this.setTitle("Authentication");
		this.setLayout(null);
		this.setSize(txtPassword.getX() + txtPassword.getWidth() + 10, 
						btnLogin.getY() + btnLogin.getHeight() + 10);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private void initFunction() {
		this.addWindowListener(new WindowAdapter() {			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				LoginForm.this.dispose();
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pengurus pengurus =  PengurusController.doLogin(txtNim.getText(), txtPassword.getText());
				if (pengurus != null) {
					AppHelper.setSesion(pengurus);
				}
				else {
					JOptionPane.showMessageDialog(LoginForm.this, "Sorry, you have no credential here", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
