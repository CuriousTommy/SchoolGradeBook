package data.sql.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.sql.AbstractSQLData;

public class SQLPersonData extends AbstractSQLData {
	String rank;
	
	public SQLPersonData(Connection con, String rank) {
		super(con);
		this.rank = rank;
	}
	
	private String generateSQLStatementForResultSet() {
		return String.format(
			"SELECT * FROM person WHERE rank='%s'",
			rank);
	}
	
	private String generateSQLStatementForPrepareStatement() {
		return String.format(
			"INSERT INTO person (%s) VALUES (%s)",
			"rank, first_name, middle_name, last_name",
			"'"+rank+"'::rank, ?, ?, ?"
		);
	}

	@Override
	protected PreparedStatement generatePreparedStatement() {
		try {
			return conn.prepareStatement(
					generateSQLStatementForPrepareStatement(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected ResultSet generateResultSet() {
		try {
			CallableStatement cs = conn.prepareCall(
					generateSQLStatementForResultSet(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE
			);
			return cs.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
