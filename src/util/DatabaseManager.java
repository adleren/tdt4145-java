package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseManager {

	public static final String DB_PATH = "db2.db";
	public static final String CREATE_TABLES_FILE_PATH = "res/create_tables.sql";

	private Connection connection = null;

	public DatabaseManager() throws SQLException {
		String url = "jdbc:sqlite:" + DatabaseManager.DB_PATH;

		this.connection = DriverManager.getConnection(url);

		SQLReader.executeQueriesFromFile(this.connection, new File(CREATE_TABLES_FILE_PATH));
	}

	public Connection getConnection() {
		return this.connection;
	}

}