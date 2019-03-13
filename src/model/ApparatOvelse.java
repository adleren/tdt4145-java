package model;

/**
 * ApparatOvelse
 */
public class ApparatOvelse extends Ovelse {

	private int kilo;
	private int sett;
	private Apparat apparat;

	public ApparatOvelse(int id, String navn, int kilo, int sett, Apparat apparat) {
		super(id, navn);

		this.kilo = kilo;
		this.sett = sett;
		this.apparat = apparat;
	}

	public int getKilo() {
		return kilo;
	}

	public int getSett() {
		return sett;
	}

	public Apparat getApparat() {
		return apparat;
	}
	
}