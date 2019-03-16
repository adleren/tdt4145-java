package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import controller.*;
import model.*;
import util.CLIPrinter;
import util.HelpReader;

public class CLIController {

	public static final String PATTERN_V45 = "'[A-Za-zæøåÆØÅ]{1,45}'";
	public static final String PATTERN_V255 = "'[A-Za-zæøåÆØÅ]{1,255}'";
	public static final String PATTERN_N = "'[0-9]{1,10}'";
	public static final String PATTERN_DATETIME = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
	public static final String PATTERN_TIME = "[0-9]{2}:[0-9]{2}:[0-9]{2}";

	private Connection connection;
	private String[] helpManual;

	public CLIController(Connection connection) {
		this.connection = connection;

		this.helpManual = HelpReader.readHelpManual();
	}

	private void addWorkout(String s) {
		CLIPrinter.print(s);
	}

	private void addEquipmentExercise(String s) {
		CLIPrinter.print(s);
	}

	private void addFreeExercise(String s) {
		CLIPrinter.print(s);
	}

	private void addGroup(String s) {
		CLIPrinter.print(s);
	}

	private void addEquipment(String s) {
		CLIPrinter.print(s);
	}

	private void updateWorkout(String s) {
		CLIPrinter.print(s);
	}

	private void updateEquipmentExercise(String s) {
		CLIPrinter.print(s);
	}

	private void updateFreeExercise(String s) {
		CLIPrinter.print(s);
	}

	private void updateGroup(String s) {
		CLIPrinter.print(s);
	}

	private void updateEquipment(String s) {
		CLIPrinter.print(s);
	}

	private void readAllWorkouts() {
		List<Workout> workouts = WorkoutController.findAll(this.connection);
		String[] array = new String[workouts.size() + 2];
		
		array[0] = "Workouts:";
		array[1] = "ID\tDate/Time\tDuration\tShape\tNotes";

		for (int i = 2; i < array.length; i++) {
			array[i] = workouts.get(i - 2).getRowString();
		}
		
		CLIPrinter.print(array);
	}

	private void readAllExercises() {
		List<Exercise> exercises = ExerciseController.findAll(this.connection);
		String[] array = new String[exercises.size() + 2];
		
		array[0] = "Exercises:";
		array[1] = "ID\tName\tDescription\tKilos\tSets\tEquipment";

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

	private void printHelp() {
		CLIPrinter.print(this.helpManual);
	}

	private void exit() {
		CLIPrinter.print("\nGood bye!");
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.exit(0);
	}

	public void checkAdd(String s) {
		String equipmentRegex = "add equipment[(]"+PATTERN_V45+","+PATTERN_V255+"[)]";
		String groupRegex = "add group[(]"+PATTERN_V45+"[)]";
		String freeExerciseRegex = "add free-exercise[(]"+PATTERN_V45+","+PATTERN_V255+"[)][("+PATTERN_V45+")]*";
		String equipmentExerciseRegex = "add equipment-exercise[(]"+PATTERN_V45+","+PATTERN_N+","+PATTERN_N+"[)][("+PATTERN_V45+")]*";
		String workoutRegex = "add workout[(]"+PATTERN_DATETIME+","+PATTERN_TIME+","+PATTERN_N+"[)][("+PATTERN_V45+","+PATTERN_N+")]*";
		
		String errorM = "Invalid '"+s.split("[(]")[0]+"' command, here's how to format it:\n\n";
		switch(s.split("[(]")[0]) {
			case "add equipment":
				if (s.matches(equipmentRegex)) {
					addEquipment(s);
				} else {
					System.out.printf(errorM+equipmentRegex+"\n------\n");
				}
				break;
			case "add group":
				if (s.matches(groupRegex)) {
					addGroup(s);
				} else {
					System.out.printf(errorM+groupRegex+"\n------\n");
				}
				break;
			case "add free-exercise":
				if (s.matches(freeExerciseRegex)) {
					addFreeExercise(s);
				} else {
					System.out.printf(errorM+freeExerciseRegex+"\n------\n");
				}
				break;
			case "add equipment-exercise":
				if (s.matches(equipmentExerciseRegex)) {
					addEquipmentExercise(s);
				} else {
					System.out.printf(errorM+equipmentExerciseRegex+"\n------\n");
				}
				break;
			case "add workout":
				if (s.matches(workoutRegex)) {
					addWorkout(s);
				} else {
					System.out.printf(errorM+workoutRegex+"\n------\n");
				}
				break;
			default:
			CLIPrinter.print(
				"Please add one of the following objects:",
				"",
				"equipment",
				"group",
				"free-exercise",
				"equipment-exercise",
				"workout",
				"------"
			);
		}
	}

	private void checkUpdate(String s) {
		String equipmentRegex = "update equipment[(]"+PATTERN_V45+","+PATTERN_V255+"[)][(]"+PATTERN_V45+","+PATTERN_V255+"[)]";
		String groupRegex = "update group[(]"+PATTERN_V45+"[)][(]"+PATTERN_V45+"[)]";
		String freeExerciseRegex = "update free-exercise[(]"+PATTERN_V45+","+PATTERN_V255+"[)][(]"+PATTERN_V45+","+PATTERN_V255+"[)][("+PATTERN_V45+")]*";
		String equipmentExerciseRegex = "update equipment-exercise[(]"+PATTERN_V45+","+PATTERN_N+","+PATTERN_N+"[)][(]"+PATTERN_V45+","+PATTERN_N+","+PATTERN_N+"[)][("+PATTERN_V45+")]*";
		String workoutRegex = "update workout[(]"+PATTERN_DATETIME+","+PATTERN_TIME+","+PATTERN_N+"[)][("+PATTERN_V45+","+PATTERN_N+")]*";
		String errorM = "Invalid '"+s.split("[(]")[0]+"' command, here's how to format it:\n\n";
		switch(s.split("[(]")[0]) {
			case "update equipment":
				if (s.matches(equipmentRegex)) {
					updateEquipment(s);
				} else {
					System.out.printf(errorM+equipmentRegex+"\n------\n");
				}
				break;
			case "update group":
				if (s.matches(groupRegex)) {
					updateGroup(s);
				} else {
					System.out.printf(errorM+groupRegex+"\n------\n");
				}
				break;
			case "update free-exercise":
				if (s.matches(freeExerciseRegex)) {
					updateFreeExercise(s);
				} else {
					System.out.printf(errorM+freeExerciseRegex+"\n------\n");
				}
				break;
			case "update equipment-exercise":
				if (s.matches(equipmentExerciseRegex)) {
					updateEquipmentExercise(s);
				} else {
					System.out.printf(errorM+equipmentExerciseRegex+"\n------\n");
				}
				break;
			case "update workout":
				if (s.matches(workoutRegex)) {
					updateWorkout(s);
				} else {
					System.out.printf(errorM+workoutRegex+"\n------\n");
				}
				break;
			default:
			CLIPrinter.print(
				"Please update one of the following objects:",
				"",
				"equipment",
				"group",
				"free-exercise",
				"equipment-exercise",
				"workout",
				"------"
			);
		}
	}
	
	private void checkDelete(String s) {
		String equipmentRegex = "delete equipment[(]"+PATTERN_V45+"[)]";
		String groupRegex = "delete group[(]"+PATTERN_V45+"[)]";
		String freeExerciseRegex = "delete free-exercise[(]"+PATTERN_V45+"[)]";
		String equipmentExerciseRegex = "delete equipment-exercise[(]"+PATTERN_V45+"[)]";
		String workoutRegex = "delete workout[(]"+PATTERN_DATETIME+"[)]";
		String errorM = "Invalid '"+s.split("[(]")[0]+"' command, here's how to format it:\n\n";
		switch(s.split("[(]")[0]) {
			case "delete equipment":
				if (s.matches(equipmentRegex)) {
					updateEquipment(s);
				} else {
					System.out.printf(errorM+equipmentRegex+"\n------\n");
				}
				break;
			case "delete group":
				if (s.matches(groupRegex)) {
					updateGroup(s);
				} else {
					System.out.printf(errorM+groupRegex+"\n------\n");
				}
				break;
			case "delete free-exercise":
				if (s.matches(freeExerciseRegex)) {
					updateFreeExercise(s);
				} else {
					System.out.printf(errorM+freeExerciseRegex+"\n------\n");
				}
				break;
			case "delete equipment-exercise":
				if (s.matches(equipmentExerciseRegex)) {
					updateEquipmentExercise(s);
				} else {
					System.out.printf(errorM+equipmentExerciseRegex+"\n------\n");
				}
				break;
			case "delete workout":
				if (s.matches(workoutRegex)) {
					updateWorkout(s);
				} else {
					System.out.printf(errorM+workoutRegex+"\n------\n");
				}
				break;
			default:
			CLIPrinter.print(
				"Please delete one of the following objects:",
				"",
				"equipment",
				"group",
				"free-exercise",
				"equipment-exercise",
				"workout",
				"------"
			);
		}
	}
	
	private void checkRead(String s) {
		if (s.split(" ").length == 1) {
			CLIPrinter.print(
				"Please read one of the following objects:",
				"",
				"equipment",
				"group",
				"free-exercise",
				"equipment-exercise",
				"workout",
				"------"
			);
		} else {
			switch(s.split(" ")[1]) {
				case "equipment":
					readAllEquipments();
					break;
				case "group":
					readAllGroups();
					break;
				case "exercise":
					readAllExercises();

					break;
				case "workout":
					readAllWorkouts();
					break;
				default:
					CLIPrinter.print(
						"Please read one of the following objects:",
						"",
						"equipment",
						"group",
						"free-exercise",
						"equipment-exercise",
						"workout",
						"------"
					);
			}
		}
	}

	public void validateString(String s) {
		switch(s.split(" ")[0]) {
		case "add":
			checkAdd(s);
			break;
		case "update":
			checkUpdate(s);
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
				"update",
				"delete",
				"read",
				"help",
				"exit",
				"------"
			);
		}
	}
	
	/*	
	Lese fra databasen:
	read equipment
	read equipment('Trolley')
	read group
	read group('Legs')
	read exercise
	read exercise('Push-ups')
	read workout('2019-03-26')

	Lag nye rader i databasen:
	add equipment('Tralle','Trall den rundt')
	add group('Beinøvelse')
	add free-exercise('Pushups','En pushup er en vanlig...')('Legs')('Arms')
	add equipment-exercise('Beinpress','22','3')('Tralle')
	add workout('2019-03-26 09:50:00','02:00:00',5)('Pushups',4)('Situps',2)

	Endre rader i databasen:
	update equipment('Tralle','Trall den rundt')('Trolley','Trolley it around')
	update group('Legs')
	update free-exercise('Push-ups','A pushup is a normal...')('Legs')
	update equipment-exercise('Leg-press','12','3')('Tralle')
	update workout('2019-03-26 09:52:00','02:00:00',5)('Pushups',3)

	Slette rader i databasen:
	delete equipment('Trolley','Trolley it around')
	delete group('Legs')
	delete free-exercise('Push-ups')
	delete equipment-exercise('Leg-press')
	delete workout('2019-03-26 09:52:00','02:00:00',5)
	*/
}