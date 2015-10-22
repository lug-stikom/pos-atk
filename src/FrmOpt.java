import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;

public class FrmOpt {
	static JFrame app=new JFrame("Pengaturan User dan Barang");

	static DefaultTableModel mdUsr=new DefaultTableModel();
	static JTable tblUsr=new JTable();
	static JScrollPane spUsr=new JScrollPane(tblUsr);
	static JButton btnAddUsr= new JButton("Tambah User");
	static JButton btnEditUsr= new JButton("Edit User");
	static JButton btnRemUsr= new JButton("Hapus User");

	static DefaultTableModel mdBrg=new DefaultTableModel();
	static JTable tblBrg=new JTable();
	static JScrollPane spBrg=new JScrollPane(tblBrg);
	static JButton btnAddBrg= new JButton("Tambah Barang");
	static JButton btnEditBrg= new JButton("Edit Barang");
	static JButton btnRemBrg= new JButton("Hapus Barang");

	static JButton btnOK= new JButton("Okey");
	
	public static void mkWindow(){
		app.setResizable(false);
		app.setLayout(null);
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		app.add(btnAddUsr);
		app.add(btnEditUsr);
		app.add(btnRemUsr);
		app.add(spUsr);

		app.add(btnAddBrg);
		app.add(btnEditBrg);
		app.add(btnRemBrg);
		app.add(spBrg);

		app.add(btnOK);
		
		spUsr.setBounds(20,0,600,200);

		btnAddUsr.setBounds(spUsr.getBounds().x, spUsr.getBounds().y+spUsr.getBounds().height, 200, 20);
		btnEditUsr.setBounds(btnAddUsr.getBounds().x+btnAddUsr.getBounds().width, btnAddUsr.getBounds().y, 200, 20);
		btnRemUsr.setBounds(btnEditUsr.getBounds().x+btnEditUsr.getBounds().width, btnAddUsr.getBounds().y, 200, 20);

		spBrg.setBounds(20,btnRemUsr.getBounds().y+btnRemUsr.getBounds().height+10,600,200);

		btnAddBrg.setBounds(spBrg.getBounds().x, spBrg.getBounds().y+spBrg.getBounds().height, 200, 20);
		btnEditBrg.setBounds(btnAddBrg.getBounds().x+btnAddBrg.getBounds().width, btnAddBrg.getBounds().y, 200, 20);
		btnRemBrg.setBounds(btnEditBrg.getBounds().x+btnEditBrg.getBounds().width, btnAddBrg.getBounds().y, 200, 20);

		btnOK.setBounds(20,btnRemBrg.getBounds().y+btnRemBrg.getBounds().height+10,100,20);
		
		app.setSize(spUsr.getBounds().width+40, btnOK.getBounds().y+2*(btnOK.getBounds().height)+30);

		if(mdUsr.getColumnCount()==0){
			mdUsr.addColumn("No");
			mdUsr.addColumn("NIM");
			mdUsr.addColumn("NAMA");
			mdUsr.addColumn("Admin");
			mdUsr.addColumn("Login");
		}
		tblUsr.setModel(mdUsr);
		tblUsr.getColumnModel().getColumn(0).setPreferredWidth(7);
		tblUsr.getColumnModel().getColumn(1).setPreferredWidth(90);
		tblUsr.getColumnModel().getColumn(2).setPreferredWidth(300);
		tblUsr.getColumnModel().getColumn(3).setPreferredWidth(50);
		tblUsr.getColumnModel().getColumn(4).setPreferredWidth(50);
		tblUsr.setVisible(true);
		
		if(mdBrg.getColumnCount()==0){
			mdBrg.addColumn("No");
			mdBrg.addColumn("NAMA");
			mdBrg.addColumn("HARGA BELI");
			mdBrg.addColumn("HARGA JUAL");
		}
		tblBrg.setModel(mdBrg);
		tblBrg.getColumnModel().getColumn(0).setPreferredWidth(7);
		tblBrg.getColumnModel().getColumn(1).setPreferredWidth(290);
		tblBrg.getColumnModel().getColumn(2).setPreferredWidth(90);
		tblBrg.getColumnModel().getColumn(3).setPreferredWidth(90);
		tblBrg.setVisible(true);

		app.setVisible(true);
		Database.listUser(tblUsr, mdUsr);
		Database.listBarang(tblBrg, mdBrg);
	}
	
	public static void klik() {
		//Komponen Untuk USER//
		btnAddUsr.addActionListener(new ActionListener() {
			
			@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JTextField nimF = new JTextField(8);
				nimF.setDocument(new JTextFieldLimit(11));
				JTextField namaF = new JTextField(15);
				JPasswordField passF = new JPasswordField(15);
				JPasswordField passF2 = new JPasswordField(15);
				String[] aa={"Ya","Tidak"};
				JComboBox admF=new JComboBox();
				admF.setModel(new javax.swing.DefaultComboBoxModel(aa));
				
				nimF.addKeyListener(new KeyListener() {
					
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
			    myPanel.setMaximumSize(new Dimension(1,1));
			    myPanel.add(new JLabel("NIM"));
			    myPanel.add(nimF);
			    myPanel.add(new JLabel("Nama"));
			    myPanel.add(namaF);
			    myPanel.add(new JLabel("Password"));
			    myPanel.add(passF);
			    myPanel.add(new JLabel("Ulangi Password"));
			    myPanel.add(passF2);
			    myPanel.add(new JLabel("Admin"));
			    myPanel.add(admF);

			    int result = JOptionPane.showConfirmDialog(null, myPanel,
			    		"Tambah User Baru", JOptionPane.OK_CANCEL_OPTION);
			    if (result == JOptionPane.OK_OPTION) {
			    	if(!nimF.getText().equals("") && !passF.getText().equals("") && (passF.getText().equals(passF2.getText())) && !namaF.getText().equals("")){
			    		String nimN=nimF.getText();
			    		String passN=passF.getText();
			    		String namaN=namaF.getText();
			    		
				        if(admF.getSelectedIndex()==0){
				        	Database.tbhUser(nimN, passN, namaN, 1);
				        }else{
				        	Database.tbhUser(nimN, passN, namaN, 0);
				        }
			    	}
			    	Database.listUser(tblUsr, mdUsr);
			    }
			}
		});

		btnEditUsr.addActionListener(new ActionListener() {
			
			@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblUsr.getSelectedRow()!=-1){
					JTextField nimF = new JTextField(8);
					nimF.setText(tblUsr.getValueAt(tblUsr.getSelectedRow(), 1).toString());
					nimF.disable();
	
					JTextField namaF = new JTextField(15);
					namaF.setText(tblUsr.getValueAt(tblUsr.getSelectedRow(), 2).toString());
	
					JPasswordField passF0 = new JPasswordField(15);
					JPasswordField passF = new JPasswordField(15);
					JPasswordField passF2 = new JPasswordField(15);
	
					String[] aa={"Ya","Tidak"};
					JComboBox admF=new JComboBox();
					admF.setModel(new javax.swing.DefaultComboBoxModel(aa));
					if(tblUsr.getValueAt(tblUsr.getSelectedRow(), 3).toString().equalsIgnoreCase("ya")){
						admF.setSelectedIndex(0);
					}else{
						admF.setSelectedIndex(1);
					}
	
				    JPanel myPanel = new JPanel();
				    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
				    myPanel.setMaximumSize(new Dimension(1,1));
				    myPanel.add(new JLabel("NIM"));
				    myPanel.add(nimF);
				    myPanel.add(new JLabel("Nama"));
				    myPanel.add(namaF);
				    myPanel.add(new JLabel("Password"));
				    myPanel.add(passF);
				    myPanel.add(new JLabel("Ulangi Password"));
				    myPanel.add(passF2);
				    myPanel.add(new JLabel("Admin"));
				    myPanel.add(admF);
	
				    int result = JOptionPane.showConfirmDialog(null, myPanel,
				    		"Tambah User Baru", JOptionPane.OK_CANCEL_OPTION);
				    if (result == JOptionPane.OK_OPTION) {
				    	if(!nimF.getText().equals("") && !namaF.getText().equals("")){
				    		String passN="";
				    		if(Database.cekLog(nimF.getText(), passF0.getText()) && (passF.getText().equals(passF2.getText()))){
					    		passN=passF.getText();
				    		}
	
				    		String nimN=nimF.getText();
				    		String namaN=namaF.getText();
				    		
					        if(admF.getSelectedIndex()==0){
					        	Database.edtUser(nimN, passN, namaN, 1);
					        }else{
					        	Database.edtUser(nimN, passN, namaN, 0);
					        }
				    	}
				    	Database.listUser(tblUsr, mdUsr);
				    }
				}
			}
		});

		btnRemUsr.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblUsr.getSelectedRow()!=-1){
					String nimm=tblUsr.getValueAt(tblUsr.getSelectedRow(), 1).toString();
					int result = JOptionPane.showConfirmDialog(null, new JLabel("Yakin Ingin menghapus User dengan NIM \""+
				    		nimm+"\"?"),
				    		"Konfirmasi Hapus User", JOptionPane.YES_NO_OPTION);
				    if (result == JOptionPane.YES_OPTION) {
				    	Database.hpsUser(nimm);
				    	Database.listUser(tblUsr, mdUsr);
				    }
				}
			}
		});
				
		tblUsr.addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if((tblUsr.getSelectedColumn()==0 ||
						tblUsr.getSelectedColumn()==1 ||
						tblUsr.getSelectedColumn()==2||
						tblUsr.getSelectedColumn()==3) && tblUsr.isEditing()){
					tblUsr.getCellEditor().cancelCellEditing();
				}
			}
			
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});

		//Komponen Untuk BARANG//
		btnAddBrg.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JTextField namaF = new JTextField(8);
				JTextField hJualF = new JTextField(15);
				JTextField hBeliF = new JTextField(15);
				
				hBeliF.addKeyListener(new KeyListener() {
					
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

				hJualF.addKeyListener(new KeyListener() {
					
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
			    myPanel.setMaximumSize(new Dimension(1,1));
			    myPanel.add(new JLabel("Nama Barang"));
			    myPanel.add(namaF);
			    myPanel.add(new JLabel("Harga Beli"));
			    myPanel.add(hBeliF);
			    myPanel.add(new JLabel("Harga Jual"));
			    myPanel.add(hJualF);

			    int result = JOptionPane.showConfirmDialog(null, myPanel,
			    		"Tambah Barang Baru", JOptionPane.OK_CANCEL_OPTION);
			    if (result == JOptionPane.OK_OPTION) {
			    	if(!namaF.getText().equals("") && !hJualF.getText().equals("") && !hBeliF.getText().equals("")){
			    		String namaN=namaF.getText();
			    		int hBeliN=Integer.parseInt(hBeliF.getText());
			    		int hJualN=Integer.parseInt(hJualF.getText());
			    		
			        	Database.tbhBarang(namaN, hBeliN, hJualN);
			    	}
			    	Database.listBarang(tblBrg, mdBrg);
			    }
			}
		});

		btnEditBrg.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblBrg.getSelectedRow()!=-1){
					JTextField namaF = new JTextField(8);
					String nama=tblBrg.getValueAt(tblBrg.getSelectedRow(), 1).toString();
					namaF.setText(nama);
					
					JTextField hBeliF = new JTextField(15);
					hBeliF.setText(tblBrg.getValueAt(tblBrg.getSelectedRow(), 2).toString());

					JTextField hJualF = new JTextField(15);
					hJualF.setText(tblBrg.getValueAt(tblBrg.getSelectedRow(), 3).toString());
					
					hBeliF.addKeyListener(new KeyListener() {
						
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

					hJualF.addKeyListener(new KeyListener() {
						
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
				    myPanel.setMaximumSize(new Dimension(1,1));
				    myPanel.add(new JLabel("Nama"));
				    myPanel.add(namaF);
				    myPanel.add(new JLabel("Harga Beli"));
				    myPanel.add(hBeliF);
				    myPanel.add(new JLabel("Harga Jual"));
				    myPanel.add(hJualF);
	
				    int result = JOptionPane.showConfirmDialog(null, myPanel,
				    		"Tambah User Baru", JOptionPane.OK_CANCEL_OPTION);
				    if (result == JOptionPane.OK_OPTION) {
				    	if(!namaF.getText().equals("") || !hBeliF.getText().equals("") || !hJualF.getText().equals("")){
				    		String namaN=namaF.getText();
				    		int hBeliN=-1;
				    		int hJualN=-1;
				    		if(!hBeliF.getText().equals("")){
				    			hBeliN=Integer.parseInt(hBeliF.getText());
				    		}
				    		
				    		if(!hJualF.getText().equals("")){
				    			hJualN=Integer.parseInt(hJualF.getText());
				    		}
				    		
				        	Database.edtBarang(nama, namaN, hBeliN, hJualN);
				    	}
				    	Database.listBarang(tblBrg, mdBrg);
				    }
				}
			}
		});

		btnRemBrg.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tblBrg.getSelectedRow()!=-1){
					String namaa=tblBrg.getValueAt(tblBrg.getSelectedRow(), 1).toString();
					int result = JOptionPane.showConfirmDialog(null, new JLabel("Yakin Ingin menghapus Barang dengan Nama \""+
				    		namaa+"\"?"),
				    		"Konfirmasi Hapus Barang", JOptionPane.YES_NO_OPTION);
				    if (result == JOptionPane.YES_OPTION) {
				    	Database.hpsBarang(namaa);
				    	Database.listBarang(tblBrg, mdBrg);
				    }
				}
			}
		});
				
		tblBrg.addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if((tblBrg.getSelectedColumn()==0 ||
						tblBrg.getSelectedColumn()==1 ||
						tblBrg.getSelectedColumn()==2 ||
						tblBrg.getSelectedColumn()==3) && tblBrg.isEditing()){
					tblBrg.getCellEditor().cancelCellEditing();
				}
			}
			
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});

		//TOMBOL OKE//
		btnOK.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Launcher.Log.equals("admin")){
					FrmLog.mkWindow();
					app.dispose();
				}else{
					FrmUT.stopModal();
					app.dispose();
				}
			}
		});
		
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(Launcher.Log.equals("admin")){
					FrmLog.mkWindow();
					app.dispose();
				}else{
					FrmUT.stopModal();
					app.dispose();
				}
			}
		});
	}

	@SuppressWarnings("deprecation")
	public static void stopModal(){
		app.enable();
	}
}

class JTextFieldLimit extends PlainDocument {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int limit;
	  JTextFieldLimit(int limit) {
	    super();
	    this.limit = limit;
	  }

	  JTextFieldLimit(int limit, boolean upper) {
	    super();
	    this.limit = limit;
	  }

	  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	    if (str == null)
	      return;

	    if ((getLength() + str.length()) <= limit) {
	      super.insertString(offset, str, attr);
	    }
	  }
}