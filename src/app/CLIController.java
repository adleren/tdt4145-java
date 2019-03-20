package app;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import controller.*;
import model.*;
import util.CLIPrinter;
import util.HelpReader;

public class CLIController {

	public static final String PATTERN_V45 		= "[A-Za-zæøåÆØÅ-]{1,45}";
	public static final String PATTERN_V255 	= "[0-9A-Za-zæøåÆØÅ ._!?%$&#/()=+@*,-]{1,255}";
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
			System.out.println("Date and time (on format 'YYYY-MM-DD HH:MM:SS'):");
			datetime = this.scanner.nextLine();

			if (!datetime.matches(PATTERN_DATETIME)) {
				System.out.println("Illegal format. Date and time should match 'YYYY-MM-DD HH:MM:SS'\n");
				datetime = null;
			}
		}

		String duration = null;
		while (duration == null) {
			System.out.println("Duration (on format 'HH:MM:SS'):");
			duration = this.scanner.nextLine();

			if (!duration.matches(PATTERN_TIME)) {
				System.out.println("Illegal format. Duration should match 'HH:MM:SS'\n");
				duration = null;
			}
		}

		String shape = null;
		while (shape == null) {
			System.out.println("Personal shape from 1 to 10:");
			shape = this.scanner.nextLine();

			if (!shape.matches(PATTERN_N)) {
				System.out.println("Illegal value. Should be an integer from 1 to 10.\n");
				shape = null;
			}
		}

		String performance = null;
		while (performance == null) {
			System.out.println("Personal performance from 1 to 10:");
			performance = this.scanner.nextLine();

			if (!performance.matches(PATTERN_N)) {
				System.out.println("Illegal value. Should be an integer from 1 to 10.\n");
				performance = null;
			}
		}

		String notes = null;
		while (notes == null) {
			System.out.println("Notes:");
			notes = this.scanner.nextLine();

			if (!notes.matches(PATTERN_V255)) {
				if (notes != "") {
					System.out.println("Your notes may contain some weird symbols. Please try again.\n");
					notes = null;
				}
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

		boolean confirmed = this.scanner.nextLine().toLowerCase().startsWith("y");
		if (confirmed) {
			Workout workout = new Workout(-1, datetime, duration, Integer.parseInt(shape), Integer.parseInt(performance), notes);
			WorkoutController.create(this.connection, workout);
			CLIPrinter.print("Successfully added workout to diary!\nYou can add exercises to this workout by running 'update workout' and selecting this workout.");
		} else {
			CLIPrinter.print("Did not add new workout to diary.");
		}

	}

	private void addExercise() {
		CLIPrinter.print("Adding new exercise");
		Exercise exercise = null;

		String type = null;
		while (type == null) {
			System.out.println("Select exercise type (free/equipment):");
			type = this.scanner.nextLine();
			if (!type.toLowerCase().equals("free") && !type.toLowerCase().equals("equipment")) {
				CLIPrinter.print("Please choose a valid exercise type.");
				type = null;
			} else {
				CLIPrinter.print("Creating new " + type + " exercise.");
			}
		}

		String name = null;
		while (name == null) {
			System.out.println("Name:");
			name = this.scanner.nextLine();

			if (!name.matches(PATTERN_V45)) {
				System.out.println("Illegal name. Try again.\n");
				name = null;
			}
		}

		if (type.toLowerCase().startsWith("f")) {
			String desc = null;
			while (desc == null) {
				System.out.println("Description:");
				desc = this.scanner.nextLine();

				if (!desc.matches(PATTERN_V255)) {
					System.out.println("Illegal description. Try again.\n");
					desc = null;
				}
			}

			exercise = new FreeExercise(-1, name, desc);

		} else if (type.toLowerCase().startsWith("e")) {
			this.readAllEquipments();
			System.out.println("Select equipment for this exercise");
			int equipmentId = -1;
			while (equipmentId < 0) {
				System.out.println("ID:");
				try {
					equipmentId = Integer.parseInt(this.scanner.nextLine());
				} catch (Exception e) {
					equipmentId = -1;
					System.out.println("Please enter a valid ID. This should be an integer.");
				}
			}
			
			Equipment equipment = EquipmentController.findById(this.connection, equipmentId);
			if (equipment != null) {
				exercise = new EquipmentExercise(-1, name, -1, -1, equipment);
			}
		}

		if (ExerciseController.create(this.connection, exercise)) {
			CLIPrinter.print("Successfully added exercise to diary!");
		} else {
			CLIPrinter.print("Unable to add exercise to diary.");
		}
	}

	private void addGroup() {
		CLIPrinter.print("Adding new group");

		String name = null;
		while (name == null) {
			System.out.println("Name:");
			name = this.scanner.nextLine();

			if (!name.matches(PATTERN_V45)) {
				System.out.println("Illegal name. Try again.\n");
				name = null;
			}
		}

		CLIPrinter.print("Does this look right?", "New Group: 'Name': " + name + " (Y/N)");

		boolean confirmed = this.scanner.nextLine().toLowerCase().startsWith("y");
		if (confirmed) {
			Group group = new Group(-1, name);
			GroupController.create(this.connection, group);
			CLIPrinter.print("Added new group to diary!\nYou associate exercises with this group by running 'update group' and selecting this group.");
		} else {
			CLIPrinter.print("Did not add new group to diary.");
		}
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
		CLIPrinter.print("Select ID of the workout to delete");

		int id = -1;
		while (id < 0) {
			System.out.println("ID:");
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
		CLIPrinter.print("Select ID of the exercise to delete");

		int id = -1;
		while (id < 0) {
			System.out.println("ID:");
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
		CLIPrinter.print("Select ID of the group to delete");

		int id = -1;
		while (id < 0) {
			System.out.println("ID:");
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
		CLIPrinter.print("Select ID of the equipment to delete");

		int id = -1;
		while (id < 0) {
			System.out.println("ID:");
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

	private void updateWorkout() {
		this.readAllWorkouts();
		CLIPrinter.print("Select workout ID to add exercises to:");

		int workoutId = -1;
		while (workoutId < 0) {
			String input = "";
			
			System.out.println("ID:");
			input = this.scanner.nextLine();
			
			try {
				workoutId = Integer.parseInt(input);
			} catch (Exception e) {
				CLIPrinter.print("ID must be an integer!");
				workoutId = -1;
			}
		}

		CLIPrinter.print("Selected workout " + workoutId);
		
		this.readAllExercises();
		CLIPrinter.print("Add exercises to workout.");
		
		List<Exercise> exercises = new ArrayList<>();
		
		while (true) {
			String input = "";
			
			System.out.println("ID (or '.done'):");
			input = this.scanner.nextLine();

			if (input.equals(".done"))
				break;

			try {
				int id = Integer.parseInt(input);
				int kilos = -1;
				int sets = -1;
				Exercise exercise = ExerciseController.findById(this.connection, id);
				
				if (exercise instanceof EquipmentExercise) {
					while (kilos < 0) {
						String kilosInput = "";
						
						System.out.println("Kilos lifted:");
						kilosInput = this.scanner.nextLine();
						
						try {
							kilos = Integer.parseInt(kilosInput);
							((EquipmentExercise) exercise).setKilos(kilos);
						} catch (Exception e) {
							CLIPrinter.print("Number of kilos lifted must be a positive integer.");
							kilos = -1;
						}
					}
					while (sets < 0) {
						String setsInput = "";
						
						System.out.println("Sets:");
						setsInput = this.scanner.nextLine();
						
						try {
							sets = Integer.parseInt(setsInput);
							((EquipmentExercise) exercise).setSets(sets);
						} catch (Exception e) {
							CLIPrinter.print("Number of sets must be a positive integer.");
							sets = -1;
						}
					}
				}

				exercises.add(exercise);
			} catch (Exception e) {
				CLIPrinter.print("ID must be an integer!");
			}
		}

		boolean success = true;

		for (Exercise exercise : exercises) {
			success = success & WorkoutController.addExerciseToWorkout(connection, workoutId, exercise);
		}

		if (success) {
			CLIPrinter.print("Successfully added exercise(s) to workout.");
		} else {
			CLIPrinter.print("Unable to add exercises to workout.");
		}
	}

	private void updateGroup() {
		this.readAllGroups();
		CLIPrinter.print("Select group ID to add exercises to:");
		int groupId = -1;
		while (groupId < 0) {
			String input = "";
			
			System.out.println("ID:");
			input = this.scanner.nextLine();
			
			try {
				groupId = Integer.parseInt(input);
			} catch (Exception e) {
				CLIPrinter.print("ID must be an integer!");
				groupId = -1;
			}
		}

		CLIPrinter.print("Selected group " + groupId);
		
		this.readAllExercises();
		CLIPrinter.print("Add exercises to group. Type '.done' when you are finished.");
		
		List<Integer> addExerciseIds = new ArrayList<>();
		
		while (true) {
			String input = "";
			
			System.out.println("ID:");
			input = this.scanner.nextLine();

			if (input.equals(".done"))
				break;

			try {
				int id = Integer.parseInt(input);
				addExerciseIds.add(id);
			} catch (Exception e) {
				CLIPrinter.print("ID must be an integer!");
			}
		}

		boolean success = true;

		for (int exerciseId : addExerciseIds) {
			success = success & GroupController.addExerciseToGroup(connection, groupId, exerciseId);
		}

		if (success) {
			CLIPrinter.print("Successfully added exercises to group.");
		} else {
			CLIPrinter.print("Unable to add exercises to group.");
		}

		List<Exercise> exercisesInGroup = ExerciseController.findByGroupId(this.connection, groupId);
		String[] output = new String[2 + exercisesInGroup.size()];
		
		output[0] = "Exercises:";
		output[1] = "ID\tType\tName";
		for (int i = 2; i < output.length; i++) {
			Exercise exercise = exercisesInGroup.get(i - 2);
			String type = (exercise instanceof FreeExercise) ? "Free" : "Equipment";
			output[i] = exercise.getId() + "\t" + type + "\t" + exercise.getName();
		}
		CLIPrinter.print(output);

		CLIPrinter.print("Delete exercises from group. Type '.done' when you are finished.");
		List<Integer> deleteExerciseIds = new ArrayList<>();
		
		while (true) {
			String input = "";
			
			System.out.println("ID:");
			input = this.scanner.nextLine();

			if (input.equals(".done"))
				break;

			try {
				int id = Integer.parseInt(input);
				deleteExerciseIds.add(id);
			} catch (Exception e) {
				CLIPrinter.print("ID must be an integer!");
			}
		}

		success = false;

		for (int exerciseId : deleteExerciseIds) {
			success = success & GroupController.deleteExerciseFromGroup(connection, groupId, exerciseId);
		}

		if (success) {
			CLIPrinter.print("Successfully deleted exercises from group.");
		} else {
			CLIPrinter.print("Unable to delete exercises from group.");
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

	private void readLatestWorkouts(String s) {
		try {
			int n = Integer.parseInt(s.split(" ")[2]);

			List<Workout> workouts = WorkoutController.findLatest(this.connection, n);
			String[] array = new String[workouts.size() + 2];
			
			array[0] = "Workouts:";
			array[1] = "ID\tDate/Time\tDuration\tShape\tPerformance\tNotes";

			for (int i = 2; i < array.length; i++) {
				array[i] = workouts.get(i - 2).getRowString();
			}
			
			CLIPrinter.print(array);
		} catch (Exception e) {
			CLIPrinter.print("Unable to parse id from 'read workouts <n>' command.");
		}
	}

	private void readWorkout() {
		readAllWorkouts();
		CLIPrinter.print("Select ID of the workout to read:");

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

		Workout workout = WorkoutController.findById(this.connection, id);

		if (workout != null) {
			List<Exercise> exercises = ExerciseController.findByWorkoutId(this.connection, id);
			String[] output = new String[5 + exercises.size()];

			output[0] = "Workout:";
			output[1] = "ID\tDate/Time\tDuration\tShape\tPerformance\tNotes";
			output[2] = workout.getRowString() + "\n";
			output[3] = "Exercises:";
			output[4] = "ID\tType\tName";

			for (int i = 5; i < output.length; i++) {
				Exercise exercise = exercises.get(i - 5);
				String type = (exercise instanceof FreeExercise) ? "Free" : "Equipment";
				output[i] = exercise.getId() + "\t" + type + "\t" + exercise.getName();
			}

			CLIPrinter.print(output);
		} else {
			CLIPrinter.print("Unable to find workout.");
		}
	}

	private void readAllExercises() {
		List<Exercise> exercises = ExerciseController.findAll(this.connection);
		String[] array = new String[exercises.size() + 2];
		
		array[0] = "Exercises:";
		array[1] = "ID\tType\tName";

		for (int i = 2; i < array.length; i++) {
			Exercise exercise = exercises.get(i - 2);
			String type = (exercise instanceof FreeExercise) ? "Free" : "Equipment";
			array[i] = exercise.getId() + "\t" + type + "\t" + exercise.getName();
		}
		
		CLIPrinter.print(array);
	}

	private void readExercise() {
		readAllExercises();
		CLIPrinter.print("Select ID of the exercise to read:");

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
		
		Exercise exercise = ExerciseController.findById(this.connection, id);

		if (exercise instanceof FreeExercise) {
			CLIPrinter.print(
				"ID\tType\tName\tDescription",
				exercise.getId() + "\t"
				+ "Free" + "\t"
				+ exercise.getName() + "\t"
				+ ((FreeExercise) exercise).getDescription()
			);
		} else if (exercise instanceof EquipmentExercise) {
			CLIPrinter.print(
				"ID\tType\tName\tEquipment",
				exercise.getId() + "\t"
				+ "Equipment" + "\t"
				+ exercise.getName() + "\t"
				+ ((EquipmentExercise) exercise).getEquipment().getName()
			);
		} else {
			CLIPrinter.print("Unable to find exercise.");
		}
	}

	private void readAllGroups() {
		List<Group> groups = GroupController.findAll(this.connection);
		String[] array = new String[groups.size() + 2];
		
		array[0] = "Exercise groups:";
		array[1] = "ID\tName";

		for (int i = 2; i < array.length; i++) {
			Group group = groups.get(i - 2);
			array[i] = group.getId() + "\t" + group.getName();
		}
		
		CLIPrinter.print(array);
	}

	private void readGroup() {
		readAllGroups();
		CLIPrinter.print("Select ID of the group to read:");

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

		Group group = GroupController.findById(this.connection, id);

		if (group != null) {
			List<Exercise> exercises = ExerciseController.findByGroupId(this.connection, id);
			String[] output = new String[5 + exercises.size()];

			output[0] = "Group:";
			output[1] = "ID\tName";
			output[2] = group.getRowString() + "\n";
			output[3] = "Exercises:";
			output[4] = "ID\tType\tName";

			for (int i = 5; i < output.length; i++) {
				Exercise exercise = exercises.get(i - 5);
				String type = (exercise instanceof FreeExercise) ? "Free" : "Equipment";
				output[i] = exercise.getId() + "\t" + type + "\t" + exercise.getName();
			}

			CLIPrinter.print(output);
		} else {
			CLIPrinter.print("Unable to find group.");
		}
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

	private void findPerformance() {
		this.readAllExercises();
		CLIPrinter.print("Select an exercise");
		
		int id = -1;
		while (id < 1) {
			System.out.println("ID (integer):");
			String input = this.scanner.nextLine();
			try {
				id = Integer.parseInt(input);
			} catch (Exception e) {
				id = -1;
				System.out.println("Please enter a valid ID. This should be an integer.");
			}
		}

		if (ExerciseController.findById(this.connection, id) == null) {
			CLIPrinter.print("Unable to find exercise.");
			return;
		}

		CLIPrinter.print("Select a start date and time");
		String from = null;
		while (from == null) {
			System.out.println("From (YYYY-MM-DD HH:MM:SS):");
			from = this.scanner.nextLine();

			if (!from.matches(PATTERN_DATETIME)) {
				System.out.println("Illegal format. Date and time should match 'YYYY-MM-DD HH:MM:SS'\n");
				from = null;
			}
		}

		CLIPrinter.print("Select a end date and time");
		String to = null;
		while (to == null) {
			System.out.println("To (YYYY-MM-DD HH:MM:SS):");
			to = this.scanner.nextLine();

			if (!to.matches(PATTERN_DATETIME)) {
				System.out.println("Illegal format. Date and time should match 'YYYY-MM-DD HH:MM:SS'\n");
				to = null;
			}
		}

		double performance = ExerciseController.getExercisePerformance(this.connection, id, from, to);

		if (performance < 0) {
			CLIPrinter.print("Unable to collect performance data for this exercise.");
		} else {
			CLIPrinter.print("Your average performance for this exercise in the time period selected is " + performance);
		}
	}

	private void findFavouriteExercise() {
		Map<Exercise, Integer> favourites = ExerciseController.findFavouriteExercises(this.connection);

		if (!favourites.isEmpty()) {
			System.out.println("\nID\tExercise\tTimes used");
			for (Exercise exercise : favourites.keySet()) {
				System.out.println(exercise.getId() + "\t" + exercise.getName() + "\t" + favourites.get(exercise));
			}
			System.out.println();
		} else {
			CLIPrinter.print("Unable to find favourite exercises.");
		}
	}

	private void findFavouriteEquipment() {
		Map<Equipment, Integer> favourites = EquipmentController.findFavouriteEquipment(this.connection);

		if (!favourites.isEmpty()) {
			System.out.println("\nID\tEquipment\tTimes used");
			for (Equipment equipment : favourites.keySet()) {
				System.out.println(equipment.getId() + "\t" + equipment.getName() + "\t" + favourites.get(equipment));
			}
			System.out.println();
		} else {
			CLIPrinter.print("Unable to find favourite equipment.");
		}
	}

	private void checkFavourite(String s) {
		String[] input = s.split(" ");

		if (input.length == 1) {
			CLIPrinter.print(
				"Please provide one of the following arguments:",
				"",
				"exercises",
				"equipment",
				"------"
			);
			return;
		}
		
		switch(input[1]) {
			case "exercises":
				findFavouriteExercise();
				break;
			case "equipment":
				findFavouriteEquipment();
				break;
			default:
				CLIPrinter.print(
					"Please provide one of the following arguments to top3 command:",
					"",
					"exercises",
					"equipment",
					"------"
				);
		}
	}

	private void checkUpdate(String s) {
		String[] input = s.split(" ");

		if (input.length == 1) {
			CLIPrinter.print(
				"Please provide one of the following arguments:",
				"",
				"group",
				"workout",
				"------"
			);
			return;
		}
		
		switch(input[1]) {
			case "group":
				updateGroup();
				break;
			case "workout":
				updateWorkout();
				break;
			default:
				CLIPrinter.print(
					"Please provide one of the following arguments to update command:",
					"",
					"group",
					"workout",
					"------"
				);
		}
	}

	private void checkAdd(String s) {
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
				"exercise(s)",
				"group(s)",
				"workout(s)",
				"------"
			);
			return;
		}
		
		switch(input[1]) {
			case "equipment":
				readAllEquipments();
				break;
			case "exercises":
				readAllExercises();
				break;
			case "exercise":
				readExercise();
				break;
			case "groups":
				readAllGroups();
				break;
			case "group":
				readGroup();
				break;
			case "workouts":
				if (input.length == 3) {
					readLatestWorkouts(s);
				} else {
					readAllWorkouts();
				}
				break;
			case "workout":
				readWorkout();
				break;
			default:
				CLIPrinter.print(
					"Please provide one of the following arguments:",
					"",
					"equipment",
					"exercise(s)",
					"group(s)",
					"workout(s)",
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
			case "update":
				checkUpdate(s);
				break;
			case "top3":
				checkFavourite(s);
				break;
			case "performance":
				findPerformance();
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
					"update",
					"top3",
					"performance",
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