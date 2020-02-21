package table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

public class SQLTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 3758875113408104230L;
	
	private ResultSet rs;
	private String[] column;
	private Map<String, String> column_dictionary;
	
	public SQLTableModel(
		ResultSet rs,
		String[] column,
		Map<String, String> column_dictionary
	) {
		this.rs = rs;
		this.column = column;
		this.column_dictionary = column_dictionary;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
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
			try {
				rs.absolute(rowIndex+1);
				return rs.getObject(column[columnIndex]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
	}
	
	public void removeValue(int []indices) {
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
}