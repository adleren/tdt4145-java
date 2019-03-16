package model;


public class Equipment extends Model {

	private String name;
	private String description;

	public Equipment(int id, String name, String description) {
		super(id);

		this.name = name;
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String getRowString() {
		return
			this.getId() + "\t"
			+ this.getName() + "\t"
			+ this.getDescription();
	}

}