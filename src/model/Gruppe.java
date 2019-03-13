package model;

/**
 * Gruppe
 */
public class Gruppe {

	private int id;
	private String navn;

	public Gruppe(int id, String navn) {
		this.id = id;
		this.navn = navn;
	}

	public int getId() {
		return id;
	}

	public String getNavn() {
		return navn;
	}
	
}