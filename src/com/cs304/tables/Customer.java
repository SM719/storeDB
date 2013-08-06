package com.cs304.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Customer{
	private Connection connect;
	
	private static final String createTableCustomer = "CREATE TABLE Customer"
														+ "(CID int not null,"
														+ "password char(30) not null,"
														+ "name char (30),"
														+ "address char(30),"
														+ "phone char(15),"
														+ "Primary Key (CID))";
	
	private static final String dropTableCustomer = "DROP TABLE Customer CASCADE CONSTRAINTS";
	
	
	public void createCustomer (Connection connect) throws SQLException {
		Statement state = null;
		try{
			state = connect.createStatement();
			state.executeUpdate(createTableCustomer);
			
		}catch (SQLException error)
		{
			error.printStackTrace();
		}
	}
	
	public void dropCustomer (Connection connect) throws SQLException {
		Statement state = null;
		try{
			state = connect.createStatement();
			state.executeUpdate(dropTableCustomer);
			//state.executeUpdate()
			}catch (SQLException Error)
			{
				Error.printStackTrace();
			}
	}
	
	public void showCustomer (Connection connect)
	{
		String CID;
		String Password;
		String name;
		String address;
		String phone; 
		Statement state;
		ResultSet r;
		
		try {
			state = connect.createStatement();
			r = state.executeQuery("SELECT * FROM Customer");
			
			ResultSetMetaData data = r.getMetaData();
			
			int Colnum = data.getColumnCount();
			
			System.out.println(" space ");
			
			for (int i = 0; i < Colnum; i++) {
				// returns column name

				System.out.printf("%-15s", data.getColumnName(i + 1));
			}
			System.out.println(" ");
			
			while(r.next())
			{
				CID = r.getString("CID");
				System.out.printf("%-10.10",CID ); 
				
				Password = r.getString("password");
				System.out.printf("%-20.20", Password);
				
				name = r.getString("name");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", name);
				}
				
				address = r.getString("address");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", address);
				}
				
				phone = r.getString("phone");
				if (r.wasNull()) {
					System.out.printf("%-20.20s", " ");
				} else {
					System.out.printf("%-20.20s", phone);
				}
			}
			state.close();
	}catch (SQLException Error)
	{
		Error.printStackTrace();
	}
}
	
public void insertCustomer (Connection connect, int cid, String pass, String name, String address, String phone)
{
	int CID = cid;
	String password = pass;
	String NAME = name;
	String ADDRESS = address;
	String PHONE = phone;
	PreparedStatement p;
	try {
		p = connect.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?)");
		
		p.setInt(1, CID);
		p.setString(2, password);
		p.setString(3, NAME);
		p.setString(4, ADDRESS);
		p.setString(5, PHONE);
		
		p.executeUpdate();
		connect.commit();
		p.close();
	}catch (SQLException Error)
	{
		System.out.println ("Insertion Error \n");
		Error.printStackTrace();
	}
	
	
}
	


public void deleteCustomer (Connection connect, int CID)
{
	PreparedStatement p;
	try {
		p = connect.prepareStatement("DELETE FROM Customer WHERE CID = ? ");
		p.setInt(1, CID);
		
		int rCount = p.executeUpdate();
		
		if (rCount == 0)
		{
			System.out.println("\nCustomer CID:" + CID
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