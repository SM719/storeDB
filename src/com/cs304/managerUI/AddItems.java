package com.cs304.managerUI;

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
import javax.swing.JTextField;

import com.cs304.tables.Item;

public class AddItems {

	Connection connection;

	private JFrame Frame, fr;

	private JTextField UPC, quantity, price;

	private JButton add;
	private JButton cancel;

	public AddItems(Connection connection, JFrame f) {
		// TODO Auto-generated constructor stub

		fr = f;
		this.connection = connection;

		Frame = new JFrame("Add Item");
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		UPC = new JTextField("Enter CD/DVD UPC:");
		quantity = new JTextField("Enter item quantity:");
		price = new JTextField("Enter item price if different:");

		add = new JButton("Add Item");
		cancel = new JButton("Cancel");

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 7;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(UPC, g);
		panel.add(UPC);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(quantity, g);
		panel.add(quantity);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(price, g);
		panel.add(price);

		g.gridx = 0;
		g.gridy = 6;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(add, g);
		panel.add(add);

		g.gridx = 0;
		g.gridy = 7;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		panel.setLayout(gb);

		theHandler Handler = new theHandler(this.connection);
		UPC.addActionListener(Handler);
		quantity.addActionListener(Handler);
		price.addActionListener(Handler);
		add.addActionListener(Handler);
		cancel.addActionListener(Handler);

		Frame.add(panel);
	}

	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener {
		Connection con;

		public theHandler(Connection connection) {
			this.con = connection;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == cancel) {
				Frame.dispose();
			} else if (event.getSource() == add) {

				// Below if statements ensure only valid UPC, quantity and
				// prices are entered
				if ((UPC.getText().equals("Enter CD/DVD UPC:"))
						|| (UPC.getText().equals(""))) {
					JOptionPane.showMessageDialog(null,
							"ERROR: Please Enter UPC");
				} else if (!(UPC.getText().matches("[0-9]+"))) {
					JOptionPane.showMessageDialog(null,
							"ERROR: UPC can only contain numbers");
				} else if ((quantity.getText().equals(""))
						|| (quantity.getText().equals("Enter the quantity:"))) {
					JOptionPane.showMessageDialog(null,
							"ERROR: Please Enter Quantity");
				} else if (!(quantity.getText().matches("[0-9]+"))) {
					JOptionPane.showMessageDialog(null,
							"ERROR: Quantity can only contain numbers");
				} else if ((!(price.getText().matches("[0-9,.]+")) && (!(price
						.getText().contains("Enter item price if different:"))))) {
					JOptionPane.showMessageDialog(null,
							"ERROR: Price can only contain numbers");
				} else {
					System.out.println(UPC.getText());
					try {
						con.setAutoCommit(false);
						Statement state = con.createStatement();
						ResultSet r = state
								.executeQuery("SELECT UPC, stock FROM Item");
						while (r.next()) {
							int upc = r.getInt("UPC");
							int stock = r.getInt("stock");
							if (upc == Integer.parseInt(UPC.getText())) {
								if (Integer.parseInt(quantity.getText()) > 0) {
									stock += Integer.parseInt(quantity
											.getText());
									new Item().updateStock(con, stock, upc);
								}
								if (!(price.getText()
										.equals("Enter item price if different:"))) {
									new Item().updatePrice(con,
											price.getText(), upc);
								}
							}
						}

						con.commit();
						state.close();
					} catch (SQLException error) {
						System.out.println(error.getMessage());
					}

				}
			}
		}
	}

}
