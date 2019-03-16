package app;


public class CLIController {

	public static final String v45 = "'[A-Za-zæøåÆØÅ]{1,45}'";
	public static final String v255 = "'[A-Za-zæøåÆØÅ]{1,255}'";
	public static final String n = "'[0-9]{1,10}'";
	public static final String datetime = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
	public static final String time = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
	
	
	private void addWorkout(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);

	}

	private void addEquipmentExercise(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);

	}

	private void addFreeExercise(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);

	}

	private void addGroup(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);

	}

	private void addEquipment(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);

	}
	
	private void updateWorkout(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);

	}

	private void updateEquipmentExercise(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);

	}

	private void updateFreeExercise(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);

	}

	private void updateGroup(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);

	}

	private void updateEquipment(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);

	}
	
	private void readAllWorkouts() {
		// TODO Auto-generated method stub
		System.out.println("All workouts");

	}

	private void readAllExercises() {
		// TODO Auto-generated method stub
		System.out.println("All exercises");

	}

	private void readAllGroups() {
		// TODO Auto-generated method stub
		System.out.println("All groups");

	}

	private void readAllEquipments() {
		// TODO Auto-generated method stub
		System.out.println("All equipments");

	}

	private void printHelp() {
		System.out.println(
			"Usage:" + "\n"
			+ "\tHelp manual goes here..."
		);
	}

	public void checkAdd(String s) {
		String equipmentRegex = "add equipment[(]"+v45+","+v255+"[)]";
		String groupRegex = "add group[(]"+v45+"[)]";
		String freeExerciseRegex = "add free-exercise[(]"+v45+","+v255+"[)][("+v45+")]*";
		String equipmentExerciseRegex = "add equipment-exercise[(]"+v45+","+n+","+n+"[)][("+v45+")]*";
		String workoutRegex = "add workout[(]"+datetime+","+time+","+n+"[)][("+v45+","+n+")]*";
		
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
				System.out.printf("Please add one of the following objects: \nequipment\ngroup\nfree-exercise\nequipment-exercise\nworkout\n------\n");
		}
	}

	private void checkUpdate(String s) {
		String equipmentRegex = "update equipment[(]"+v45+","+v255+"[)][(]"+v45+","+v255+"[)]";
		String groupRegex = "update group[(]"+v45+"[)][(]"+v45+"[)]";
		String freeExerciseRegex = "update free-exercise[(]"+v45+","+v255+"[)][(]"+v45+","+v255+"[)][("+v45+")]*";
		String equipmentExerciseRegex = "update equipment-exercise[(]"+v45+","+n+","+n+"[)][(]"+v45+","+n+","+n+"[)][("+v45+")]*";
		String workoutRegex = "update workout[(]"+datetime+","+time+","+n+"[)][("+v45+","+n+")]*";
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
				System.out.printf("Please add one of the following objects: \nequipment\ngroup\nfree-exercise\nequipment-exercise\nworkout\n------\n");
		}
	}
	
	private void checkDelete(String s) {
		String equipmentRegex = "delete equipment[(]"+v45+"[)]";
		String groupRegex = "delete group[(]"+v45+"[)]";
		String freeExerciseRegex = "delete free-exercise[(]"+v45+"[)]";
		String equipmentExerciseRegex = "delete equipment-exercise[(]"+v45+"[)]";
		String workoutRegex = "delete workout[(]"+datetime+"[)]";
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
				System.out.printf("Please delete one of the following objects: \nequipment\ngroup\nfree-exercise\nequipment-exercise\nworkout\n------\n");
		}
	}
	
	private void checkRead(String s) {
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
				System.out.printf("Please read one of the following objects: \nequipment\ngroup\nfree-exercise\nequipment-exercise\nworkout\n------\n");
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
		default:
			System.out.printf("Please use one of the following commands: \nupdate\ndelete\nread\n------\n");
		}
	}

	public static void main(String[] args) {
		CLIController c = new CLIController();
		c.validateString("add group('Beinøvelse')");
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