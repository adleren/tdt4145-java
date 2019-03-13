package model;

import java.sql.Date;
import java.sql.Time;

/**
 * TreningsOkt
 */
public class Treningsokt {

	private int id;
	private Date datotid;
	private Time varighet; // i minutter
	private int form;
	private String notat;

	public Treningsokt(int id, Date datotid, Time varighet, int form, String notat) {
		this.id = id;
		this.datotid = datotid;
		this.varighet = varighet;
		this.form = form;
		this.notat = notat;
	}

	public int getId() {
		return id;
	}

	public Date getDatotid() {
		return datotid;
	}

	public Time getVarighet() {
		return varighet;
	}

	public int getForm() {
		return form;
	}

	public String getNotat() {
		return notat;
	}

}