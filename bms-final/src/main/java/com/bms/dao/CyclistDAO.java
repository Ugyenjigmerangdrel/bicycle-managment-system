package com.bms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.bms.model.Cyclist;
import com.bms.util.DBUtil;

public class CyclistDAO {
	private static final String INSERT_CY_SQL = "INSERT INTO bms_cyclist" + "  (name, email, contact, card_id, enrolment_number, photo) VALUES "
			+ " (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_CY_BY_ID = "select id,name, email, contact, card_id, enrolment_number, photo from bms_cyclist where card_id =?";

	private static final String SELECT_ALL_CY = "select * from bms_cyclist ";
	private static final String DELETE_CY_SQL = "delete from bms_cyclist  where id = ?;";
	private static final String UPDATE_CY_SQL = "update bms_cyclist  set name = ?,email= ?, contact=?, card_id=?, enrolment_number=?, photo=? where id = ?;";

	public CyclistDAO() {

	}

	public void insertCyclist(Cyclist cyclist) throws SQLException {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_CY_SQL)) {
			pstmt.setString(1, cyclist.getName());
			pstmt.setString(2, cyclist.getEmail()); 
			pstmt.setString(3, cyclist.getContact()); 
			pstmt.setString(4, cyclist.getCard_id()); 
			pstmt.setString(5, cyclist.getEnrolment_number()); 
			pstmt.setString(6, cyclist.getPhoto()); 
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cyclist selectCyclist(String card_id) {
		Cyclist cyclist = null;
		// Step 1: Establishing a Connection
		try (Connection connection = DBUtil.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CY_BY_ID);) {
			preparedStatement.setString(1, card_id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String contact = rs.getString("contact");
				String enrolment_number = rs.getString("enrolment_number");
				String photo = rs.getString("photo");
				cyclist = new Cyclist(id, name, email, contact, card_id, enrolment_number, photo);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return cyclist;
	}

	public List<Cyclist> selectAllCyclist() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Cyclist> cyclists = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = DBUtil.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CY);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String contact = rs.getString("contact");
				String card_id = rs.getString("card_id");
				String enrolment_number = rs.getString("enrolment_number");
				String photo = rs.getString("photo");
				
				cyclists.add(new Cyclist(id, name, email, contact, card_id, enrolment_number, photo));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return cyclists;
	}

	public boolean deleteCyclist(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CY_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateCyclist(Cyclist cyclist) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_CY_SQL);) {
			pstmt.setString(1, cyclist.getName());
			pstmt.setString(2, cyclist.getEmail()); 
			pstmt.setString(3, cyclist.getContact()); 
			pstmt.setString(4, cyclist.getCard_id()); 
			pstmt.setString(5, cyclist.getEnrolment_number()); 
			pstmt.setString(6, cyclist.getPhoto()); 
			pstmt.setInt(7, cyclist.getId()); 
			rowUpdated = pstmt.executeUpdate() > 0;
		}
		return rowUpdated;
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
