package com.bms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String jdbcURL = "jdbc:mysql://YOUR _DATABASE_URL";
	private static String jdbcUsername = "USERNAME";
	private static String jdbcPassword = "PASSWORD";
	
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
