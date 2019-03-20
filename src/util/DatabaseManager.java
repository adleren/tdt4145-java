package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseManager {

	public static final String PATH = "treningsdagbok.db";

	private Connection connection = null;

	public DatabaseManager() throws SQLException {
		String url = "jdbc:sqlite:" + DatabaseManager.PATH;

		this.connection = DriverManager.getConnection(url);

		SQLReader.executeQueriesFromFile(this.connection, new File("res/create_tables.sql"));
	}

	public Connection getConnection() {
		return this.connection;
	}

}