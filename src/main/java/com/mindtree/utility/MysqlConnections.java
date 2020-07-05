package com.mindtree.utility;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class MysqlConnections {
	private static Connection myConnection;

	private MysqlConnections() {

	}

	public static Connection getConnections() throws ClassNotFoundException, SQLException {
		if (myConnection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/user_registration";
				String user = "root";
				String password = "Welcome123";
				myConnection = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				throw new ClassNotFoundException("Error in the connection. Try again after some time or else contact the administrator!!!");
				
			} catch (SQLException e) {
				throw new SQLException("Error in the connection. Try again after some time or else contact the administrator!!!");
				}
		}
		return myConnection;

	}
}
