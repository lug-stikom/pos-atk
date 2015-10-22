import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrmLog {
	static JFrame app=new JFrame("Login");
	static JLabel lblNim=new JLabel("NIM");
	static JLabel lblPass=new JLabel("Password");
	static JTextField txtNim=new JTextField();
	static JPasswordField txtPass=new JPasswordField();
	static JButton btnOk= new JButton("Sip");
	static JButton btnClose= new JButton("Keluar");
	static String usr="admin";
	static String pass="Lug123pos";
	static int time=0;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void mkWindow(){
		app.setResizable(false);
		app.getContentPane().setLayout(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		app.getContentPane().add(lblNim);
		app.getContentPane().add(lblPass);
		app.getContentPane().add(txtNim);
		app.getContentPane().add(txtPass);
		app.getContentPane().add(btnOk);
		app.getContentPane().add(btnClose);
				
		lblNim.setBounds(20, 0, 130, 20);
		lblPass.setBounds(20, lblNim.getBounds().y+lblNim.getBounds().height+10, 90, 20);
		
		txtNim.setBounds(lblNim.getBounds().x+lblNim.getBounds().width+10, lblNim.getBounds().y, 130, 20);
		txtPass.setBounds(txtNim.getBounds().x, lblPass.getBounds().y, 130, 20);
		
		btnOk.setBounds(20,lblPass.getBounds().y + lblPass.getBounds().height + 10, 100, 20);
		btnClose.setBounds(btnOk.getBounds().x + btnOk.getBounds().width + 10, btnOk.getBounds().y, 100, 20);
				
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		app.setSize(txtPass.getBounds().x + txtPass.getBounds().width + 20, lblNim.getBounds().y + lblNim.getBounds().height + lblPass.getBounds().y + lblPass.getBounds().height + btnOk.getBounds().y);
		app.setLocation(dim.width/2-app.getSize().width/2, dim.height/2-app.getSize().height/2);
		

		app.setVisible(true);
	}
		
	public static void klik() {			
		btnOk.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Calendar date=new GregorianCalendar();
				int tahun=date.get(Calendar.YEAR);
				int bulan=date.get(Calendar.MONTH);
				int tanggal=date.get(Calendar.DATE);
				int jam=date.get(Calendar.HOUR_OF_DAY);
				int menit=date.get(Calendar.MINUTE);
				int detik=date.get(Calendar.SECOND);
				String wkt=tahun+"-"+bulan+"-"+tanggal+" "+jam+"-"+menit+"-"+detik;

				if(txtNim.getText().equals(usr) && txtPass.getText().equals(pass)){
					Database.recLog(usr, wkt);
					
					Launcher.Log=txtNim.getText();
					Launcher.adm=true;
					FrmOpt.mkWindow();
					app.dispose();
				}else
					if(Database.cekLog(txtNim.getText(), txtPass.getText())){
						Database.recLog(txtNim.getText(), wkt);
						
						Launcher.Log=txtNim.getText();
						Launcher.adm=Database.cekAdm(txtNim.getText());
						FrmUT.mkWindow();
						app.dispose();
					}else{
						JOptionPane.showMessageDialog(null, "NIM atau Password Salah Boss! Hubungi Admin :D");
						txtNim.requestFocus();
						if(time!=2){
							time++;
						}else{
							app.dispose();;
						}
					}
					txtNim.setText("");
					txtPass.setText("");
				}
		});

		btnClose.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				app.dispose();
				Database.tutup();
			}
		});
		
		app.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
					Database.tutup();
			  }
		});
	}
}
