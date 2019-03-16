package model;


public class FreeExercise extends Exercise {

	private String description;

	public FreeExercise(int id, String name, String description) {
		super(id, name);

		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
}