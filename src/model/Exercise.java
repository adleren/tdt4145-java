package model;


public class Exercise extends Model {

	protected String name;

	public Exercise(int id, String name) {
		super(id);

		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String getRowString() {
		String type = "N/A";
		String description = "N/A";
		String kilos = "N/A";
		String sets = "N/A";
		String equipment = "N/A";

		if (this instanceof FreeExercise) {
			type = "Free";
			description = ((FreeExercise) this).getDescription();
		} else if (this instanceof EquipmentExercise) {
			type = "Equipment";
			kilos = Integer.toString(((EquipmentExercise) this).getKilos());
			sets = Integer.toString(((EquipmentExercise) this).getSets());
			equipment = ((EquipmentExercise) this).getEquipment().getName();
		} else {
			return this.getId() + "\t"
			+ this.getName();
		}

		return
			this.getId()+ "\t"
			+ this.getName() + "\t"
			+ type + "\t"
			+ description + "\t"
			+ kilos + "\t"
			+ sets + "\t"
			+ equipment;
	}
}