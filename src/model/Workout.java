package model;

import java.sql.Date;
import java.sql.Time;


public class Workout extends Model{

	private Date datetime;
	private Time duration; // i minutter
	private int shape;
	private int performance;
	private String notes;

	public Workout(int id, Date datetime, Time duration, int shape, int performance, String notes) {
		super(id);

		this.datetime = datetime;
		this.duration = duration;
		this.shape = shape;
		this.performance = performance;
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

	public int getPerformance() {
		return this.performance;
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
			+ this.getShape() + "/10" + "\t"
			+ this.getPerformance() + "/10" + "\t"
			+ this.getNotes();
	}

}