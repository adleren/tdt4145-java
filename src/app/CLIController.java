package app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import controller.*;
import model.*;
import util.CLIPrinter;
import util.HelpReader;

public class CLIController {

	public static final String PATTERN_V45 		= "[A-Za-zæøåÆØÅ]{1,45}";
	public static final String PATTERN_V255 	= "[A-Za-zæøåÆØÅ ]{1,255}";
	public static final String PATTERN_N 		= "[0-9]{1,10}";
	public static final String PATTERN_DATETIME = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
	public static final String PATTERN_TIME 	= "[0-9]{2}:[0-9]{2}:[0-9]{2}";

	private Connection connection;
	private Scanner scanner;
	private String[] helpManual;


	public CLIController(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
		this.helpManual = HelpReader.readHelpManual();
	}

	private void addWorkout() {
		CLIPrinter.print("Adding new workout");

		String datetime = null;
		while (datetime == null) {
			System.out.println("Date and time:");
			datetime = this.scanner.nextLine();

			if (!datetime.matches(PATTERN_DATETIME)) {
				System.out.println("Illegal date and time. Try again.\n");
				datetime = null;
			}
		}

		String duration = null;
		while (duration == null) {
			System.out.println("Duration:");
			duration = this.scanner.nextLine();

			if (!duration.matches(PATTERN_TIME)) {
				System.out.println("Illegal duration value. Try again.\n");
				duration = null;
			}
		}

		String shape = null;
		while (shape == null) {
			System.out.println("Personal shape:");
			shape = this.scanner.nextLine();

			if (!shape.matches(PATTERN_N)) {
				System.out.println("Illegal value. Try again.\n");
				shape = null;
			}
		}

		String performance = null;
		while (performance == null) {
			System.out.println("Personal performance:");
			performance = this.scanner.nextLine();

			if (!performance.matches(PATTERN_N)) {
				System.out.println("Illegal value. Try again.\n");
				performance = null;
			}
		}

		String notes = null;
		while (notes == null) {
			System.out.println("Notes:");
			notes = this.scanner.nextLine();

			if (!notes.matches(PATTERN_V255)) {
				System.out.println("Illegal value. Try again.\n");
				notes = null;
			}
		}

		CLIPrinter.print("Does this look right?",
			"New Workout: "
			+ "'Date/Time': " + datetime + ", "
			+ "'Duration': " + duration + ", "
			+ "'Shape': " + shape + ", "
			+ "'Performance': " + performance + ", "
			+ "'Notes': " + notes + " "
			+ "(Y/N)"
		);

		// TODO: Add exercises to workout here somewhere

		boolean confirmed = this.scanner.nextLine().toLowerCase().startsWith("y");
		if (confirmed) {
			Workout workout = new Workout(-1, Date.valueOf(datetime), Time.valueOf(duration), Integer.parseInt(shape), Integer.parseInt(performance), notes);
			WorkoutController.create(this.connection, workout);
			CLIPrinter.print("Successfully added workout to diary!");
		} else {
			CLIPrinter.print("Did not add new workout to diary.");
		}

	}

	private void addExercise() {
		// TODO: Add method
	}

	private void addGroup() {
		// TODO: Add method
	}

	private void addEquipment() {
		CLIPrinter.print("Adding new equipment");

		String name = null;
		while (name == null) {
			System.out.println("Name:");
			name = this.scanner.nextLine();

			if (!name.matches(PATTERN_V45)) {
				System.out.println("Illegal name. Try again.\n");
				name = null;
			}
		}

		String desc = null;
		while (desc == null) {
			System.out.println("Description:");
			desc = this.scanner.nextLine();

			if (!desc.matches(PATTERN_V255)) {
				System.out.println("Illegal description. Try again.\n");
				desc = null;
			}
		}

		CLIPrinter.print("Does this look right?", "New Equipment: 'Name': " + name + ", 'Description': " + desc + " (Y/N)");

		boolean confirmed = this.scanner.nextLine().toLowerCase().startsWith("y");
		if (confirmed) {
			Equipment equipment = new Equipment(-1, name, desc);
			EquipmentController.create(this.connection, equipment);
			CLIPrinter.print("Added new equipment to diary!");
		} else {
			CLIPrinter.print("Did not add new equipment to diary.");
		}
	}

	private void deleteWorkout() {
		readAllWorkouts();
		CLIPrinter.print("Select ID of the workout to delete:");

		int id = -1;
		while (id < 1) {
			String input = this.scanner.nextLine();
			try {
				id = Integer.parseInt(input);
			} catch (Exception e) {
				id = -1;
				System.out.println("Please enter a valid ID. This should be an integer.");
			}
		}

		if (WorkoutController.deleteById(this.connection, id)) {
			CLIPrinter.print("Successfully deleted workout!");
		} else {
			CLIPrinter.print("Unable to delete workout. Please check the ID and try again.");
		}
	}

	private void deleteExercise() {
		readAllExercises();
		CLIPrinter.print("Select ID of the exercise to delete:");

		int id = -1;
		while (id < 1) {
			String input = this.scanner.nextLine();
			try {
				id = Integer.parseInt(input);
			} catch (Exception e) {
				id = -1;
				System.out.println("Please enter a valid ID. This should be an integer.");
			}
		}

		if (ExerciseController.deleteById(this.connection, id)) {
			CLIPrinter.print("Successfully deleted exercise!");
		} else {
			CLIPrinter.print("Unable to delete exercise. Please check the ID and try again.");
		}
	}

	private void deleteGroup() {
		readAllGroups();
		CLIPrinter.print("Select ID of the group to delete:");

		int id = -1;
		while (id < 1) {
			String input = this.scanner.nextLine();
			try {
				id = Integer.parseInt(input);
			} catch (Exception e) {
				id = -1;
				System.out.println("Please enter a valid ID. This should be an integer.");
			}
		}

		if (GroupController.deleteById(this.connection, id)) {
			CLIPrinter.print("Successfully deleted group!");
		} else {
			CLIPrinter.print("Unable to delete group. Please check the ID and try again.");
		}
	}

	private void deleteEquipment() {
		readAllEquipments();
		CLIPrinter.print("Select ID of the equipment to delete:");

		int id = -1;
		while (id < 1) {
			String input = this.scanner.nextLine();
			try {
				id = Integer.parseInt(input);
			} catch (Exception e) {
				id = -1;
				System.out.println("Please enter a valid ID. This should be an integer.");
			}
		}

		if (EquipmentController.deleteById(this.connection, id)) {
			CLIPrinter.print("Successfully deleted equipment!");
		} else {
			CLIPrinter.print("Unable to delete equipment. Please check the ID and try again.");
		}
	}

	private void readAllWorkouts() {
		List<Workout> workouts = WorkoutController.findAll(this.connection);
		String[] array = new String[workouts.size() + 2];
		
		array[0] = "Workouts:";
		array[1] = "ID\tDate/Time\tDuration\tShape\tPerformance\tNotes";

		for (int i = 2; i < array.length; i++) {
			array[i] = workouts.get(i - 2).getRowString();
		}
		
		CLIPrinter.print(array);
	}

	private void readAllExercises() {
		List<Exercise> exercises = ExerciseController.findAll(this.connection);
		String[] array = new String[exercises.size() + 2];
		
		array[0] = "Exercises:";
		array[1] = "ID\tName\tType\tDescription\tKilos\tSets\tEquipment";

		for (int i = 2; i < array.length; i++) {
			array[i] = exercises.get(i - 2).getRowString();
		}
		
		CLIPrinter.print(array);
	}

	private void readAllGroups() {
		List<Group> groups = GroupController.findAll(this.connection);
		String[] array = new String[groups.size() + 2];
		
		array[0] = "Exercise groups:";
		array[1] = "ID\tName";

		for (int i = 2; i < array.length; i++) {
			array[i] = groups.get(i - 2).getRowString();
		}
		
		CLIPrinter.print(array);
	}

	private void readAllEquipments() {
		List<Equipment> equipment = EquipmentController.findAll(this.connection);
		String[] array = new String[equipment.size() + 2];
		
		array[0] = "Equipment:";
		array[1] = "ID\tName\tDescription";

		for (int i = 2; i < array.length; i++) {
			array[i] = equipment.get(i - 2).getRowString();
		}
		
		CLIPrinter.print(array);
	}

	public void checkAdd(String s) {
		String[] input = s.split(" ");

		if (input.length == 1) {
			CLIPrinter.print(
				"Please provide one of the following arguments:",
				"",
				"equipment",
				"exercise",
				"group",
				"workout",
				"------"
			);
			return;
		}
		
		switch(input[1]) {
			case "equipment":
				addEquipment();
				break;
			case "exercise":
				addExercise();
				break;
			case "group":
				addGroup();
				break;
			case "workout":
				addWorkout();
				break;
			default:
				CLIPrinter.print(
					"Please provide one of the following arguments:",
					"",
					"equipment",
					"exercise",
					"group",
					"workout",
					"------"
				);
		}
	}
	
	private void checkDelete(String s) {
		String[] input = s.split(" ");

		if (input.length == 1) {
			CLIPrinter.print(
				"Please provide one of the following arguments:",
				"",
				"equipment",
				"exercise",
				"group",
				"workout",
				"------"
			);
			return;
		}
		
		switch(input[1]) {
			case "equipment":
				deleteEquipment();
				break;
			case "exercise":
				deleteExercise();
				break;
			case "group":
				deleteGroup();
				break;
			case "workout":
				deleteWorkout();
				break;
			default:
				CLIPrinter.print(
					"Please provide one of the following arguments to delete command:",
					"",
					"equipment",
					"exercise",
					"group",
					"workout",
					"------"
				);
		}
	}
	
	private void checkRead(String s) {
		String[] input = s.split(" ");

		if (input.length == 1) {
			CLIPrinter.print(
				"Please provide one of the following arguments:",
				"",
				"equipment",
				"exercise",
				"group",
				"workout",
				"------"
			);
			return;
		}
		
		switch(input[1]) {
			case "equipment":
				readAllEquipments();
				break;
			case "exercise":
				readAllExercises();
				break;
			case "group":
				readAllGroups();
				break;
			case "workout":
				readAllWorkouts();
				break;
			default:
				CLIPrinter.print(
					"Please provide one of the following arguments:",
					"",
					"equipment",
					"exercise",
					"group",
					"workout",
					"------"
				);
		}
	}

	private void validateString(String s) {
		switch(s.split(" ")[0]) {
			case "add":
				checkAdd(s);
				break;
			case "delete":
				checkDelete(s);
				break;
			case "read":
				checkRead(s);
				break;
			case "help":
				printHelp();
				break;
			case "exit":
				exit();
				break;
			default:
				CLIPrinter.print(
					"Please use one of the following commands:",
					"",
					"add",
					"delete",
					"read",
					"help",
					"exit",
					"------"
			);
		}
	}

	public void tick(String s) {
		this.validateString(s);
	}

	private void printHelp() {
		CLIPrinter.print(this.helpManual);
	}

	private void exit() {
		CLIPrinter.print("\nGood bye!");
		try {
			this.connection.close();
			this.scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.exit(0);
	}

}