package model;


public abstract class Exercise extends Model {

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
		String description = "null";
		String kilos = "null";
		String sets = "null";
		String equipment = "null";

		if (this instanceof FreeExercise) {
			description = ((FreeExercise) this).getDescription();
		} else if (this instanceof EquipmentExercise) {
			kilos = Integer.toString(((EquipmentExercise) this).getKilos());
			sets = Integer.toString(((EquipmentExercise) this).getSets());
			equipment = ((EquipmentExercise) this).getEquipment().getName();
		}

		return this.getId() + "\t" + this.getName() + "\t" + description + "\t" + kilos + "\t" + sets + "\t" + equipment;
	}
}