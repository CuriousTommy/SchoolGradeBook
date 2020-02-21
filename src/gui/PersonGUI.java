package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

import data.sql.SQLCommon;
import data.sql.SQLPersonData;
import gui.helper.PersonHelperTab;
import table.SQLTableModel;

public class PersonGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = -3838057608060564412L;
	
	JTabbedPane tabbedPanel;
	JButton addButton;
	JButton removeButton;
	
	GridBagConstraints gbc;
	SQLPersonData sqlData;
	
	public PersonGUI(Connection connection) {
		setSize(500,400);
		setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		sqlData = new SQLPersonData(connection);
		setupTabbedPanel();
		setupButtonOptions();
		
		setVisible(true);
	}
	
	private void setupTabbedPanel() {
		tabbedPanel = new JTabbedPane();
		
		tabbedPanel.addTab("Student", setupTabbedComponent(SQLCommon.STUDENT));
		tabbedPanel.addTab("Facility", setupTabbedComponent(SQLCommon.FACILITY));
		tabbedPanel.addTab("Staff", setupTabbedComponent(SQLCommon.STAFF));
		
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.weightx = 1; gbc.weighty = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		add(tabbedPanel, gbc);
	}
	
	private void setupButtonOptions() {
		gbc.gridy = 1;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.weightx = 0; gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		
		addButton =  new JButton("Add");
		addButton.addActionListener(this);
		gbc.gridx = 0;
		add(addButton, gbc);
		
		removeButton = new JButton("Remove");
		removeButton.addActionListener(this);
		gbc.gridx = 1;
		add(removeButton, gbc);
	}
	
	private Component setupTabbedComponent(String indentifier) {
		return new PersonHelperTab(indentifier, sqlData);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			if (tabbedPanel.getSelectedComponent() instanceof PersonHelperTab) {
				PersonHelperTab pht = (PersonHelperTab) tabbedPanel.getSelectedComponent();
				System.out.println(pht.identifier);
			}

		} else if (e.getSource() == removeButton) {
			if (tabbedPanel.getSelectedComponent() instanceof PersonHelperTab) {
				PersonHelperTab pht = (PersonHelperTab) tabbedPanel.getSelectedComponent();
				int[] rows = pht.table.getSelectedRows();
				SQLTableModel tableModel = pht.getTableModel();
				
				tableModel.removeValue(rows);
			}
		
		} else {
			System.out.println("Invalid option selected");
		}
		
	}
}