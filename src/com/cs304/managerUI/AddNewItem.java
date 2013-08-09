package com.cs304.managerUI;

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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewItem {

	Connection connection;

	private JFrame Frame, fr;

	private JTextField UPC, price, title, type, category, company, year, stock;

	private JButton add;
	private JButton cancel;

	public AddNewItem(Connection connection, JFrame f, String upc,
			String quantity, String pr) {
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

		UPC = new JTextField();
		UPC.setText(upc);

		price = new JTextField();
		price.setText(pr);

		stock = new JTextField("Enter item price if different:");
		stock.setText(quantity);

		title = new JTextField("Enter title:");
		type = new JTextField("Enter item type:");
		category = new JTextField("Enter the category:");
		company = new JTextField("Enter the company:");
		year = new JTextField("Enter the year:");
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
		gb.setConstraints(price, g);
		panel.add(price);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(stock, g);
		panel.add(stock);

		g.gridx = 0;
		g.gridy = 4;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(title, g);
		panel.add(title);

		g.gridx = 0;
		g.gridy = 5;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(type, g);
		panel.add(type);

		g.gridx = 0;
		g.gridy = 6;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(category, g);
		panel.add(category);

		g.gridx = 0;
		g.gridy = 7;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(company, g);
		panel.add(company);

		g.gridx = 0;
		g.gridy = 8;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(year, g);
		panel.add(year);

		g.gridx = 0;
		g.gridy = 9;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(add, g);
		panel.add(add);

		g.gridx = 0;
		g.gridy = 10;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		panel.setLayout(gb);

		theHandler Handler = new theHandler(this.connection);
		UPC.addActionListener(Handler);
		stock.addActionListener(Handler);
		price.addActionListener(Handler);
		title.addActionListener(Handler);
		type.addActionListener(Handler);
		category.addActionListener(Handler);
		company.addActionListener(Handler);
		year.addActionListener(Handler);
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

			}
		}
	}
}
