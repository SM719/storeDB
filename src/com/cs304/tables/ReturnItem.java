package com.cs304.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ReturnItem {
	
	private Connection connect;
	
	private static final String createTableReturnItem = "CREATE TABLE ReturnItem" +
														"(retID int," +
														" UPC int," +
														" quantity int," +
														" PRIMARY KEY(retID, UPC)," +
														" FOREIGN KEY (UPC) references Item(UPC) ON DELETE CASCADE," +
														" FOREIGN KEY (retID) references Return(retID) ON DELETE CASCADE)";
														
	private static final String dropTableReturnItem = "DROP TABLE ReturnItem CASCADE CONSTRAINTS";
	
	public void createReturnItem(Connection connect) throws SQLException {
		Statement state = null;
		try{
			state = connect.createStatement();
			state.executeUpdate(createTableReturnItem);
		} catch (SQLException Error)
		{
			Error.printStackTrace();
		}	
	}
	
	public void dropReturnItem (Connection connect) throws SQLException {
		Statement state = null; 
		try {
			state = connect.createStatement();
			state.executeUpdate(dropTableReturnItem);
			
		}catch (SQLException Error)
		{
			Error.printStackTrace ();
		}
	}

	public void showReturnItem(Connection connect) throws SQLException {
		
		String retID;
		String UPC;
		String quantity;
		Statement state;
		ResultSet r;
		
		try{
			state = connect.createStatement();
			r = state.executeQuery("SELECT * FROM ReturnItem");
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
				System.out.printf("%-10.10s", UPC);
				
				retID = r.getString("retID");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", retID);
				}
				
				quantity = r.getString("quantity");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", quantity);
				}
			}
			state.close();
		}
		catch (SQLException Error){
			Error.printStackTrace();
		}
			
	}
	
	public void insertReturnItem(Connection connect, int rID, int upc, int qty){
		
		int UPC = upc;
		int retID = rID;
		int quantity = qty;
		PreparedStatement p;
		
		try {
			p = connect.prepareStatement("INSERT INTO ReturnItem VALUES (?,?,?)");
			
			p.setInt(2, UPC);
			p.setInt(1, retID);
			p.setInt(3, quantity);
			
			p.executeUpdate();
			connect.commit();
			p.close();
			
		}
		catch (SQLException Error){
			
			System.out.println ("Insertion Error for ReturnItem\n");
			Error.printStackTrace();
			
		}
	}
	
	public void deleteReturnItem (Connection connect, int UPC){
		
		PreparedStatement p;
		try{
			p = connect.prepareStatement("DELETE FROM ReturnItem WHERE UPC = ? ");
			p.setInt(1, UPC);
			
			int rowCount = p.executeUpdate();
			
			if (rowCount == 0)
			{
				System.out.println("\nReturnItem UPC:" + UPC
						+ " does not exist.");
			}
			
			connect.commit();
			p.close();
			
		}
		catch (SQLException Error){
			
			Error.printStackTrace();
		}
	}	
}