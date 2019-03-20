package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelpReader {

	public static final String HELP_FILE_PATH = "res/help.txt";

	public static String[] readHelpManual() {
		List<String> lines = new ArrayList<>();

		try (Scanner scanner = new Scanner(new File(HELP_FILE_PATH))) {

			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}

		} catch(IOException e) {
			e.printStackTrace();
		}

		String[] printable = new String[lines.size()];
		printable = lines.toArray(printable);

		return printable;
	}
}