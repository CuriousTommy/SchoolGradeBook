package gui;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

import data.sql.SQLCommon;
import data.sql.sqldata.SQLPersonData;
import gui.helper.PersonAddUI;
import gui.helper.PersonHelperTab;
import table.SQLTableModel;

public class PersonGUI extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = -3838057608060564412L;

	JTabbedPane tabbedPanel;
	JButton addButton;
	JButton removeButton;
	
	GridBagConstraints gbc;
	ArrayList<SQLPersonData> sqlDataList;
	
	public PersonGUI(Connection conn) {
		setSize(500,400);
		setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		sqlDataList = new ArrayList<>();
		setupTabbedPanel(conn);
		setupButtonOptions();
		
		setVisible(true);
	}
	
	private void setupTabbedPanel(Connection conn) {
		tabbedPanel = new JTabbedPane();
		
		tabbedPanel.addTab("Student", setupTabbedComponent(SQLCommon.STUDENT, conn));
		tabbedPanel.addTab("Facility", setupTabbedComponent(SQLCommon.FACILITY, conn));
		tabbedPanel.addTab("Staff", setupTabbedComponent(SQLCommon.STAFF, conn));
		
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
	
	private Component setupTabbedComponent(String indentifier, Connection conn) {
		SQLPersonData sqlData = new SQLPersonData(conn, indentifier);
		return new PersonHelperTab(indentifier, sqlData);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			if (tabbedPanel.getSelectedComponent() instanceof PersonHelperTab) {
				PersonHelperTab pht = (PersonHelperTab) tabbedPanel.getSelectedComponent();
				new PersonAddUI(this, pht).setVisible(true);
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

	@Override
	public void windowClosing(WindowEvent e) {
		for (SQLPersonData sqlData: sqlDataList) {
			sqlData.close();
		}
		
		dispose();
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}