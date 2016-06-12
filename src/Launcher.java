import helper.AppHelper;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.LoginForm;
import view.TransactionForm;

public class Launcher {
		
	public static void main(String [] x){
		LoginForm.getInstance().setVisible(true);

	    LoginForm.getInstance().addWindowListener(new WindowAdapter() {

	        @Override
	        public void windowClosing(WindowEvent arg0) {
	        	LoginForm.getInstance().dispose();
	        }

	        @Override
	        public void windowClosed(WindowEvent e) {
	        	// TODO Auto-generated method stub
                if (AppHelper.getSesion() != null) {
    	        	LoginForm.getInstance().reset();
                	TransactionForm.getInstance().setVisible(true);
                }
	        }
	    });

	    TransactionForm.getInstance().addWindowListener(new WindowAdapter() {

	        @Override
	        public void windowClosing(WindowEvent arg0) {
	        	TransactionForm.getInstance().dispose();
	        }

	        @Override
	        public void windowClosed(WindowEvent e) {
	        	// TODO Auto-generated method stub
	        	AppHelper.setSesion(null);
            	LoginForm.getInstance().setVisible(true);
	        }
	    });
	}
}