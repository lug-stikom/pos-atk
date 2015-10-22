import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.*;

public class FrmUT {
	static JFrame app=new JFrame("Point of Sale ATK Linux User Group");
	static JLabel lblNama=new JLabel("Nama Barang");
	static JLabel lblQty=new JLabel("Qty");
	static JLabel lblHarga=new JLabel("Harga");
	static String [] listCombo;
	@SuppressWarnings("rawtypes")
	static JComboBox cboNama= new JComboBox();
	static JTextField txtQty=new JTextField();
	static JTextField txtHarga=new JTextField();
	static JButton btnAdd= new JButton("Tambah ke Daftar");
	static JButton btnRem= new JButton("Hapus dari Daftar");
	static DefaultTableModel md=new DefaultTableModel();
	static JTable tblMK=new JTable();
	static JScrollPane sp=new JScrollPane(tblMK);
	static JButton btnOK= new JButton("Bayar");
	static JButton btnSet= new JButton("Admin");
	static JButton btnClose= new JButton("Log Out");
	static JLabel lblIP=new JLabel();
	static boolean mod=false;
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public static void mkWindow(){
		listCombo=Database.listBarang();
		cboNama.setModel(new javax.swing.DefaultComboBoxModel(listCombo));
		app.setResizable(false);
		app.setLayout(null);
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		app.add(lblNama);
		app.add(lblQty);
		app.add(lblHarga);
		app.add(cboNama);
		app.add(txtQty);
		app.add(txtHarga);
		app.add(btnAdd);
		app.add(btnRem);
		app.add(sp);
		app.add(btnSet);
		app.add(btnOK);
		app.add(btnClose);
		app.add(lblIP);

		btnSet.setVisible(Launcher.adm);

		lblNama.setBounds(20, 0, 230, 20);
		lblHarga.setBounds(lblNama.getBounds().x+lblNama.getBounds().width+10, 0, 150, 20);
		lblQty.setBounds(lblHarga.getBounds().x+lblHarga.getBounds().width+10, 0, 30, 20);
		
		cboNama.setBounds(lblNama.getBounds().x, 25, 230, 20);
		txtHarga.setBounds(lblHarga.getBounds().x, 25, 150, 20);
		txtHarga.disable();
		txtQty.setBounds(lblQty.getBounds().x, 25, 30, 20);
		
		btnAdd.setBounds(cboNama.getBounds().x, cboNama.getBounds().y+cboNama.getBounds().height+5, (txtQty.getBounds().x+txtQty.getBounds().width-25)/2, 20);
		btnRem.setBounds(btnAdd.getBounds().x+btnAdd.getBounds().width+5, cboNama.getBounds().y+cboNama.getBounds().height+5, (txtQty.getBounds().x+txtQty.getBounds().width-25)/2, 20);
		
		sp.setBounds(20,btnAdd.getBounds().y+btnAdd.getBounds().height+5,txtQty.getBounds().x+txtQty.getBounds().width-20,200);
		lblIP.setBounds(sp.getBounds().x+sp.getBounds().width-210,sp.getBounds().y+sp.getBounds().height+10,210,20);
		lblIP.setText("Total: RP                            -,00");
		btnSet.setBounds(sp.getBounds().x,lblIP.getBounds().y+lblIP.getBounds().height+10,100,20);
		btnOK.setBounds(sp.getBounds().x+sp.getBounds().width-210,lblIP.getBounds().y+lblIP.getBounds().height+10,100,20);
		btnClose.setBounds(btnOK.getBounds().x+btnOK.getBounds().width+10,btnOK.getBounds().y,100,20);
		
		app.setSize(sp.getBounds().width+40, btnOK.getBounds().y+3*(btnOK.getBounds().height)+10);
		cboNama.setSelectedIndex(0);
		txtHarga.setText(null);
		txtQty.setText(null);
		

		for(int i=md.getRowCount()-1;i>-1;i--){
			md.removeRow(i);
		}

		if(md.getColumnCount()==0){
			md.addColumn("No");
			md.addColumn("Nama Barang");
			md.addColumn("Harga");
			md.addColumn("Qty");
			md.addColumn("Total");
		}
		tblMK.setModel(md);
		tblMK.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblMK.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblMK.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblMK.getColumnModel().getColumn(3).setPreferredWidth(50);
		tblMK.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblMK.setVisible(true);
		
		app.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public static void stopModal(){
		app.enable();
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mod=false;
	}
	
	private static void updateTabel(JTable tbl){
		for(int i=0;i<tbl.getRowCount();i++){
			double akh=(Double.parseDouble(tbl.getValueAt(i, 2).toString()))*(Double.parseDouble(tbl.getValueAt(i, 3).toString()));
			tbl.setValueAt(akh, i, 4);
		}
	}
	
	public static String total(JTable tbl){
		String r;
		int n=0;
		for(int i=0;i<=tbl.getRowCount()-1;i++){
			n+=(int)Double.parseDouble(tbl.getValueAt(i, 4).toString());
		}
		int pjg=Integer.toString(n).length();
		
		r="Total: RP";
		for(int i=0; i<28-pjg;i++){
			r+=" ";
		}
		if(tbl.getRowCount()!=0){
			r+=Integer.toString(n)+",00";
		}else{
			r+="-,00";
		}
		return r;
	}
	
	public static int total1(JTable tbl){
		int n=0;
		for(int i=0;i<=tbl.getRowCount()-1;i++){
			n+=(int)Double.parseDouble(tbl.getValueAt(i, 4).toString());
		}
		
		return n;
	}

	public static void klik() {
		cboNama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!cboNama.getSelectedItem().toString().equals("-Silahkan Pilih-")){
					txtHarga.setText(Database.hBarang(cboNama.getSelectedItem().toString()));
				}else{
					txtHarga.setText("");
				}
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!cboNama.getSelectedItem().toString().equals("-Silahkan Pilih-") &&
					!txtHarga.getText().equals("") && 
					!txtQty.getText().equals("")){
						int n=tblMK.getRowCount();
						Double total=Double.parseDouble(txtHarga.getText())*Double.parseDouble(txtQty.getText());
						int brs=-1;
						for(int i=0;i<=tblMK.getRowCount()-1;i++){
							if(tblMK.getValueAt(i, 1).toString().equals(cboNama.getSelectedItem().toString())){
								brs=i;
								break;
							}
						}
						
						if(brs==-1){
							md.addRow(new Object[]{n+1,
									cboNama.getSelectedItem().toString(),
									txtHarga.getText(),
									txtQty.getText(),
									total});
							tblMK.setModel(md);
							cboNama.setSelectedIndex(0);
							txtHarga.setText("");
							txtQty.setText("");
							lblIP.setText(total(tblMK));
						}else{
						    JPanel myPanel = new JPanel();
						    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
						    myPanel.add(new JLabel("Data \""+cboNama.getSelectedItem().toString()+"\" telah ada. Ganti dengan data baru?"));
						    myPanel.add(new JLabel("Yes: Ganti"));
						    myPanel.add(new JLabel("No: Gabung"));
						    myPanel.add(new JLabel("Cancel: Batal"));

							int result = JOptionPane.showConfirmDialog(null, myPanel,
						    		"Data Telah ada", JOptionPane.YES_NO_CANCEL_OPTION);
							if(result==JOptionPane.YES_OPTION){
								tblMK.setValueAt(txtQty.getText(), brs, 3);
								total=Double.parseDouble(tblMK.getValueAt(brs, 2).toString())*Double.parseDouble(tblMK.getValueAt(brs, 3).toString());
								tblMK.setValueAt(total, brs, 4);

								tblMK.setModel(md);
								cboNama.setSelectedIndex(0);
								txtHarga.setText("");
								txtQty.setText("");
								lblIP.setText(total(tblMK));
							}else if(result==JOptionPane.NO_OPTION){
								tblMK.setValueAt((Double.parseDouble(tblMK.getValueAt(brs, 3).toString())+Double.parseDouble(txtQty.getText())), brs, 3);
								total=Double.parseDouble(tblMK.getValueAt(brs, 2).toString())*Double.parseDouble(tblMK.getValueAt(brs, 3).toString());
								tblMK.setValueAt(total, brs, 4);

								tblMK.setModel(md);
								cboNama.setSelectedIndex(0);
								txtHarga.setText("");
								txtQty.setText("");
								lblIP.setText(total(tblMK));
							}
						}
				}else{
					if(cboNama.getSelectedItem().toString().equals("-Silahkan Pilih-")){
						cboNama.requestFocus();
					}

					if(txtHarga.getText().equals("")){
						txtHarga.requestFocus();
					}

					if(txtQty.getText().equals("")){
						txtQty.requestFocus();
					}
				}
			}
		});

		btnRem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblMK.getSelectedRow()!=-1){
					md.removeRow(tblMK.getSelectedRow());
					tblMK.setModel(md);
					for(int i=0;i<=tblMK.getRowCount()-1;i++){
						tblMK.setValueAt(i+1, i, 0);
					}
					lblIP.setText(total(tblMK));
				}
			}
		});
				
		tblMK.addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if((tblMK.getSelectedColumn()==0 ||
						tblMK.getSelectedColumn()==1 ||
						tblMK.getSelectedColumn()==2 ||
						tblMK.getSelectedColumn()==4) && tblMK.isEditing()){
					tblMK.getCellEditor().cancelCellEditing();
				}
				updateTabel(tblMK);
			}
			
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				updateTabel(tblMK);
			}
		});

		btnSet.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblMK.getRowCount()==0){
					app.disable();
					app.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					mod=true;
					FrmOpt.mkWindow();
				}else{
					JOptionPane.showMessageDialog(null, "Maaf tidak dapat masuk admin ketika transaksi berlangsung");
				}
			}
		});

		btnOK.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblMK.getRowCount()!=0){
					Calendar date=new GregorianCalendar();
					int tahun=date.get(Calendar.YEAR);
					int bulan=date.get(Calendar.MONTH);
					int tanggal=date.get(Calendar.DATE);
					int jam=date.get(Calendar.HOUR_OF_DAY);
					int menit=date.get(Calendar.MINUTE);
					int detik=date.get(Calendar.SECOND);
					String wkt=tahun+"-"+bulan+"-"+tanggal+" "+jam+"-"+menit+"-"+detik;
	
					JTextField total=new JTextField();
					total.setText(Integer.toString(total1(tblMK)));
					total.disable();
					JTextField bayar=new JTextField();
					
					bayar.addKeyListener(new KeyListener() {
						
						public void keyTyped(KeyEvent e) {
							// TODO Auto-generated method stub
							char c = e.getKeyChar();
							if (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE){  
								if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')){
									e.consume();
					            }
							}
						}
						
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						public void keyPressed(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
					});

				    JPanel myPanel = new JPanel();
				    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
				    myPanel.add(new JLabel("TOTAL:"));
				    myPanel.add(total);
				    myPanel.add(new JLabel("BAYAR:"));
				    myPanel.add(bayar);
	
					int result = JOptionPane.showConfirmDialog(null, myPanel,
				    		"Pembayaran", JOptionPane.OK_CANCEL_OPTION);
					if(result==JOptionPane.OK_OPTION){
						int sisa=Integer.parseInt(bayar.getText())-Integer.parseInt(total.getText());
						if(sisa>0){
							JOptionPane.showMessageDialog(null, "Jumlah Kembalian Rp"+sisa+",00");
							Database.trx(tblMK, wkt, Launcher.Log);
							
							cboNama.setSelectedIndex(0);
							txtHarga.setText("");
							txtQty.setText("");
							for(int i=md.getRowCount()-1;i>-1;i--){
								md.removeRow(i);
							}
							tblMK.setModel(md);
							lblIP.setText(total(tblMK));
						}else if(sisa==0){
							Database.trx(tblMK, wkt, Launcher.Log);
	
							cboNama.setSelectedIndex(0);
							txtHarga.setText("");
							txtQty.setText("");
							for(int i=md.getRowCount()-1;i>-1;i--){
								md.removeRow(i);
							}
							tblMK.setModel(md);
							lblIP.setText(total(tblMK));
						}else{
							JOptionPane.showMessageDialog(null, "Uang kurang");
						}
					}
				}
			}
		});

		btnClose.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mod==false){
					FrmLog.mkWindow();
					app.dispose();
				}
			}
		});
		
		txtQty.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE){  
					if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')){
						e.consume();
		            }
				}
			}
			
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		txtHarga.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE){  
					if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')){
						e.consume();
		            }
				}
			}
			
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		app.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				  if(mod==false){
					FrmLog.mkWindow();
					app.dispose();
				  }
			  }
			});
	}
}
