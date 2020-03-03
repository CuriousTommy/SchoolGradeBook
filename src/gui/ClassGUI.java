package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.sql.SQLCommon;
import data.sql.data.SQLClassData;
import data.sql.data.SQLPersonClassData;
import data.sql.data.SQLPersonData;
import data.sql.data.SQLTermData;
import data.sql.item.Term;
import generic.helper.GBC;
import gui.sqlmodel.SQLTableModel;

public class ClassGUI extends JFrame implements ActionListener, ListSelectionListener {
	private static final long serialVersionUID = 8938966658406212582L;
	
	private Connection conn;
	private SQLTermData termSQL;
	private SQLPersonClassData personClassSQL;
	private SQLClassData classSQL;
	
	private JComboBox<Term> schoolTermComboBox;
	private JTable personClassTable;
	private JTable classTable;
	
	JScrollPane scrollPane;
	
	GridBagConstraints gbc;
	
	
	public ClassGUI(Connection conn) {
		setSize(500,400);
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		
		this.conn = conn;
		
		schoolTermComboBox = generateSeasonComboBox();
		classTable = generateClassTable();
		personClassTable = generatePeopleTable();
		
		setVisible(true);
	}
	
	
	/*
	 *	This section is responsible for generating the GUI Components
	 */

	
	private JComboBox<Term> generateSeasonComboBox() {
		this.termSQL = new SQLTermData(conn);
		
		JComboBox<Term> comboBox = new JComboBox<Term>(termSQL.generateTermItems());
		comboBox.addActionListener(this);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		GBC.changeWeight(gbc, 0, 0);
		GBC.changeGrid(gbc, 0, 0);
		GBC.changeGridDimension(gbc, 4, 1);
		add(comboBox, gbc);
		return comboBox;
	}
	
	
	private JTable generatePeopleTable() {
		this.personClassSQL = new SQLPersonClassData(
			conn,
			(Term) schoolTermComboBox.getModel().getSelectedItem(),
			getClassID(0)
		);
		
		String[] columns = {
			SQLCommon.Person.FIRST_NAME,
			SQLCommon.Person.MIDDLE_NAME,
			SQLCommon.Person.LAST_NAME
		};
		
		JTable table = new JTable(
			new SQLTableModel(personClassSQL, columns, null)
		);
		scrollPane = new JScrollPane(table);
		
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		GBC.changeWeight(gbc, 0.5, 1);
		GBC.changeGrid(gbc, 1, 1);
		GBC.changeGridDimension(gbc, 1, 2);
		table.setFillsViewportHeight(true);
		add(scrollPane, gbc);
		return table;
	}
	
	
	private JTable generateClassTable() {
		this.classSQL = new SQLClassData(
			conn,
			((Term)this.schoolTermComboBox.getSelectedItem())
		);
		
		String[] columns = {
			SQLCommon.Class.ID,
			SQLCommon.Class.NAME,
			SQLCommon.Class.ROOM_NUMBER,
			SQLCommon.Class.PERIOD
		};
		
		JTable table = new JTable(
			new SQLTableModel(classSQL, columns, null)
		);
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.getSelectionModel().addListSelectionListener(this);
		
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		GBC.changeWeight(gbc, 0.5, 1);
		GBC.changeGrid(gbc, 0, 1);
		GBC.changeGridDimension(gbc, 1, 2);
		table.setFillsViewportHeight(true);
		add(scrollPane, gbc);
		return table;
	}
	
	
	/*
	 * Event Listener Functions
	 */
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == schoolTermComboBox) {
			Term currentItem = (Term) schoolTermComboBox.getModel().getSelectedItem();
			SQLTableModel tableModel;
			
			tableModel = (SQLTableModel) classTable.getModel();
			classSQL.updateTerm(currentItem);
			tableModel.fireTableDataChanged();
			
			tableModel = (SQLTableModel) personClassTable.getModel();
			personClassSQL.updateTerm(currentItem, getClassID(0));
			tableModel.fireTableDataChanged();
		}
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			System.out.println("valueChanged()");
			if (e.getSource() instanceof DefaultListSelectionModel) {
				DefaultListSelectionModel dlsm = (DefaultListSelectionModel) e.getSource();
				int firstIndex = dlsm.getMinSelectionIndex();
				
				// TODO: Come up with a better solution, I am not a fan of how
				// currently works.
				//
				// Consider providing a public method for SQLTableModel to request
				// a column using a string
				
				if (firstIndex != -1) {
					long class_id = getClassID(firstIndex);
					
					System.out.println(class_id);
					
					personClassSQL.updateClass(class_id);
					SQLTableModel tableModel = (SQLTableModel) personClassTable.getModel();
					tableModel.fireTableDataChanged();
				}
			}
		}
	}
	
	
	public long getClassID(int index) {
		return (long) classTable.getModel().getValueAt(index, 0);
	}
}
