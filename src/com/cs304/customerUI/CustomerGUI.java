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

public class CustomerGUI {

	// UNCOMMENT LATER
	Connection connection;

	private JFrame Frame, fr;

	private JLabel item1;

	private JButton register;
	private JButton purchaseOnline;
	private JButton close;

	// constructor
	public CustomerGUI(Connection con, JFrame f) {

		connection = con;
		fr = f;

		Frame = new JFrame("Customer Operations");
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		close = new JButton("Exit");
		register = new JButton("Register");
		purchaseOnline = new JButton("Purchase items online");

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 5;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(register, g);
		panel.add(register);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(purchaseOnline, g);
		panel.add(purchaseOnline);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(close, g);
		panel.add(close);

		panel.setLayout(gb);

		theHandler Handler = new theHandler();
		register.addActionListener(Handler);
		purchaseOnline.addActionListener(Handler);
		close.addActionListener(Handler);

		Frame.add(panel);
	}

	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == register) {
				new Registration(connection, fr);
			} else if (event.getSource() == purchaseOnline) {
				new Purchase(connection, fr);
			} else if (event.getSource() == close) {
				Frame.dispose();
			}
		}

	}

}
