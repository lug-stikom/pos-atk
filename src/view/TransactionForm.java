package view;

import helper.AppHelper;
import helper.UIHelper;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TransactionForm extends Frame {
	////////////////////////////////////////////////////////
	/**/private static final long serialVersionUID = 1L;/**/
	////////////////////////////////////////////////////////

	private static TransactionForm mInstance;
	private JTable lsSummary, lsProduct;
	private Label lblSummary, lblTotal;
	private Button btnDiscard, btnCheckout, btnAdd, btnRemove;

	public TransactionForm() {
		// TODO Auto-generated constructor stub
		mInstance = this;
		initUI();
		initFunction();
	}

	public static synchronized TransactionForm getInstance() {
		if (mInstance == null) {
			mInstance = new TransactionForm();
		}
		
		return mInstance;
	}

	private void initUI() {
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 12);

		//init label Summary
		lblSummary = new Label("Order Summary");
		lblSummary.setSize(160, 16);
		lblSummary.setLocation(10, 10);
		lblSummary.setFont(f);
		this.add(lblSummary);

		//init Table Summary
	    Vector<String> summaryColumnNames = new Vector<String>();
	    summaryColumnNames.addElement("Type");
	    summaryColumnNames.addElement("Product");
	    summaryColumnNames.addElement("Qty");
	    summaryColumnNames.addElement("Total");

	    lsSummary = new JTable(new DefaultTableModel(summaryColumnNames, 0));
	    lsSummary.getTableHeader().setReorderingAllowed(false);
	    lsSummary.getColumn(summaryColumnNames.elementAt(0)).setPreferredWidth(5); //type
	    lsSummary.getColumn(summaryColumnNames.elementAt(1)).setPreferredWidth(200); //product
	    lsSummary.getColumn(summaryColumnNames.elementAt(2)).setPreferredWidth(1); //qty
	    lsSummary.getColumn(summaryColumnNames.elementAt(3)).setPreferredWidth(90); //total

	    lsSummary.getColumn(summaryColumnNames.elementAt(0)).setResizable(false); //type
	    lsSummary.getColumn(summaryColumnNames.elementAt(1)).setResizable(false); //product
	    lsSummary.getColumn(summaryColumnNames.elementAt(2)).setResizable(false); //qty
	    lsSummary.getColumn(summaryColumnNames.elementAt(3)).setResizable(false); //total

		JScrollPane summaryScrollPane = new JScrollPane(lsSummary);
		summaryScrollPane.setSize(500, 260);
		UIHelper.bottomOf(lblSummary, summaryScrollPane, 10);
	    this.add(summaryScrollPane);

		//init label Total
		lblTotal = new Label("Rp 170.000");
		lblTotal.setSize(150, 16);
		lblTotal.setFont(f);
		lblTotal.setAlignment(2);
		lblTotal.setLocation(summaryScrollPane.getX() + summaryScrollPane.getWidth() - lblTotal.getWidth(), lblSummary.getY());
		this.add(lblTotal);

		//init Table Summary
	    Vector<String> productColumnNames = new Vector<String>();
	    productColumnNames.addElement("Type");
	    productColumnNames.addElement("Product");
	    productColumnNames.addElement("Price");

	    lsProduct = new JTable(new DefaultTableModel(productColumnNames, 0){
	    	////////////////////////////////////////////////////////
	    	/**/private static final long serialVersionUID = 1L;/**/
	    	////////////////////////////////////////////////////////

			@Override
	    	public boolean isCellEditable(int row, int column) {
	    		return false;
	    	}
	    });
	    lsProduct.getTableHeader().setReorderingAllowed(false);
	    lsProduct.getColumn(productColumnNames.elementAt(0)).setPreferredWidth(5); //type
	    lsProduct.getColumn(productColumnNames.elementAt(1)).setPreferredWidth(200); //product
	    lsProduct.getColumn(productColumnNames.elementAt(2)).setPreferredWidth(1); //price

	    lsProduct.getColumn(productColumnNames.elementAt(0)).setResizable(false); //type
	    lsProduct.getColumn(productColumnNames.elementAt(1)).setResizable(false); //product
	    lsProduct.getColumn(productColumnNames.elementAt(2)).setResizable(false); //price

		JScrollPane productScrollPane = new JScrollPane(lsProduct);
		productScrollPane.setSize(370, lblTotal.getHeight() + summaryScrollPane.getHeight() + 10);
		UIHelper.rightOf(lblTotal, productScrollPane, 10);
	    this.add(productScrollPane);

	    //init btnDiscard
	    btnCheckout = new Button("Checkout");
	    btnCheckout.setSize(80, 24);
	    UIHelper.bottomOf(summaryScrollPane, btnCheckout, 10);
	    this.add(btnCheckout);

	    //init btnDiscard
	    btnDiscard = new Button("Discard");
	    btnDiscard.setSize(60, 24);
	    UIHelper.rightOf(btnCheckout, btnDiscard, 10);
	    this.add(btnDiscard);

	    //init btnDiscard
	    btnRemove = new Button("Remove");
	    btnRemove.setSize(60, 24);
	    UIHelper.bottomOf(summaryScrollPane, btnRemove, 10);
	    btnRemove.setLocation(summaryScrollPane.getX() + summaryScrollPane.getWidth() - btnRemove.getWidth(), btnRemove.getY());
	    this.add(btnRemove);

	    //init btnDiscard
	    btnAdd = new Button("Add");
	    btnAdd.setSize(60, 24);
	    UIHelper.bottomOf(productScrollPane, btnAdd, 10);
	    this.add(btnAdd);

		// init window
		this.setTitle("Transaction");

		if (AppHelper.getSesion() != null) {
			this.setTitle(this.getTitle() + " | Member: " + AppHelper.getSesion().getNama() + "(" + AppHelper.getSesion().getNim() + ")");
		}
		
		this.setLayout(null);
		this.setSize(900, 350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		listProduct("print", "Print Gambar < 1/2 (Hitam-Putih)", "20000");
	}
	
	private void initFunction() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rowNum = lsProduct.getSelectedRow();
				if (rowNum > -1) {
					
				}
			}
		});
	}
	
	private void addToSummary(String type, String product, String qty, String total) {
	    DefaultTableModel model = (DefaultTableModel)lsSummary.getModel();
	    model.addRow(new Object[] {type, product, qty, total});
	}

	private void listProduct(String type, String product, String price) {
	    DefaultTableModel model = (DefaultTableModel)lsProduct.getModel();
	    model.addRow(new Object[] {type, product, price});
	}
}
