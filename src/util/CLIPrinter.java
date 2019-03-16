package util;


public class CLIPrinter {

	public static void print(String... args) {
		System.out.println();
		
		for (String s : args) {
			System.out.println(s);
		}
		
		System.out.println();
	}
}