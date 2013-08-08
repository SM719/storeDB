package com.cs304.customerUI;

//import java.awt.FlowLayout;
//import javax.swing.JFrame;
//import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * Customer Registration. 
 * Customers who access the store online for the first time, 
 * will be asked to register by providing their personal information,
 *  including their name, their address, phone number,an id and a password.
 *  If the id is already in the system, they will be asked to provide another one. 
 */

public class Purchase {

	// UNCOMMENT LATER
	Connection connection;

	private JFrame Frame, fr;

	private JLabel item1;
	private JTextField ID;
	private JPasswordField password;

	private JTextField category;
	private JTextField quantity;

	private JButton search;
	private JButton cancel;

	// constructor
	public Purchase(Connection con, JFrame f) {

		fr = f;
		connection = con;

		Frame = new JFrame("Customer Purchase");
		Frame.setVisible(true);
		Frame.setSize(500, 400);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		ID = new JTextField("Enter your user ID");
		category = new JTextField(
				"Enter your item category/title/lead singer(s)");
		quantity = new JTextField("Enter your item quantity");
		password = new JPasswordField();
		search = new JButton("Search");
		cancel = new JButton("Cancel");

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 5;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(ID, g);
		panel.add(ID);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(password, g);
		panel.add(password);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(category, g);
		panel.add(category);

		g.gridx = 0;
		g.gridy = 4;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(quantity, g);
		panel.add(quantity);

		g.gridx = 0;
		g.gridy = 5;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(search, g);
		panel.add(search);

		g.gridx = 0;
		g.gridy = 6;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		panel.setLayout(gb);

		theHandler Handler = new theHandler();
		ID.addActionListener(Handler);
		password.addActionListener(Handler);
		category.addActionListener(Handler);
		quantity.addActionListener(Handler);
		search.addActionListener(Handler);
		cancel.addActionListener(Handler);

		Frame.add(panel);
	}

	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == cancel) {
				Frame.dispose();
			}
		}

	}

}
