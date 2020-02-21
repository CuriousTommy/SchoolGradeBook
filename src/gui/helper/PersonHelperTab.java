package gui.helper;

import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data.sql.SQLCommon;
import data.sql.SQLPersonData;
import table.SQLTableModel;

public class PersonHelperTab extends JPanel {	
	public JTable table;
	public String identifier;
	
	public PersonHelperTab(String indentifier, SQLPersonData sqlData) {
		this.table = new JTable();
		this.identifier = indentifier;
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		initalizeTableModel(sqlData);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		add(scrollPane);
	}
	
	private void initalizeTableModel(SQLPersonData sqlData) {
		ResultSet rs = null;
		String[] column = { 
				"id",
				"first_name",
				"middle_name",
				"last_name"
		};
		
		if (identifier.compareTo(SQLCommon.STUDENT) == 0) {
			rs = sqlData.getStudentData();
		
		} else if (identifier.compareTo(SQLCommon.FACILITY) == 0) {
			rs = sqlData.getFacilityData();
			
		} else if (identifier.compareTo(SQLCommon.STAFF) == 0) {
			rs = sqlData.getStaffData();
		
		} else {
			throw new IllegalArgumentException("Invalid identifier provided");
		}

		this.table.setModel(new SQLTableModel(rs, column, null));
	}
	
	public SQLTableModel getTableModel() {
		return (SQLTableModel) table.getModel();
	}
	
}