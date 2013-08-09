package com.cs304.managerUI;

import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableGUI extends JFrame {

	JTable table;
	Connection connect;
	JFrame fr;

	public TableGUI() {
		// connect = con;
		// fr = f;
		setSize(500, 500);
		String[] columnNames = { "UPC", "Category", "UnitPrice", "Units",
				"TotalValue" };

		Object[][] data = { { "q", "w", "e", "r" }, { "r", "t", "y", "t" } };

		table = new JTable(data, columnNames);
		// table.setSize(500, 500);
		table.setPreferredScrollableViewportSize(new Dimension(500, 500));
		table.setFillsViewportHeight(true);

		JScrollPane scroll = new JScrollPane(table);
		add(scroll);

	}

}
