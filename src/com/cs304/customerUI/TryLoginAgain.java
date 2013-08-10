package com.cs304.customerUI;

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

public class TryLoginAgain {

	Connection connection;

	private JFrame Frame, fr;

	private JLabel message;

	private JButton tryAgain;
	private JButton register;

	// constructor
	public TryLoginAgain(Connection con, JFrame f) {

		fr = f;
		connection = con;

		Frame = new JFrame("Try Again?");
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		message = new JLabel("Would you like to try again or register?");
		tryAgain = new JButton("Try Again");
		register = new JButton("Register");

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 5;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(message, g);
		panel.add(message);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(tryAgain, g);
		panel.add(tryAgain);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(register, g);
		panel.add(register);

		panel.setLayout(gb);

		theHandler Handler = new theHandler();

		tryAgain.addActionListener(Handler);
		register.addActionListener(Handler);

		Frame.add(panel);
	}

	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == tryAgain) {
				new RegCustomerLogin(connection, fr);
				Frame.dispose();
			} else if (event.getSource() == register) {
				new Registration(connection, fr);
				Frame.dispose();
			}

		}

	}
}