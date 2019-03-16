package model;

import java.sql.Date;
import java.sql.Time;


public class Workout extends Model{

	private Date datetime;
	private Time duration; // i minutter
	private int shape;
	private String notes;

	public Workout(int id, Date datetime, Time duration, int shape, String notes) {
		super(id);

		this.datetime = datetime;
		this.duration = duration;
		this.shape = shape;
		this.notes = notes;
	}

	public Date getDatetime() {
		return datetime;
	}

	public Time getDuration() {
		return duration;
	}

	public int getShape() {
		return shape;
	}

	public String getNotes() {
		return notes;
	}

	@Override
	public String getRowString() {
		return
			this.getId() + "\t"
			+ this.getDatetime() + "\t"
			+ this.getDuration() + "\t"
			+ this.getShape() + "\t"
			+ this.getNotes();
	}

}