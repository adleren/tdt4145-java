package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class DatabaseManager {

	public static final String PATH = "treningsdagbok.db";

	private Connection connection = null;

	public DatabaseManager() throws SQLException {
		String url = "jdbc:sqlite:" + DatabaseManager.PATH;

		this.connection = DriverManager.getConnection(url);

		this.createTables();
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void createTables() throws SQLException {
		List<String> queries = SQLReader.readQueriesFromFile(this.connection, new File("res/create_tables.sql"));

		queries.stream().forEach(query -> {
			System.out.println(query);

			try (Statement stmt = this.connection.createStatement()) {
				stmt.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

}