package com.cs304.clerkUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cs304.tables.Item;
import com.cs304.tables.Purchase;
import com.cs304.tables.PurchaseItem;

public class PurchaseUI implements ActionListener {
	Connection connect;
	private JFrame tframe;
	JFrame testframe, testframe2;
	JButton Breturn, cancel;
	JTextField textfields, textfields2, textfields3, textfields4;
	JLabel rid, cardnum, expireDate, qty;
	int rowCount;
	int count = 0;

	// float prices;

	public PurchaseUI(Connection c, JFrame frame) {
		tframe = frame;
		connect = c;
		rowCount = -1;
		testframe = new JFrame("Purchase");
		JPanel p = new JPanel();

		testframe.setVisible(true);
		testframe.setSize(400, 400);

		Dimension dim = testframe.getToolkit().getScreenSize();
		Rectangle r = testframe.getBounds();
		testframe.setLocation((dim.width - r.width) / 2,
				(dim.height - r.height) / 2);

		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints gcon = new GridBagConstraints();

		Breturn = new JButton("Confirm");
		cancel = new JButton("Cancel");

		rid = new JLabel("UPC: ");
		cardnum = new JLabel("card#: ");
		expireDate = new JLabel("Card Expire Date (yy-mm-dd): ");
		qty = new JLabel("Quantity: ");
		textfields = new JTextField();
		textfields2 = new JTextField();
		textfields3 = new JTextField();
		textfields4 = new JTextField();

		gcon.gridx = 0;
		gcon.gridy = 1;
		gcon.insets = new Insets(0, 0, 10, 10);
		grid.setConstraints(rid, gcon);
		p.add(rid);

		gcon.gridx = 1;
		gcon.gridy = 1;
		textfields.setColumns(10);
		grid.setConstraints(textfields, gcon);
		p.add(textfields);

		gcon.gridx = 0;
		gcon.gridy = 2;

		grid.setConstraints(cardnum, gcon);
		p.add(cardnum);

		gcon.gridx = 1;
		gcon.gridy = 2;
		textfields2.setColumns(10);
		grid.setConstraints(textfields2, gcon);
		p.add(textfields2);

		gcon.gridx = 0;
		gcon.gridy = 3;

		grid.setConstraints(expireDate, gcon);
		p.add(expireDate);

		gcon.gridx = 1;
		gcon.gridy = 3;
		textfields3.setColumns(10);
		grid.setConstraints(textfields3, gcon);
		p.add(textfields3);

		gcon.gridx = 0;
		gcon.gridy = 4;

		grid.setConstraints(qty, gcon);
		p.add(qty);

		gcon.gridx = 1;
		gcon.gridy = 4;
		textfields4.setColumns(10);
		grid.setConstraints(textfields4, gcon);
		p.add(textfields4);

		gcon.gridx = 0;
		gcon.gridy = 5;
		grid.setConstraints(Breturn, gcon);
		p.add(Breturn);

		gcon.gridx = 1;
		gcon.gridy = 5;
		grid.setConstraints(cancel, gcon);
		p.add(cancel);

		Breturn.addActionListener(this);
		cancel.addActionListener(this);
		p.setLayout(grid);

		testframe.add(p);

	}

	@Override
	public void actionPerformed(ActionEvent act) {
		String s[] = new String[150];
		s[0] = "Receipt: \n";
		Float prices = new Float(0.0);
		if (act.getSource() == cancel) {
			tframe.setVisible(true);
			testframe.dispose();
		}

		if (act.getSource() == Breturn) {

		}

		if (!isInt(textfields.getText())) {

			JOptionPane.showMessageDialog(null,
					"Cannot be null or is not an int");
		} else {
			int upc = Integer.parseInt(textfields.getText());
			GregorianCalendar calendar = new GregorianCalendar();
			String formatString = "yyyy-MM-dd";

			SimpleDateFormat tDate = new SimpleDateFormat(formatString);

			// java.sql.Date expiryDate = new
			// java.sql.Date(tDate.parse(textfields3.getText()).getTime());

			java.sql.Date sday = new java.sql.Date(calendar.getTime().getTime());

			try {
				connect.setAutoCommit(false);
				Statement state = connect.createStatement();
				ResultSet rs = state
						.executeQuery("SELECT COUNT(*) AS TOTAL FROM Item WHERE UPC ="
								+ "'" + textfields.getText() + "'");
				// Have to check if item exists somehow
				while (rs.next()) {
					count = count + rs.getInt("TOTAL");

				}
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "Item does not exist");
				} else {
					ResultSet rs1 = state
							.executeQuery("SELECT * FROM ITEM WHERE UPC ="
									+ "'" + textfields.getText() + "'");
					while (rs1.next()) {
						Item i = new Item();
						Purchase p = new Purchase();
						prices = rs1.getFloat("price");
						PurchaseItem pi = new PurchaseItem();
						i.deleteItem(connect, rs1.getInt("upc"));
						int newItemCount = (rs1.getInt("stock") - Integer
								.parseInt(textfields4.getText()));
						i.insertItem(connect, rs1.getInt("upc"),
								rs1.getString("title"), rs1.getString("type"),
								rs1.getString("catagory"),
								rs1.getString("company"),
								rs1.getString("year"), rs1.getString("price"),
								newItemCount);

						if (textfields2.getText() == "") {
							p.insertPurchase(connect, 1, null, sday, null, null);
						} else {
							java.sql.Date expiryDate = new java.sql.Date(tDate
									.parse(textfields3.getText()).getTime());
							p.insertPurchase(connect, 1, textfields2.getText(),
									expiryDate, null, null);
						}

						pi.insertPurchaseItem(connect, rs1.getInt("upc"),
								Integer.parseInt(textfields4.getText()));

					}
					System.out.println("before currvalquery \n");
					ResultSet rs2 = state
							.executeQuery("SELECT * FROM PURCHASE WHERE "
									+ "receiptID = PurchaseCounter.currval");
					System.out.println("after currvalquery \n");
					while (rs2.next()) {
						s[1] = "ReceiptID:" + rs2.getInt("receiptID")
								+ "\nDate:" + rs2.getDate("bdate")
								+ "\nItemUPC:" + rs2.getInt("upc")
								+ "\nQuantity:" + textfields4.getText()
								+ "\nPrice:"
								+ Integer.parseInt(textfields4.getText())
								* prices + "\nCardNum:"
								+ rs2.getString("cardnum").substring(11, 16);
					}

				}

				/*
				 * if (rs.getInt(upc) ) {
				 * 
				 * }
				 */

				connect.commit();
				state.close();
			} catch (SQLException error) {
				System.out.println(error.getMessage());
			} catch (ParseException e) {

			}

		}

	}

	public boolean isInt(String s) {

		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

}
