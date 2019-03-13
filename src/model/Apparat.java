package model;

/**
 * Apparat
 */
public class Apparat extends Model {

	private String navn;
	private String beskrivlese;

	public Apparat(int id, String navn, String beskrivelse) {
		super(id);

		this.navn = navn;
		this.beskrivlese = beskrivelse;
	}

	public String getNavn() {
		return this.navn;
	}

	public String getBeskrivelse() {
		return this.beskrivlese;
	}

}