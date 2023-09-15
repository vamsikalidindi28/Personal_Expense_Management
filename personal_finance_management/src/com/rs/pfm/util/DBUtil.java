package com.rs.pfm.util;   

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public static Connection getConnection() {
		Connection connection = null;
		// 1. Register the driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. get the connection

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pfm?useSSL=false", "root",
					"root");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
			return connection;
		}
		public static void closeConnection(Connection connection) {                
			try
			{
				connection.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}
			
			
		}
	}
