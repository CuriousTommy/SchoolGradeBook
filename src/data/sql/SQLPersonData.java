package data.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPersonData {
	private Connection connection;
	private ResultSet studentData = null;
	private ResultSet facilityData = null;
	private ResultSet staffData = null;
	
	public SQLPersonData(Connection connection) {
		this.connection = connection;
	}
	
	private String generateSQLStatement(String rank) {
		return String.format(
			"SELECT * FROM person WHERE rank='%s'",
			rank);
	}
	
	private ResultSet generateResultSet(String rank) {
		try {
			CallableStatement cs = connection.prepareCall(
					generateSQLStatement(rank),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
			);
			return studentData = cs.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ResultSet getStudentData() {
		if (studentData == null) {	
			studentData = generateResultSet(SQLCommon.STUDENT);
		}
		
		return studentData;
	}
	
	public ResultSet getFacilityData() {
		if (facilityData == null) {
			facilityData = generateResultSet(SQLCommon.FACILITY);
		}
		
		return facilityData;
	}
	
	public ResultSet getStaffData() {
		if (staffData == null) {
			staffData = generateResultSet(SQLCommon.STAFF);
		}
		
		return staffData;
	}
	
	public void close() {
		if (studentData != null) {
			try {
				studentData.close();
				studentData = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (facilityData != null) {
			try {
				facilityData.close();
				facilityData = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (staffData != null) {
			try {
				staffData.close();
				staffData = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
