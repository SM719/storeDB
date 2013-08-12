package com.cs304.managerUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.Vector;

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
	public BestSellersTable(Connection con, JFrame f, String date, int n) {
		// Set the frame characteristics
		connect = con;
		fr = f;

		JFrame frame = new JFrame();
		frame.setTitle("Top Selling Items");
		// Parse String D so we can enter date we want for delivery date
		String[] dateList = date.split("/");
		dateList[0] = "20" + dateList[0];
		int counter = 0;

		GregorianCalendar calendar = new GregorianCalendar(
				Integer.parseInt(dateList[0]),
				(Integer.parseInt(dateList[1]) - 1),
				Integer.parseInt(dateList[2]));
		java.sql.Date sday = new java.sql.Date(calendar.getTime().getTime());
		System.out.println(sday);

		int[] upc = new int[10];
		Integer[] sold = new Integer[10];
		String[] title = new String[10];
		String[] company = new String[10];
		Integer[] q = new Integer[10];

		int count2 = 0;
		try {
			connect.setAutoCommit(false);
			Statement state = connect.createStatement();
			ResultSet rs1 = state
					.executeQuery("SELECT * FROM  Purchase LEFT JOIN PurchaseItem ON PurchaseItem.receiptID = Purchase.receiptID");
			while (rs1.next() == true) {
				if (rs1.getDate("bdate").compareTo(sday) == 0) {
					upc[count2] = rs1.getInt("UPC");
					sold[count2] = rs1.getInt("quantity");
					// title[count2] = rs1.getString("title");
					// ompany[count2] = rs1.getString("company");
					count2++;
				}
				counter++;
			}
			Statement state2 = connect.createStatement();
			ResultSet rs2 = state2.executeQuery("Select * From Item");
			while (rs2.next() == true) {

				for (int i = 0; i < upc.length; i++) {
					if (upc[i] == rs2.getInt("UPC")) {
						System.out.println("test2");
						title[i] = rs2.getString("title");
						company[i] = rs2.getString("company");
						q[i] = rs2.getInt("stock");

					}
				}
			}

			// Have array sorted in order of most bought

			con.commit();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vector<String> rowOne = new Vector<String>();
		if (n >= 1) {
			rowOne.addElement(title[0]);
			rowOne.addElement(company[0]);
			rowOne.addElement(q[0].toString());
			rowOne.addElement(sold[0].toString());

		} else {
			rowOne.addElement("");
			rowOne.addElement("");
			rowOne.addElement("");
			rowOne.addElement("");
		}

		Vector<String> rowTwo = new Vector<String>();
		if (n >= 2) {
			rowTwo.addElement(title[1]);
			rowTwo.addElement(company[1]);
			rowTwo.addElement(q[1].toString());
			rowTwo.addElement(sold[1].toString());

		} else {
			rowTwo.addElement("");
			rowTwo.addElement("");
			rowTwo.addElement("");
			rowOne.addElement("");
		}
		Vector<String> rowThree = new Vector<String>();
		if (n >= 3) {
			rowThree.addElement(title[2]);
			rowThree.addElement(company[2]);
			rowThree.addElement(q[2].toString());
			rowThree.addElement(sold[2].toString());
		} else {
			rowThree.addElement("");
			rowThree.addElement("");
			rowThree.addElement("");
			rowThree.addElement("");
		}

		Vector<String> rowFour = new Vector<String>();
		if (n >= 4) {
			rowFour.addElement(title[3]);
			rowFour.addElement(company[3]);
			rowFour.addElement(q[3].toString());
			rowFour.addElement(sold[3].toString());
		} else {
			rowFour.addElement("");
			rowFour.addElement("");
			rowFour.addElement("");
			rowFour.addElement("");
		}

		Vector<String> rowFive = new Vector<String>();
		if (n >= 5) {
			rowFive.addElement(title[4]);
			rowFive.addElement(company[4]);
			rowFive.addElement(q[4].toString());
			rowFive.addElement(sold[4].toString());
		} else {
			rowFive.addElement("");
			rowFive.addElement("");
			rowFive.addElement("");
			rowFive.addElement("");
		}

		Vector<Vector> rowData = new Vector<Vector>();
		rowData.addElement(rowOne);
		rowData.addElement(rowTwo);
		rowData.addElement(rowThree);
		rowData.addElement(rowFour);
		rowData.addElement(rowFive);
		// Object rowData[][] = {
		// { "Row1-Column1", company[0], "Row1-Column3", "Row1-Column4" },
		// { "Row2-Column1", "Row2-Column2", "Row2-Column3",
		// "Row2-Column5" } };

		// auto fill later

		// Object columnNames[] = { "Title", "Company", "Stock", "CopiesSold" };
		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("Title");
		columnNames.addElement("Company");
		columnNames.addElement("Stock");
		columnNames.addElement("CopiesSold");

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