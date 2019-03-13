package app;

import java.sql.*;

/**
 * DatabaseManager
 */
public class DatabaseManager {

	private Connection connection = null;

	public final String HOST = "mysql.stud.ntnu.no";
	public final String USER = "magnram_db_acc";
	public final String PASSWORD = "db_acc";
	public final String NAME = "magnram_db";


	public DatabaseManager() throws SQLException {
		this.connection = DriverManager.getConnection("jdbc:mysql://" + this.HOST + "/" + this.NAME + "?" + "user=" + this.USER + "&password=" + this.PASSWORD);

		System.out.println("Connected to db!");
	}


	public Connection getConnection() {
		return this.connection;
	}


	public static void main(String args[]) {

	}
}