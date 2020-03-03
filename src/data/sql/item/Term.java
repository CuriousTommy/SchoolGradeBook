package data.sql.item;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.sql.AbstractSQLItem;
import data.sql.SQLCommon;

public class Term extends AbstractSQLItem {
	@SuppressWarnings("unused")
	private long id;
	private String term;
	
	public Term(ResultSet rs) {
		super(rs);
	}

	@Override
	protected void initalizeValuesFrom(ResultSet rs) {
		try {
			id = rs.getLong(SQLCommon.Term.ID);
			term = rs.getString(SQLCommon.Term.TERM);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return term;
	}
	
	public long getId() {
		return id;
	}
}
