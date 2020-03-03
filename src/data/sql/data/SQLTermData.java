package data.sql.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import data.sql.AbstractSQLData;
import data.sql.item.Term;

public class SQLTermData extends AbstractSQLData {

	public SQLTermData(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
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

	private String generateSQLStatementForResultSet() {
		return "SELECT * FROM term LIMIT 400";
	}
	
	public Vector<Term> generateTermItems() {
		ResultSet rs = this.getResultSet();
		Vector<Term> terms = new Vector<>();
		
		try {
			if (rs.first()) {
				do {
					terms.add(new Term(rs));
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return terms;
	}
}
