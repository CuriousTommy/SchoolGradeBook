package data.sql;

import java.sql.ResultSet;

public abstract class AbstractSQLItem {	
	public AbstractSQLItem(ResultSet rs) { 
		initalizeValuesFrom(rs);
	}
	
	abstract protected void initalizeValuesFrom(ResultSet rs);
}
