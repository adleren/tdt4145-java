package util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelpReader {

	public static final String HELP_FILE_PATH = "help.txt";

	public static String[] readHelpManual() {
		List<String> lines = new ArrayList<>();

		try {
			InputStream is = SQLReader.class.getClassLoader().getResourceAsStream(HELP_FILE_PATH);
			Scanner scanner = new Scanner(is);

			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
			
			scanner.close();

		} catch(Exception e) {
			e.printStackTrace();
		}

		String[] printable = new String[lines.size()];
		printable = lines.toArray(printable);

		return printable;
	}
}