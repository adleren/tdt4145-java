package model;

/**
 * FriOvelse
 */
public class FriOvelse extends Ovelse {

	private String beskrivelse;

	public FriOvelse(int id, String navn, String beskrivelse) {
		super(id, navn);

		this.beskrivelse = beskrivelse;
	}

	public String getBesktrivelse() {
		return this.beskrivelse;
	}
	
}