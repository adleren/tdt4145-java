package util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SQLReader {

	public static List<String> readQueriesFromFile(Connection connection, File file) {
		List<String> queries = new ArrayList<>();

		try (Scanner scanner = new Scanner(file)) {
			
			scanner.useDelimiter(";");

			while (scanner.hasNext()) {
				queries.add(scanner.next().trim() + ";");
			}

		} catch(IOException e) {
			e.printStackTrace();
		}

		return queries;
	}
}