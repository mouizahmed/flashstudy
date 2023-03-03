package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	private static ResultSet rs1;
	private static String sql;
	private static String query1;

	public JDBC() {
		String url = "jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_957a5ec054245a7?reconnect=true";
		String user = "b94e112144658f";
		String password = "7af4eb5c";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				//initialize();
				System.out.println("Connected to the database");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
