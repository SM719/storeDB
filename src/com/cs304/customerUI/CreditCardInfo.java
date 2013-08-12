package com.cs304.customerUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cs304.tables.Purchase;
import com.cs304.tables.PurchaseItem;

public class CreditCardInfo {

	Connection connection;

	private JFrame Frame, fr;

	private JTextField eDate;
	private JTextField num;
	private JTextField CID;
	private JLabel amount;

	private JButton purchase;
	private JButton cancel;
	int i;
	String price;
	String[] u = new String[100];

	// constructor
	public CreditCardInfo(Connection con, JFrame f, String id, float p,
			String[] upc) {

		fr = f;
		connection = con;
		try {
			i = Integer.parseInt(id);

		} catch (NumberFormatException e) {
		}
		price = Float.toString(p);

		for (int a = 0; a < upc.length; a++) {
			u[a] = upc[a];
		}

		Frame = new JFrame("Payment");
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		CID = new JTextField("Please Enter your CID:");
		amount = new JLabel("Your total amount is: $" + price);
		num = new JTextField("Enter your credit card number");
		eDate = new JTextField("Enter the card's expiry date");
		purchase = new JButton("Purchase");
		cancel = new JButton("Cancel");

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 5;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(amount, g);
		panel.add(amount);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(CID, g);
		panel.add(CID);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(num, g);
		panel.add(num);

		g.gridx = 0;
		g.gridy = 4;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(eDate, g);
		panel.add(eDate);

		g.gridx = 0;
		g.gridy = 5;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(purchase, g);
		panel.add(purchase);

		g.gridx = 0;
		g.gridy = 6;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		panel.setLayout(gb);

		theHandler Handler = new theHandler(i);
		eDate.addActionListener(Handler);
		num.addActionListener(Handler);

		purchase.addActionListener(Handler);
		cancel.addActionListener(Handler);

		Frame.add(panel);
	}

	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener {

		// String d;
		// int num;
		int ccid;

		public theHandler(int i) {
			// TODO Auto-generated constructor stub
			ccid = i;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == cancel) {
				Frame.dispose();
			} else if (event.getSource() == purchase) {
				JOptionPane.showMessageDialog(null,
						"Your order will be delivered in 7 days");
				Frame.dispose();

				Date eD = new Date(2014, 8, 5);
				Date d = new Date(2013, 8, 15);
				Date del = new Date(2013, 8, 15);
				int c = Integer.parseInt(CID.getText());

				new Purchase().insertPurchase(connection, c, num.getText(), eD,
						d, del);

				int fUpc;
				for (int i = 0; i < u.length; i++) {
					if (u[i] != null) {
						fUpc = Integer.valueOf(u[i]);
						System.out.println(fUpc + "\n");

						new PurchaseItem().insertPurchaseItem(connection, fUpc,
								1);
						// fUpc = 1;
					}
				}

				// new PurchaseItem().insertPurchaseItem(connection, 6, 1);

			}

		}

	}
}