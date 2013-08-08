package com.cs304_p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import com.cs304.UIs.Login;
import com.cs304.UIs.MenuBar;
import com.cs304.tables.HasSong;
import com.cs304.tables.Item;
import com.cs304.tables.LeadSinger;

public class core {
		//reads cmd line
	
	private BufferedReader BR = new BufferedReader (new InputStreamReader(System.in));
	
	private static Connection connect;
	
	private static Item item;
	private static HasSong hassong;
	private static LeadSinger leadsinger;
	Login Login;
	
	public core() throws InterruptedException {
		
		Login = new Login ();
		
		while(true) {
			Thread.sleep (9000);
			if (Login.checkConnection()) {
				break;
			}
		}
		if (Login.checkConnection() == true) {
			connect = Login.getConnecton();
			try {
				
				item = new Item();
				hassong = new HasSong();
				leadsinger = new LeadSinger();
				
				// intialize point
				MenuScreen();
			} catch (ParseException perror) {
				// TODO Auto-generated catch block
				perror.printStackTrace();
			}
	}
	}
	
	
	
	
	
	private void MenuScreen () throws ParseException {
		int mode;
		boolean end;
		end = false;
		
		try {
			connect.setAutoCommit(false);
			
			while (!end){
				System.out.print("\n\n TESTING PURPOSES \n\n");
				System.out.print("1. Make every Tables \n");
				System.out.print("2. Drop every Tables \n");
				System.out.print("3. individual test cases \n");
				System.out.print("4. individual test cases \n");
				System.out.print("5. individual test cases \n");
				System.out.print("6. individual test cases \n");
				mode = Integer.parseInt(BR.readLine());
				System.out.println();
				
				switch (mode){
				case 1:
					AllTables();
					break;
				case 2:
					DAllTables();
					break;
				case 3:
					item.insertItem(connect, 1, "testtitle", "cd", "rock", "TestRecords", "2001", "25.25", 52);
					hassong.insertHasSong(connect, 1, "testtitleSong");
					leadsinger.insertLeadSinger(connect, 1, "testsinger");
					break;
				case 4: 
					item.showItem(connect);
					hassong.showHasSong(connect);
					leadsinger.showLeadSinger(connect);
					break;
				case 5:
					item.deleteItem(connect, 1);
					hassong.deleteHasSong(connect, 1);
					leadsinger.deleteLeadSinger(connect, 1);
					break;
				
				}
			}
			connect.close();
			BR.close();
			System.out.println("\n Done testing \n\n");
			System.exit(0);
		}catch (IOException error)
		{
			System.out.println ("IOExcept \n");
			
		}catch (SQLException error)
		{
			System.out.println ("SQLException \n");
			
		}
	}
	
	public Connection getConnect ()
	{
		return connect;
	}
	
	public static void main(String args[]) throws InterruptedException {
				MenuBar Menu = new MenuBar(connect);		
				core test = new core();
				//ClerkGUI clerk = new ClerkGUI(connect);		
				//Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				

	}
	
	private void AllTables() { /*create table function*/

		try { 
			item.createItem(connect);
			hassong.createHasSong(connect);
			leadsinger.createLeadSinger(connect);
		} catch (SQLException e) {
			System.out.println("Creating Tables failed");
		}
	}
	
	private void DAllTables(){
		try{
			item.dropItem(connect);
			hassong.dropHasSong(connect);
			leadsinger.dropLeadSinger(connect);
		}catch (SQLException error) {
			System.out.println ("drop table failed");
			
		}
	}
}