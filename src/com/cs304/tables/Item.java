package com.cs304.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

//import com.cs304_p3.core;

public class Item {

	private Connection connect;

	private static final String createTableItem = "CREATE TABLE Item"
			+ "(UPC int not null," + "title char(30)," + "type char(30),"
			+ "catagory char(30)," + "company char(50)," + "year char(5),"
			+ "price float," + "stock int," + "PRIMARY KEY(UPC))";

	private static final String dropTableItem = "DROP TABLE Item CASCADE CONSTRAINTS";

	private static final String CreateItemSequence = "CREATE SEQUENCE item_counter\n"
			+ "START WITH 1\n" + "INCREMENT BY 1";

	private static final String DropItemSequence = "DROP SEQUENCE item_counter";

	public void createItem(Connection connect) throws SQLException {
		Statement state = null;
		try {
			state = connect.createStatement();
			state.executeUpdate(createTableItem);
			state.executeUpdate(CreateItemSequence);
		} catch (SQLException Error) {
			Error.printStackTrace();
		}
	}

	public void dropItem(Connection connect) throws SQLException {
		Statement state = null;
		try {
			state = connect.createStatement();
			state.executeUpdate(dropTableItem);
			state.executeUpdate(DropItemSequence);

		} catch (SQLException Error) {
			Error.printStackTrace();
		}
	}

	public void showItem(Connection connect) {
		String UPC;
		String title;
		String type;
		String catagory;
		String company;
		String year;
		String price;
		String stock;
		Statement state;
		ResultSet r;
		try {
			state = connect.createStatement();
			r = state.executeQuery("SELECT * FROM Item");

			ResultSetMetaData data = r.getMetaData();

			int Colnum = data.getColumnCount();

			System.out.println(" ");

			for (int i = 0; i < Colnum; i++) {
				// returns column name

				System.out.printf("%-15s", data.getColumnName(i + 1));
			}

			System.out.println(" ");

			while (r.next()) {
				UPC = r.getString("UPC");
				System.out.printf("\n%-10.10s", UPC);

				title = r.getString("title");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", title);
				}

				type = r.getString("type");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", type);
				}

				catagory = r.getString("catagory");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", catagory);
				}

				company = r.getString("company");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", company);
				}

				year = r.getString("year");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", year);
				}

				price = r.getString("price");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", price);
				}

				stock = r.getString("stock");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", stock);
				}

			}
			state.close();
		} catch (SQLException Error) {
			Error.printStackTrace();
		}

	}

	public void insertItem(Connection connect, int upc, String titl,
			String typ, String catagor, String compan, String yea, String pric,
			int stoc)

	{
		int UPC = upc;
		String title = titl;
		String type = typ;
		String catagory = catagor;
		String company = compan;
		String year = yea;
		int stock = stoc;

		float price = Float.parseFloat(pric);
		PreparedStatement p;

		try {
			p = connect
					.prepareStatement("INSERT INTO Item VALUES (?,?,?,?,?,?,?,?)");

			p.setInt(1, UPC);
			p.setString(2, title);
			p.setString(3, type);
			p.setString(4, catagory);
			p.setString(5, company);
			p.setString(6, year);
			p.setFloat(7, price);
			p.setInt(8, stock);

			p.executeUpdate();
			connect.commit();
			p.close();

		} catch (SQLException Error) {
			System.out.println("Insertion Error \n");
			Error.printStackTrace();

		}

	}

	public void deleteItem(Connection connect, int UPC) {
		PreparedStatement p;
		try {
			p = connect.prepareStatement("DELETE FROM Item WHERE UPC = ? ");
			p.setInt(1, UPC);

			int rCount = p.executeUpdate();

			if (rCount == 0) {
				System.out.println("\nItem UPC:" + UPC + " does not exist.");
			}

			connect.commit();
			p.close();

		} catch (SQLException Error) {
			Error.printStackTrace();
		}
	}

	public void updateStock(Connection connect, int stock, int upc) {
		PreparedStatement p;
		try {
			p = connect
					.prepareStatement("UPDATE item SET stock = ? WHERE UPC = ?");
			p.setInt(1, stock);
			p.setInt(2, upc);
			p.executeUpdate();
			connect.commit();
			p.close();

		} catch (SQLException Error) {
			System.out.println("Insertion Error \n");
			Error.printStackTrace();

		}
	}

	public void updatePrice(Connection connect, String price, int upc) {
		PreparedStatement p;
		try {
			p = connect
					.prepareStatement("UPDATE item SET price = ? WHERE UPC = ?");
			p.setString(1, price);
			p.setInt(2, upc);
			p.executeUpdate();
			connect.commit();
			p.close();

		} catch (SQLException Error) {
			System.out.println("Insertion Error \n");
			Error.printStackTrace();
		}
	}
}
