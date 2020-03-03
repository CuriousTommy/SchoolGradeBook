package data.sql.resultset;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface InterfaceSetValueToPreparedStatement {
	public void setValueToPreparedStatement(PreparedStatement ps) throws SQLException;
}
