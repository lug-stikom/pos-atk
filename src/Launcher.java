import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.LoginForm;

public class Launcher {
	public static String Log = "";
	public static boolean adm = false;
	
	private static Object lock = new Object();
	
	public static void main(String [] x){
		LoginForm auth = new LoginForm();
		auth.setVisible(true);

		Thread t = new Thread() {
	        public void run() {
	            synchronized(lock) {
	                while (auth.isVisible()) {
	                    try {
	                        lock.wait();
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	                System.out.println("Working now");
	            }
	        }
	    };
	    t.start();

	    auth.addWindowListener(new WindowAdapter() {

	        @Override
	        public void windowClosing(WindowEvent arg0) {
                auth.dispose();
	        }

	        @Override
	        public void windowClosed(WindowEvent e) {
	        	// TODO Auto-generated method stub
	            synchronized (lock) {
	                auth.dispose();
	                lock.notify();
	            }
	        }
	    });

	}
}