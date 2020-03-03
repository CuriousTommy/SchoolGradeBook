package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = -6508626185123863757L;
	
	private String address;
	private String username;
	private String password;
	
	private JButton buttonPersonGUI;
	private JButton buttonGradeGUI;
	private JButton buttonClassGUI;
	
	private Connection con;
	
	
	public MainGUI(String address, String username, String password) {
		setSize(175, 150);
		
		this.address = address;
		this.username = username;
		this.password = password;
		
		buttonPersonGUI = createNewButton("Manage People");
		buttonGradeGUI = createNewButton("Manage Grades");
		buttonClassGUI = createNewButton("Manage Class");
		
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setVisible(true);
	}
	
	
	private JButton createNewButton(String name) {
		JButton button = new JButton(name);
		button.addActionListener(this);
		add(button);
		return button;
	}
	
	private Connection createConnection() {
		if (con == null) {
			try {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(
					address, username, password
				);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}

		
		return con;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonPersonGUI) {
			activatePersonGUI();
		} else if (e.getSource() == buttonGradeGUI) {
			activateGradeGUI();
		} else if (e.getSource() == buttonClassGUI) {
			activateClassGUI();
		}
	}
	
	
	private void activatePersonGUI() {
		new PersonGUI(createConnection());
	}
	
	private void activateGradeGUI() {
		
	}
	
	private void activateClassGUI() {
		new ClassGUI(createConnection());
	}
}
