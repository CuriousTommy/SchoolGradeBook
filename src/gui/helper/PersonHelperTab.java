package gui.helper;

import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data.sql.SQLCommon;
import data.sql.sqldata.SQLPersonData;
import table.SQLTableModel;

public class PersonHelperTab extends JPanel {
	public JTable table;
	public String identifier;
	public SQLPersonData sqlData;
	
	public PersonHelperTab(String indentifier, SQLPersonData sqlData) {
		this.table = new JTable();
		this.identifier = indentifier;
		this.sqlData = sqlData;
		
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
		
		this.table.setModel(new SQLTableModel(sqlData, column, null));
	}
	
	public SQLTableModel getTableModel() {
		return (SQLTableModel) table.getModel();
	}
	
}