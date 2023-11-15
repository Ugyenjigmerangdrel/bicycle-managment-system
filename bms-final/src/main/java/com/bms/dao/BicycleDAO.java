package com.bms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bms.model.Bicycle;
import com.bms.util.DBUtil;

public class BicycleDAO {
	private static final String INSERT_BC_SQL = "INSERT INTO bms_bicycle" + "  (bicycle_no, cuuid, suuid) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_BC_BY_ID = "select id,bicycle_no,cuuid,suuid from bms_bicycle where id =?";
	private static final String SELECT_ALL_BC = "select * from bms_bicycle ";
	private static final String DELETE_BC_SQL = "delete from bms_bicycle  where id = ?;";
	private static final String UPDATE_BC_SQL = "update bms_bicycle  set bicycle_no = ?,cuuid= ?, suuid=? where id = ?;";

	public BicycleDAO() {

	}

	public void insertBicycle(Bicycle bicycle) throws SQLException {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_BC_SQL)) {
			pstmt.setInt(1, bicycle.getBicycle_no());
			pstmt.setString(2, bicycle.getCUUID()); // Use index 2 for email
			pstmt.setString(3, bicycle.getSUUID()); // Use index 3 for country
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Bicycle selectBicycle(int id) {
		Bicycle bicycle = null;
		// Step 1: Establishing a Connection
		try (Connection connection = DBUtil.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BC_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int bicycle_no = Integer.parseInt(rs.getString("bicycle_no"));
				String cuuid = rs.getString("cuuid");
				String suuid = rs.getString("suuid");
				bicycle = new Bicycle(id, bicycle_no, cuuid, suuid);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return bicycle;
	}

	public List<Bicycle> selectAllBicycles() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Bicycle> bicycles = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = DBUtil.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BC);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				int bicycle_no = Integer.parseInt(rs.getString("bicycle_no"));
				String cuuid = rs.getString("cuuid");
				String suuid = rs.getString("suuid");
				bicycles.add(new Bicycle(id, bicycle_no, cuuid, suuid));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return bicycles;
	}

	public boolean deleteBicycle(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_BC_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateBicycle(Bicycle bicycle) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_BC_SQL);) {
			statement.setInt(1, bicycle.getBicycle_no());
			statement.setString(2, bicycle.getCUUID());
			statement.setString(3, bicycle.getSUUID());
			statement.setInt(4, bicycle.getId());

			rowUpdated = statement.executeUpdate() > 0;
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
