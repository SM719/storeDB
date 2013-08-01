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
	
}