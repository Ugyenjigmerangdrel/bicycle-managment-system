package com.bms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bms.model.Bicycle;
import com.bms.model.User;
import com.bms.util.DBUtil;

public class UserDAO {
	private static final String SELECT_BC_BY_USERNAME = "select name,password,role from users where name =?";
	public UserDAO() {
		
	}
	public User getUserByUsername(String uname) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = DBUtil.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BC_BY_USERNAME);) {
			preparedStatement.setString(1, uname);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String role = rs.getString("role");
				user = new User(name, password, role);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
