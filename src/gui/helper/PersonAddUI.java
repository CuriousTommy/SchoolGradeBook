package gui.helper;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import data.sql.resultset.NewPersonResultSet;
import table.SQLTableModel;

public class PersonAddUI extends JDialog implements ActionListener {
	private static final long serialVersionUID = -5702228510239913992L;
	
	private JTextField firstName;
	private JTextField middleName;
	private JTextField lastName;
	
	private JButton buttonAdd;
	private JButton buttonCancel;
	
	private PersonHelperTab helperTab;
	
	GridBagConstraints c = new GridBagConstraints();
	
	
	public PersonAddUI(Frame owner, PersonHelperTab pht) {
		super(
			owner,
			"Add New Person",
			Dialog.ModalityType.APPLICATION_MODAL
		);
		
		helperTab = pht;
		
		this.setMinimumSize(new Dimension(200,135));
		setSize(250,135);
		this.setLayout(new BorderLayout());
		addPersonFrame();
		addButtonOptions();
	}
	
	
	private void addPersonFrame() {
		JPanel frame = new JPanel();
		frame.setLayout(new GridBagLayout());
		
		firstName = new JTextField();
		middleName = new JTextField();
		lastName = new JTextField();
		
		Component[] items = {
			firstName,
			middleName,
			lastName
		};
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 14;
		
		c.fill = GridBagConstraints.HORIZONTAL;
		for (Component item: items) {
			frame.add(item, c);
			c.gridy++;
		}
		
		items = new Component[] {
			new JLabel("First Name: "),
			new JLabel("Middle Name: "),
			new JLabel("Last Name: "),
		};
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		for (Component item: items) {
			frame.add(item, c);
			c.gridy++;
		}
		
		add(frame, BorderLayout.CENTER);
	}
	
	
	public void addButtonOptions() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		buttonAdd = new JButton("Add");
		buttonCancel = new JButton("Cancel");
		
		JButton[] items = {
			buttonAdd,
			buttonCancel
		};
		
		for (JButton item: items) {
			item.addActionListener(this);
			panel.add(item);
		}
		
		add(panel, BorderLayout.PAGE_END);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAdd) {
			SQLTableModel tableModel = helperTab.getTableModel();
			NewPersonResultSet nprs = new NewPersonResultSet(
				firstName.getText(),
				!middleName.getText().contentEquals("") ? middleName.getText() : null,
				lastName.getText()
			);
			
			tableModel.addValue(nprs);
			this.dispose();
			
		} else if (e.getSource() == buttonCancel) {
			this.dispose();
		}
	}
}
