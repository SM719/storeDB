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

/*
 * Top Selling Items: The user provides a date and a number, say n.
 *  The system prints a list of the n best selling items on that day. 
 *  For each best seller, the system shows the title, the company,
 *  the current stock and the number of copies sold. 
 *  The output should be ordered according to sales: 
 *  the best selling item should be first, the second best will follow, etc.
 */

public class TopSellers {

	Connection connection;

	private JFrame Frame;

	private JTextField date;
	private JTextField num;

	private JButton display;
	private JButton cancel;

	// constructor
	public TopSellers(Connection con) {

		// f =frame;
		connection = con;

		Frame = new JFrame("Top Selling Items");
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		date = new JTextField("Enter the date: ??/??/??");
		num = new JTextField("Enter the number of items:");
		display = new JButton("Search");
		cancel = new JButton("Cancel");

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 5;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(date, g);
		panel.add(date);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(num, g);
		panel.add(num);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(display, g);
		panel.add(display);

		g.gridx = 0;
		g.gridy = 4;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		panel.setLayout(gb);

		theHandler Handler = new theHandler();
		date.addActionListener(Handler);
		num.addActionListener(Handler);

		display.addActionListener(Handler);
		cancel.addActionListener(Handler);

		Frame.add(panel);
	}

	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

		}

	}

}
