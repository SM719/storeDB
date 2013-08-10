package com.cs304.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.GregorianCalendar;

public class Return {
	private Connection connect;
	
	private static final String createTableReturn = "CREATE TABLE Return"
													+ "(retid int not null,"
													+ "rdate Date,"
													+ "receiptID int not null,"
													+ "PRIMARY KEY (retid),"
													+ "FOREIGN KEY (receiptID) references Purchase(receiptID))";
	
	private static final String dropTableReturn = "DROP TABLE Return CASCADE CONSTRAINTS";
	
	private static final String createReturnSequence = "CREATE SEQUENCE returnidCounter\n" + "START WITH 1\n" + "INCREMENT BY 1";
	private static final String DropReturnSequence = "DROP SEQUENCE returnidCounter";
	
	
	public void createReturn (Connection connect) throws SQLException{
		Statement state = null;
		try{
		state = connect.createStatement();
		state.executeUpdate(createTableReturn);
		state.executeUpdate(createReturnSequence);
		} catch (SQLException Error)
		{
			Error.printStackTrace();
		}	
	}
	
	public void dropReturn (Connection connect) throws SQLException {
		Statement state = null; 
		try {
			state = connect.createStatement();
			state.executeUpdate(dropTableReturn);
			state.executeUpdate(DropReturnSequence);
			
		}catch (SQLException Error)
		{
			Error.printStackTrace ();
		}
	}
	
	
	public void showReturn (Connection connect)
	{
		String returnID;
		Date date;
		String receiptID;
		Statement state;
		ResultSet r;
		
		try {
			state = connect.createStatement();
			r = state.executeQuery("SELECT * FROM Return");
			
			ResultSetMetaData data = r.getMetaData();
			
			int Colnum = data.getColumnCount();
			
			System.out.println(" ");
			
			for (int i = 0; i < Colnum; i++) {
				// returns column name

				System.out.printf("%-15s", data.getColumnName(i + 1));
			}
			System.out.println(" ");
			
			while (r.next()) { 
				receiptID = r.getString("retid");
				System.out.printf("\n%-10.10s", receiptID);
				
				date = r.getDate(2);
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", date);
				}
				
				receiptID = r.getString("receiptID");
				System.out.printf("%-20.20s", receiptID);
				
			}
			state.close();
	}catch (SQLException Error)
	{
		Error.printStackTrace();
	}
}
	
public void insertReturn (Connection connect, int receiptID)
{
	PreparedStatement p;
	int receiptid =  receiptID;
	
	try{
		p = connect.prepareStatement("INSERT INTO Return VALUES (returnidCounter.nextval,?,?)");
		
		GregorianCalendar calendar = new GregorianCalendar();
		
		java.sql.Date sDate = new java.sql.Date(calendar.getTime().getTime());
		p.setDate(1, sDate);
		p.setInt(2, receiptid);

		p.executeUpdate();
		connect.commit();
		p.close();
		
	}catch (SQLException error)
	{
	System.out.println ("Insertion Error \n");
	error.printStackTrace();
		
	}
	
}
	
public void deleteReturn (Connection connect, int returnid)
{
	PreparedStatement p;
	try {
		p = connect.prepareStatement("DELETE FROM Return WHERE retid = ?");
		p.setInt(1, returnid);
		
		int rCount = p.executeUpdate();
		
		if (rCount == 0)
		{
			System.out.println("\nReturn retid:" + returnid
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