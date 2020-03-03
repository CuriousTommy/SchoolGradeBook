package data.sql.resultset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import data.sql.SQLCommon;

public class NewPersonIndex implements InterfaceSetValueToPreparedStatement {
	static public final String RANK = "rank";
	static public final String FIRST_NAME = "first_name";
	static public final String MIDDLE_NAME = "middle_name";
	static public final String LAST_NAME = "last_name";
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	public NewPersonIndex(
		String firstName,
		String middleName,
		String lastName
	) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	public NewPersonIndex(
		String firstName,
		String lastName
	) {
		this(firstName, null, lastName);
	}

	@Override
	public void setValueToPreparedStatement(PreparedStatement rs) throws SQLException {
		rs.setString(1, firstName);
		rs.setString(3, lastName);
		
		if (middleName == null) {
			rs.setString(2, middleName);
		} else {
			rs.setNull(2, Types.VARCHAR);
		}
	}
}
