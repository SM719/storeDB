package com.cs304.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class PurchaseItem {

	private Connection connect;

	private static final String createTablePurchaseItem = "CREATE TABLE PurchaseItem"
			+ "(receiptID int not null,"
			+ " UPC int not null,"
			+ " quantity int,"
			+ " PRIMARY KEY(receiptID, UPC),"
			+ " FOREIGN KEY (UPC) references Item(UPC) ON DELETE CASCADE,"
			+ " FOREIGN KEY (receiptID) references Purchase(receiptID) ON DELETE CASCADE)";

	private static final String dropTablePurchaseItem = "DROP TABLE PurchaseItem CASCADE CONSTRAINTS";
	private static final String CreateSequence = "CREATE SEQUENCE PurchaseItemCounter\n"
			+ "START WITH 1\n" + "INCREMENT BY 1";
	private static final String DropSequence = "DROP SEQUENCE PurchaseItemCounter";

	public void createPurchaseItem(Connection connect) throws SQLException {
		Statement state = null;
		try {
			state = connect.createStatement();
			state.executeUpdate(createTablePurchaseItem);
			state.executeUpdate(CreateSequence);
		} catch (SQLException Error) {
			Error.printStackTrace();
		}
	}

	public void dropPurchaseItem(Connection connect) throws SQLException {
		Statement state = null;
		try {
			state = connect.createStatement();
			state.executeUpdate(dropTablePurchaseItem);
			state.executeUpdate(DropSequence);

		} catch (SQLException Error) {
			Error.printStackTrace();
		}
	}

	public void showPurchaseItem(Connection connect) throws SQLException {

		String receiptID;
		String UPC;
		String quantity;
		Statement state;
		ResultSet r;

		try {
			state = connect.createStatement();
			r = state.executeQuery("SELECT * FROM PurchaseItem");
			ResultSetMetaData data = r.getMetaData();
			int colNum = data.getColumnCount();

			System.out.println(" ");
			for (int i = 0; i < colNum; i++) {
				// returns column name
				System.out.printf("%-15s", data.getColumnName(i + 1));
			}

			System.out.println("");

			while (r.next()) {
				UPC = r.getString("UPC");
				System.out.printf("\n%-10.10s", UPC);

				receiptID = r.getString("receiptID");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", receiptID);
				}

				quantity = r.getString("quantity");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", quantity);
				}
			}
			state.close();
		} catch (SQLException Error) {
			Error.printStackTrace();
		}

	}

	public void insertPurchaseItem(Connection connect, int upc, int qty) {

		int UPC = upc;
		// .PurchaseItem.int receiptID = recID;
		int quantity = qty;
		PreparedStatement p;

		try {
			p = connect
					.prepareStatement("INSERT INTO PurchaseItem VALUES (PurchaseItemCounter.nextval,?,?)");

			p.setInt(1, UPC);
			// p.setInt(1, receiptID);
			p.setInt(2, quantity);

			p.executeUpdate();
			connect.commit();
			p.close();

		} catch (SQLException Error) {

			System.out.println("Insertion Error for PurchaseItem\n");
			Error.printStackTrace();

		}
	}

	public void deletePurchaseItem(Connection connect, int UPC) {

		PreparedStatement p;
		try {
			p = connect
					.prepareStatement("DELETE FROM PurchaseItem WHERE UPC = ? ");
			p.setInt(1, UPC);

			int rowCount = p.executeUpdate();

			if (rowCount == 0) {
				System.out.println("\nPurchaseItem UPC:" + UPC
						+ " does not exist.");
			}

			connect.commit();
			p.close();

		} catch (SQLException Error) {

			Error.printStackTrace();
		}
	}
}