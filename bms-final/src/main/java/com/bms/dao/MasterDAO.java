package com.bms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bms.model.Master;
import com.bms.util.DBUtil;

public class MasterDAO {
	private static final String SELECT_CY_BY_ID = "select * from bms_master where transaction_id =?";
	private static final String SELECT_ALL_CY = "select * from bms_master where connection_status=?";
	private static final String SELECT_ALL_AC = "select * from bms_master where connection_status=?";

	public MasterDAO() {

	}

	public Master selectMaster(String transactionId) {
		Master master = null;
		// Step 1: Establishing a Connection
		try (Connection connection = DBUtil.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CY_BY_ID);) {
			preparedStatement.setString(1, transactionId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String transaction_id = rs.getString("transaction_id");
				String used_by = rs.getString("used_by");
				String user_id = rs.getString("user_id");
				String fromtime = rs.getString("from_time");
				String totime = rs.getString("totime");
				String cycle_no = rs.getString("cycle_no");
				String connection_status = rs.getString("connection_status");
				master = new Master(transaction_id, connection_status, cycle_no, fromtime, totime,
						used_by, user_id);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return master;
	}

	public List<Master> selectAllMaster() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Master> masters = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = DBUtil.getConnection();
				
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CY);) {
			preparedStatement.setString(1, "parked");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String transaction_id = rs.getString("transaction_id");
				String used_by = rs.getString("used_by");
				String user_id = rs.getString("user_id");
				String fromtime = rs.getString("from_time");
				String totime = rs.getString("to_time");
				String cycle_no = rs.getString("cycle_no");
				String connection_status = rs.getString("connection_status");
				
				
				masters.add(new Master(transaction_id, connection_status, cycle_no, fromtime, totime,
						used_by, user_id));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return masters;
	}
	
	public List<Master> selectAllActive() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Master> masters = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = DBUtil.getConnection();
				
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AC);) {
			preparedStatement.setString(1, "active");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String transaction_id = rs.getString("transaction_id");
				String used_by = rs.getString("used_by");
				String user_id = rs.getString("user_id");
				String fromtime = rs.getString("from_time");
				String totime = rs.getString("to_time");
				String cycle_no = rs.getString("cycle_no");
				String connection_status = rs.getString("connection_status");
				
				
				masters.add(new Master(transaction_id, connection_status, cycle_no, fromtime, totime,
						used_by, user_id));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return masters;
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





