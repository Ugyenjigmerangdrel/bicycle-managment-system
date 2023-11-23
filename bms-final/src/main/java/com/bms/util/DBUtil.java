package com.bms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String jdbcURL = "jdbc:mysql://mysqlsynergy.mysql.database.azure.com:3306/synergy?useSSL=true";
	//private static String jdbcURL = "jdbc:mysql://localhost:3306/synergy";
	private static String jdbcUsername = "synergy_admin";
	private static String jdbcPassword = "workshop#123";
	
    public static Connection getConnection() throws SQLException {
    	Connection connection = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	        System.out.println("Connected to Database.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("SQLException: " + e.getMessage());
	    } catch(ClassNotFoundException e) {
	    	e.printStackTrace();
	    	System.out.println("ClassException: " + e.getMessage());
	    }
	    return connection;
    }
}
