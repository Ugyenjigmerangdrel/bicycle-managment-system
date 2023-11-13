package p1;

import java.sql.*;

public class Connect {
	 public static void main(String[] args) {
	        // JDBC URL, username, and password of MySQL server
	        String url = "jdbc:mysql://localhost:3306/synergy";
	        String user = "root";
	        String password = "";

	        try {
	            // Register JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Open a connection
	            System.out.println("Connecting to database...");
	            Connection connection = DriverManager.getConnection(url, user, password);

	            // Perform database operations here...
	            if(connection != null) {
	            	System.out.print("Connected");
	            }
	            // Close the connection
	            connection.close();
	            System.out.println("Connection closed.");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
}



