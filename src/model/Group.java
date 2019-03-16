package model;


public class Group extends Model{

	private String name;

	public Group(int id, String name) {
		super(id);

		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getRowString() {
		return
			this.getId() + "\t"
			+ this.getName();
	}
	
}