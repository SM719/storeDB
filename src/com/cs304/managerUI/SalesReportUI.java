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

public class SalesReportUI {

	Connection connection;

	private JFrame Frame, fr;

	private JTextField date;

	private JButton display;
	private JButton cancel;

	public SalesReportUI(Connection connection, JFrame f) {
		// TODO Auto-generated constructor stub

		fr = f;
		this.connection = connection;

		Frame = new JFrame("Sales Report");
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		date = new JTextField("Enter the date: ??/??/??");
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
		display.addActionListener(Handler);
		cancel.addActionListener(Handler);

		Frame.add(panel);
	}

	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener {

		String d;

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == cancel) {
				Frame.dispose();
			} else if (event.getSource() == display) {
				new SalesReportTable(connection, fr, date.getText());
			}
		}

	}

}
