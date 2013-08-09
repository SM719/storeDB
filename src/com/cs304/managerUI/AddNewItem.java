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

	private JTextField price, title, type, category, company, year, songs,
			singers;

	private JButton add;
	private JButton cancel;

	public AddNewItem(Connection connection, JFrame f, String upc,
			String quantity, String pr) {
		// TODO Auto-generated constructor stub

		fr = f;
		this.connection = connection;

		Frame = new JFrame("Add New Item");
		Frame.setVisible(true);
		Frame.setSize(700, 600);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		price = new JTextField("Enter item price:");
		title = new JTextField("Enter item title:");
		type = new JTextField("Enter item type cd or dvd:");
		category = new JTextField("Enter item category:");
		company = new JTextField("Enter item company:");
		year = new JTextField("Enter item year:");
		add = new JButton("Add Item");
		cancel = new JButton("Cancel");
		songs = new JTextField("Enter item songs (seperate each song by comma)");
		singers = new JTextField(
				"Enter item singers (seperate each song by comma)");

		g.gridx = 0;
		g.gridy = 8;
		g.gridwidth = 10;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(songs, g);
		panel.add(songs);

		g.gridx = 0;
		g.gridy = 1;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(price, g);
		panel.add(price);

		g.gridx = 0;
		g.gridy = 7;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(singers, g);
		panel.add(singers);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(title, g);
		panel.add(title);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(type, g);
		panel.add(type);

		g.gridx = 0;
		g.gridy = 4;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(category, g);
		panel.add(category);

		g.gridx = 0;
		g.gridy = 5;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(company, g);
		panel.add(company);

		g.gridx = 0;
		g.gridy = 6;
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
		songs.addActionListener(Handler);
		singers.addActionListener(Handler);
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
