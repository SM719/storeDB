package com.cs304.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.cs304_p3.core;

public class HasSong {
			//private Connection connect;
			
			private static final String createTableHasSong = "CREATE TABLE HasSong"
															+ "(UPC int not null,"
															+ "title char(30) not null,"
															+ "PRIMARY KEY(UPC,title),"
															+ "FOREIGN KEY(UPC) references Item(UPC) ON DELETE CASCADE)";
			
			private static final String dropTableHasSong = "DROP TABLE HasSong";
			
	public void createHasSong (Connection connect) throws SQLException {
				Statement state = null;
					
				try {
					state = connect.createStatement();
					state.executeUpdate(createTableHasSong);
				}catch (SQLException error)
				{
					error.printStackTrace();
				}
			}
			
	public void dropHasSong (Connection connect) throws SQLException {
		Statement state = null; 
		
		try {
			state = connect.createStatement();
			state.executeUpdate(dropTableHasSong);
			
		}catch (SQLException error)
		{
			error.printStackTrace();
		}
	}
	
	public void showHasSong (Connection connect) throws SQLException {
		Statement state = null; 
		String UPC;
		String title;
		ResultSet r;
		try {
			state = connect.createStatement();
			r = state.executeQuery("SELECT * FROM HasSong");
			
			ResultSetMetaData rs = r.getMetaData();
			
			int colnum = rs.getColumnCount();
			
			System.out.println(" ");
			
			for (int i = 0; i< colnum; i++)
			{
				System.out.printf("%-15s", rs.getColumnName(i+1));
			}
			System.out.println(" ");
			
			while (r.next())
			{
				UPC = r.getString("UPC");
				System.out.printf("%-10.10s", UPC);
				
				title = r.getString("title");
				System.out.printf("%-20.20s", title);
				
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
		
	
	public void insertHasSong (Connection connect, int upc, String titl)
	{
		int UPC = upc;
		String title = titl;
		PreparedStatement p;
		
		try{
			p = connect.prepareStatement("INSERT INTO HasSong VALUES (?,?)");
			p.setInt(1, UPC);
			p.setString(2, title);
			
			p.executeUpdate();
			connect.commit();
			p.close();
		}catch (SQLException error)
		{
			System.out.println ("Insertion Error \n");
			error.printStackTrace();
		}
		
	}
	
	public void deleteHasSong (Connection connect, int upc)
	{
		PreparedStatement p;
		try{
			p = connect.prepareStatement("DELETE FROM HasSong WHERE UPC = ? ");
			
			p.setInt (1, upc);
			
			int CountRow = p.executeUpdate();
			
			if (CountRow == 0){
				System.out.println("\n Song:" + upc + "does not exist \n"); 
							}
			connect.commit();
		}catch (SQLException error)
		{
			System.out.println ("Deletion error");
			System.exit(1);
		}
	}
}