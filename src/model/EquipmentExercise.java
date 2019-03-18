package model;


public class EquipmentExercise extends Exercise {

	private int kilos;
	private int sets;
	private Equipment equipment;

	public EquipmentExercise(int id, String name, int kilos, int sets, Equipment equipment) {
		super(id, name);

		this.kilos = kilos;
		this.sets = sets;
		this.equipment = equipment;
	}

	public int getKilos() {
		return kilos;
	}

	public void setKilos(int kilos) {
		this.kilos = kilos;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public Equipment getEquipment() {
		return equipment;
	}
	
}