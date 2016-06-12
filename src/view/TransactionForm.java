package view;

import helper.AppHelper;
import helper.UIHelper;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bean.Pengurus;
import controller.PengurusController;

public class TransactionForm extends Frame {
	////////////////////////////////////////////////////////
	/**/private static final long serialVersionUID = 1L;/**/
	////////////////////////////////////////////////////////

	private static TransactionForm mInstance;
	private JTable lsSummary, lsProduct;
	private Label lblSummary, lblTotal;
	private Button btnLogin;

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

		JScrollPane summaryScrollPane = new JScrollPane(lsSummary);
		summaryScrollPane.setSize(500, 160);
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

	    lsProduct = new JTable(new DefaultTableModel(productColumnNames, 0));

		JScrollPane productScrollPane = new JScrollPane(lsProduct);
		productScrollPane.setSize(370, lblTotal.getHeight() + summaryScrollPane.getHeight() + 10);
		UIHelper.rightOf(lblTotal, productScrollPane, 10);
	    this.add(productScrollPane);

		// init window
		this.setTitle("Transaction");
		this.setLayout(null);
		this.setSize(900, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private void initFunction() {
		
	}
	
	private void addToSummary(String type, String product, String qty, String total) {
	    DefaultTableModel model = (DefaultTableModel)lsSummary.getModel();
	    model.addRow(new Object[] {type, product, qty, total});
	}
}
