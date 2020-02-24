package table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import data.sql.AbstractSQLData;
import data.sql.resultset.InterfaceSetValueToPreparedStatement;

public class SQLTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 3758875113408104230L;
	
	private AbstractSQLData sqlData;
	private String[] column;
	private Map<String, String> column_dictionary;
	
	public SQLTableModel(
		AbstractSQLData sqlData,
		String[] column,
		Map<String, String> column_dictionary
	) {
		this.sqlData = sqlData;
		this.column = column;
		this.column_dictionary = column_dictionary;
	}
	
	@Override
	public int getRowCount() {
		try {
			sqlData.getResultSet().last();
			return sqlData.getResultSet().getRow();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public String getColumnName(int index) {
		if (column_dictionary != null) {
			return column_dictionary.get(column[index]);
		} else {
			return column[index];
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ResultSet rs = sqlData.getResultSet();
		
		try {
			rs.absolute(rowIndex+1);
			return rs.getObject(column[columnIndex]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void removeValue(int []indices) {
		ResultSet rs = sqlData.getResultSet();
		
		for (int i=indices.length-1; i >= 0; i--) {
			int index = indices[i];
			try {
				rs.absolute(1+index);
				rs.deleteRow();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addValue(InterfaceSetValueToPreparedStatement svtps) {
		PreparedStatement ps = sqlData.getPreparedStatement();
		
		if (ps != null) {
			try {
				svtps.setValueToResultSet(ps);
				ps.executeUpdate();
				sqlData.freeResultSet();
				this.fireTableDataChanged();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}