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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

	private JTextField category, title, leadSinger;
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

		category = new JTextField("Enter your item category");
		title = new JTextField("Enter your item title");
		leadSinger = new JTextField("Enter your the lead singer");
		quantity = new JTextField("Enter your item quantity");
		search = new JButton("Search");
		cancel = new JButton("Cancel");

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 5;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(category, g);
		panel.add(category);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(quantity, g);
		panel.add(quantity);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(title, g);
		panel.add(title);

		g.gridx = 0;
		g.gridy = 4;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(leadSinger, g);
		panel.add(leadSinger);

		g.gridx = 0;
		g.gridy = 5;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(quantity, g);
		panel.add(quantity);

		g.gridx = 0;
		g.gridy = 6;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(search, g);
		panel.add(search);

		g.gridx = 0;
		g.gridy = 7;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		panel.setLayout(gb);

		theHandler Handler = new theHandler();

		category.addActionListener(Handler);
		quantity.addActionListener(Handler);
		title.addActionListener(Handler);
		leadSinger.addActionListener(Handler);

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
			} else if (event.getSource() == search) {

				// ERROR CHECKING
				if ((category.getText().equals("Enter your item category"))
						&& (title.getText().equals("Enter your item title"))
						&& (leadSinger.getText()
								.equals("Enter your the lead singer"))) {
					JOptionPane.showMessageDialog(null,
							"ERROR: Please enter atleast on item descroption");

				} else if ((quantity.getText().equals(""))
						|| quantity.getText()
								.equals("Enter your item quantity")) {
					JOptionPane.showMessageDialog(null,
							"ERROR: Please enter a quantity");

				} else {
					// CATEGORIES:
					// rock, pop, rap, country, classical, new age and
					// instrumental

					Integer stk = Integer
							.valueOf(quantity.getText().toString());

					new ItemList(connection, fr);

				}

			}

		}
	}

}
