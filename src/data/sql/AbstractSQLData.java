package data.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractSQLData {
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected Connection conn;
	
	protected AbstractSQLData(Connection conn) {
		this.conn = conn;
	}
	
	abstract protected PreparedStatement generatePreparedStatement();
	abstract protected ResultSet generateResultSet();
	
	public PreparedStatement getPreparedStatement() {
		if (ps == null) {
			ps = generatePreparedStatement();
		}
		
		return ps;
	}
	
	public ResultSet getResultSet() {
		if (rs == null) {
			rs = generateResultSet();
		}
		
		return rs;
	}
	
	private void freeResultSet() {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void updateResultSet() {
		freeResultSet();
		getResultSet();
	}
	
	public void close() {
		if (ps != null) {
			try {
				ps.close();
				ps = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		freeResultSet();
	}
}
