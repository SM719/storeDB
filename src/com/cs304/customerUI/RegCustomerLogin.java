package com.cs304.customerUI;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegCustomerLogin {

	Connection connection;

	private JFrame Frame, fr;

	private JTextField ID;
	private JPasswordField password;

	private JButton login;
	private JButton cancel;

	// constructor
	public RegCustomerLogin(Connection con, JFrame f) {

		fr = f;
		connection = con;

		Frame = new JFrame("Login");
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		ID = new JTextField("Enter your user ID");
		password = new JPasswordField();
		login = new JButton("Login");
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
		gb.setConstraints(login, g);
		panel.add(login);

		g.gridx = 0;
		g.gridy = 4;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		panel.setLayout(gb);

		theHandler Handler = new theHandler();
		ID.addActionListener(Handler);
		password.addActionListener(Handler);

		login.addActionListener(Handler);
		cancel.addActionListener(Handler);

		Frame.add(panel);
	}

	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener {

		String d;
		int num;
		boolean log = true;

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == cancel) {
				Frame.dispose();
			} else if (event.getSource() == login) {

				if ((ID.getText().equals("Enter your user ID"))
						|| (ID.getText().equals(""))) {

					JOptionPane.showMessageDialog(null,
							"ERROR: Please enter your use ID");
				} else if (password.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null,
							"ERROR: Please enter a password");
				} else if ((password.getPassword().equals("") && (ID.getText()
						.equals("")))) {
					JOptionPane.showMessageDialog(null,
							"ERROR: Please enter your user ID and password");
				} else {
					try {

						Statement state = connection.createStatement();
						Integer CID = Integer.valueOf(ID.getText().toString());
						String p = password.getPassword().toString();
						ResultSet r = state
								.executeQuery("SELECT CID FROM Customer");
						while (r.next()) {
							int cid = r.getInt("CID");

							if ((CID != cid) && !(p.equals(""))) {
								log = false;
								ID.setText("");
								password.setText("");
							} else {
								log = true;
							}
						}
						connection.commit();
						state.close();

					} catch (SQLException error) {
						System.out.println(error.getMessage());
					}

					if (log) {
						new Purchase(connection, fr);
						Frame.dispose();
						log = false;
					} else {
						new TryLoginAgain(connection, fr);
						Frame.dispose();
					}
				}

			}

		}
	}
}