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

public class AddItems {

	Connection connection;

	private JFrame Frame;

	private JTextField UPC, quantity, price, songs, singers;

	private JButton add;
	private JButton cancel;

	public AddItems(Connection connection) {
		// TODO Auto-generated constructor stub

		// f =frame;
		this.connection = connection;

		Frame = new JFrame("Add Item");
		Frame.setVisible(true);
		Frame.setSize(500, 400);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		UPC = new JTextField("Enter CD/DVD UPC:");
		quantity = new JTextField("Enter the quantity:");
		price = new JTextField("(Optional) Enter the item price:");
		songs = new JTextField("Enter songs (seperated by comma");
		singers = new JTextField("Enter singers (seperated by comma)");

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
		g.gridy = 4;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(add, g);
		panel.add(add);

		g.gridx = 0;
		g.gridy = 5;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		g.gridx = 0;
		g.gridy = 6;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(songs, g);
		panel.add(songs);

		g.gridx = 0;
		g.gridy = 7;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(singers, g);
		panel.add(singers);

		panel.setLayout(gb);

		theHandler Handler = new theHandler();
		UPC.addActionListener(Handler);
		quantity.addActionListener(Handler);
		price.addActionListener(Handler);
		add.addActionListener(Handler);
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
