package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SQLReader {

	public static List<String> readQueriesFromFile(Connection connection, String filename) {
		List<String> queries = new ArrayList<>();

		try {
			InputStream is = SQLReader.class.getClassLoader().getResourceAsStream(filename);
			Scanner scanner = new Scanner(is);

			scanner.useDelimiter(";");

			while (scanner.hasNext()) {
				queries.add(scanner.next().trim() + ";");
			}
			
			scanner.close();

		} catch(Exception e) {
			e.printStackTrace();
		}

		return queries;
	}

	public static boolean executeQueriesFromFile(Connection connection, String filename) {
		List<String> queries = SQLReader.readQueriesFromFile(connection, filename);

		for (String query : queries) {
			try (Statement stmt = connection.createStatement()) {
				stmt.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}
}