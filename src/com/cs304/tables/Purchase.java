package com.cs304.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.GregorianCalendar;

public class Purchase {
	private Connection connect;
	
	private static final String createTablePurchase = "CREATE TABLE Purchase"
													+ "(receiptID int not null,"
													+ "date Date,"
													+ "CID int not null,"
													+ "card# char(30),"
													+ "expiryDate Date,"
													+ "expectedDate Date,"
													+ "deliveredDate Date,"
													+ "PRIMARY KEY(receiptID),"
													+ "FOREIGN KEY (CID) references Customer(CID) ON DELETE CASCADE)";
	
	private static final String dropTablePurchase = "DROP TABLE Purchase";
	
	private static final String CreateSequence = "CREATE SEQUENCE PurchaseCounter\n" + "START WITH 1\n" +"INCREMENT BY 1";
	private static final String DropSequence = "DROP SEQUENCE PurchaseCounter";
	
	public void createPurchase (Connection connect) throws SQLException {
		Statement state = null;
		try {
			state = connect.createStatement();
			state.executeUpdate(createTablePurchase);
			state.executeUpdate(CreateSequence);
			
		}catch (SQLException Error)
		{
			Error.printStackTrace();
			
		}
	}
	
	public void dropPurchase (Connection connect) throws SQLException {
		Statement state = null; 
		try {
			state = connect.createStatement();
			state.executeUpdate(dropTablePurchase);
			state.executeUpdate(DropSequence);
			
		}catch (SQLException Error)
		{
			Error.printStackTrace ();
		}
		
	}
	
	public void showPurchase (Connection connect){
		String Receipt;
		Date date;
		String cid;
		String cardnum;
		Date eDate;
		Date exDate;
		Date dDate;
		Statement state;
		ResultSet r;
		
		try {
				state = connect.createStatement();
				r = state.executeQuery("SELECT * FROM Purchase");
				ResultSetMetaData data = r.getMetaData();
				
				int Colnum = data.getColumnCount();
				
				System.out.println(" ");
				
				for (int i = 0; i < Colnum; i++) {
					// returns column name

					System.out.printf("%-15s", data.getColumnName(i + 1));
				}
				System.out.println(" ");
				while (r.next()) { 
					Receipt = r.getString("receiptID");
					System.out.printf("%-10.10s", Receipt);
					
					date = r.getDate(2);
					if (r.wasNull()) {
						System.out.printf("%-20.20s", " ");
					} else {
						System.out.printf("%-20.20s", date);
					}
					
					cid = r.getString("CID");
					System.out.printf("%-20.20s", cid);
					
					cardnum = r.getString("card#");
					if (r.wasNull()) {
						System.out.printf("%-20.20s", " ");
					} else {
						System.out.printf("%-20.20s", cardnum);
					}
					
					eDate = r.getDate(5);
					if (r.wasNull()) {
						System.out.printf("%-20.20s", " ");
					} else {
						System.out.printf("%-20.20s", eDate);
					}
					
					exDate = r.getDate(6);
					if (r.wasNull()) {
						System.out.printf("%-20.20s", " ");
					} else {
						System.out.printf("%-20.20s", exDate);
					}
					
					dDate = r.getDate(7);
					if (r.wasNull()) {
						System.out.printf("%-20.20s", " ");
					} else {
						System.out.printf("%-20.20s", dDate);
					}

				}
				state.close();
		}catch (SQLException Error)
		{
			Error.printStackTrace();
		}
	}
	
	public void insertPurchase (Connection connect, int CID, String Cardnum, Date expireDate, Date expectedDate, Date deliveredDate)
	{
		PreparedStatement p;
		int cid = CID;
		String Cardn = Cardnum;
		Date eDate = expireDate;
		Date exDate = expectedDate;
		Date dDate = deliveredDate;
		
		try {
			p = connect.prepareStatement("INSERT INTO Purchase VALUES (PurchaseCounter.nextval,?,?,?,?,?,?)");
			
			GregorianCalendar calendar = new GregorianCalendar();
			
			java.sql.Date sDate = new java.sql.Date(calendar.getTime().getTime());
			
			p.setDate(2, sDate);
			p.setInt(3,cid);
			p.setString(4, Cardn);
			p.setDate (5,eDate);
			p.setDate(6, exDate);
			p.setDate(7, dDate);
			
			p.executeUpdate();
			connect.commit();
			p.close();
		}catch (SQLException Error)
		{
			System.out.println ("Insertion Error \n");
			Error.printStackTrace();
			
		}
		
	}

	public void deletePurchase (Connection connect, int receiptID)
	{
		PreparedStatement p;
		
		try {
			p = connect.prepareStatement("DELETE FROM Purchase WHERE receiptID = ?");
			
			p.setInt (1,receiptID);
			
			int rCount = p.executeUpdate();
			
			if (rCount == 0)
			{
				System.out.println("\nPurchase receiptID:" + receiptID
						+ " does not exist.");
			}
			
			connect.commit();
			p.close();
		}catch (SQLException Error)
		{
			Error.printStackTrace();
		}
	}
	
}