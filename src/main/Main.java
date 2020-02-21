package main;
import gui.PersonGUI;

import java.sql.*;

import data.sql.SQLPersonData;

public class Main {
	static public void main(String[] args) throws SQLException, ClassNotFoundException {
		if (args.length != 3) {
			System.out.println("There must be exactly three arguments. "
					+ "Address, Username, and Password.");
		}
		
		String address = String.format("jdbc:postgresql://%s", args[0]);
		String username = args[1];
		String password = args[2];
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					address,
					username, password
			);
			
			new PersonGUI(con);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}