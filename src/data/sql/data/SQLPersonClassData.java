package data.sql.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.sql.AbstractSQLData;
import data.sql.item.Term;

public class SQLPersonClassData extends AbstractSQLData {
	private Term term;
	private long class_id;

	public SQLPersonClassData(Connection conn, Term term, long class_id) {
		super(conn);
		this.term = term;
		this.class_id = class_id;
	}

	@Override
	protected PreparedStatement generatePreparedStatement() {
		return null;
	}

	@Override
	protected ResultSet generateResultSet() {
		CallableStatement cs;
		try {
			cs = conn.prepareCall(
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
	
	private String generateSQLStatementForResultSet() {
		return String.format(
			"SELECT p.first_name, p.middle_name, p.last_name FROM personclass pc " +
			"JOIN class c ON pc.class_id=c.id AND c.term=%d AND c.id=%d " +
			"JOIN person p ON pc.person_id=p.id",
			
			term.getId(), class_id);
	}
	
	public void updateTerm(Term term, long class_id) {
		this.term = term;
		updateClass(class_id);
	}
	
	public void updateClass(long class_id) {
		this.class_id = class_id;
		updateResultSet();
	}
}
