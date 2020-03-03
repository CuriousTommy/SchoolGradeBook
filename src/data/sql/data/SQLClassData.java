package data.sql.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.sql.AbstractSQLData;
import data.sql.SQLCommon;
import data.sql.item.Term;

public class SQLClassData extends AbstractSQLData {
	Term term;
	
	public SQLClassData(Connection conn, Term term) {
		super(conn);
		this.term = term;
	}
	
	private String generateSQLStatementForResultSet() {
		return String.format("SELECT * FROM class WHERE %s=%d",
			SQLCommon.Class.TERM, term.getId()
		);
	}
	
	@Override
	protected PreparedStatement generatePreparedStatement() {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateTerm(Term term) {
		this.term = term;
		updateResultSet();
	}
}
