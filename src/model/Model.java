package model;


public abstract class Model {

	protected int id;

	public Model(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public abstract String getRowString();
}