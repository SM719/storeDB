package com.cs304.managerUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class BestSellersTable {

	private JPanel topPanel;
	private JTable table;
	private JScrollPane scrollPane;

	private JFrame fr;
	Connection connect;

	// Constructor of main frame
	public BestSellersTable(Connection con, JFrame f) {
		// Set the frame characteristics
		connect = con;
		fr = f;

		JFrame frame = new JFrame();
		frame.setTitle("Top Selling Items");

		// auto fill later
		Object rowData[][] = {
				{ "Row1-Column1", "Row1-Column2", "Row1-Column3",
						"Row1-Column4" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3",
						"Row2-Column5" } };

		Object columnNames[] = { "Title", "Company", "Stock", "CopiesSold" };

		JTable table = new JTable(rowData, columnNames);

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(500, 250);
		frame.setVisible(true);
	}

	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

}