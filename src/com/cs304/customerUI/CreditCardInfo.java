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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cs304.tables.Purchase;

public class CreditCardInfo {

	Connection connection;

	private JFrame Frame, fr;

	private JTextField eDate;
	private JTextField num;

	private JButton purchase;
	private JButton cancel;
	Integer i;

	// constructor
	public CreditCardInfo(Connection con, JFrame f, String id) {

		fr = f;
		connection = con;
		i = Integer.valueOf(id);

		Frame = new JFrame("Payment");
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		num = new JTextField("Enter your credit card number");
		eDate = new JTextField("Enter the card's expiry date");
		purchase = new JButton("Purchase");
		cancel = new JButton("Cancel");

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 5;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(num, g);
		panel.add(num);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(eDate, g);
		panel.add(eDate);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(purchase, g);
		panel.add(purchase);

		g.gridx = 0;
		g.gridy = 4;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		panel.setLayout(gb);

		theHandler Handler = new theHandler();
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

				new Purchase().insertPurchase(connection, i, num.getText()
						.toString(), eD, d, del);
			}

		}

	}
}