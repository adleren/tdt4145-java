package model;

/**
 * Ovelse
 */
public abstract class Ovelse extends Model {

	protected String navn;

	public Ovelse(int id, String navn) {
		super(id);

		this.navn = navn;
	}

	public String getNavn() {
		return this.navn;
	}
}