package main;

import gui.MainGUI;
import java.sql.*;

public class Main {
	static public void main(String[] args) throws SQLException, ClassNotFoundException {
		if (args.length != 3) {
			System.out.println("There must be exactly three arguments. "
					+ "Address, Username, and Password.");
		}
		
		String address = String.format("jdbc:postgresql://%s", args[0]);
		String username = args[1];
		String password = args[2];
		
		new MainGUI(address, username, password);
	}
}