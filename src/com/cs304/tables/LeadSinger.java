package com.cs304.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class LeadSinger {
	private Connection connect;
	
	private static final String createTableLeadSinger = "CREATE TABLE LeadSinger"
														+ "(UPC int not null,"
														+ "name char(30) not null,"
														+ "PRIMARY KEY (UPC, name),"
														+ "FOREIGN KEY (UPC) references Item(UPC) ON DELETE CASCADE)";
	
	private static final String dropTableLeadSinger = "DROP TABLE LeadSinger";
	
	
	public void createLeadSinger (Connection connect) throws SQLException {
		Statement state = null;
		try {
			state = connect.createStatement();
			state.executeUpdate(createTableLeadSinger);
			} catch (SQLException error)
			{
				error.printStackTrace();
			}
	}
	
	public void dropLeadSinger (Connection connect) throws SQLException {
		Statement state = null ;
		
		try {
				state = connect.createStatement();
				state.executeUpdate (dropTableLeadSinger);
		}catch (SQLException error)
		{
			error.printStackTrace();
		}
	}
	
	public void showLeadSinger (Connection connect) throws SQLException {
		Statement state = null;
		String UPC;
		String name;
		ResultSet r;
		
		try {
				state = connect.createStatement();
				r = state.executeQuery("SELECT * FROM LeadSinger");
				
				ResultSetMetaData rs = r.getMetaData();
				int colnum = rs.getColumnCount();
				System.out.println(" ");
				
				for (int i = 0; i< colnum; i++)
				{
					System.out.printf("%-15s", rs.getColumnName (i+1));
				}
				System.out.println(" ");
				
				while (r.next())
				{
					
					UPC = r.getString("UPC");
					System.out.printf ("%-10.10s",UPC);
					
					name = r.getString("name");
					System.out.printf("%-20.20s", name);
					
					
				}
		}catch (SQLException error)
		{
			error.printStackTrace();
		}
		
	}
	
	public void insertLeadSinger (Connection connect, int upc, String iname)
	{
		int UPC = upc;
		String name = iname;
		PreparedStatement p;
		
		try{
			p = connect.prepareStatement("INSERT INTO LeadSinger VALUES (?,?)");
			p.setInt(1, UPC);
			p.setString(2, name);
			
			p.executeUpdate();
			connect.commit();
			p.close();
		}catch (SQLException error)
		{
			System.out.println ("Insertion error \n");
			error.printStackTrace();
		}
	}
	
	public void deleteLeadSinger (Connection connect, int upc)
	{
		PreparedStatement p;
		try {
			p = connect.prepareStatement("DELETE FROM LeadSinger WHERE UPC = ?");
			
			p.setInt(1, upc);
			
			int CountRow = p.executeUpdate();
			if (CountRow == 0)
			{
				System.out.println ("\n LeadSinger UPC:" + upc + "does not exist \n");
				connect.commit();
			}
		}catch (SQLException error)
		{
			System.out.println("deletion Error");
			System.exit(1);
		}
		
	}
	
}