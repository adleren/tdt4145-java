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
}