package com.util;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException;



public class DBUtil {

	private static final String URL = "jdbc:mysql://127.0.0.1/db_demo?useSSL=false";

	private static final String USER="root";

	private static final String PASSWORD="12345678";

	private  static Connection connection=null;
	
	public static Connection getConnection() {
		return connection; 
	} 
	
	static{
		try { 
			 
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch(SQLException e){
				e.printStackTrace();			
		}
	}	
}
