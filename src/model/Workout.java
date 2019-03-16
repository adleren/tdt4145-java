package model;

import java.sql.Date;
import java.sql.Time;


public class Workout {

	private int id;
	private Date datetime;
	private Time duration; // i minutter
	private int shape;
	private String note;

	public Workout(int id, Date datetime, Time duration, int shape, String note) {
		this.id = id;
		this.datetime = datetime;
		this.duration = duration;
		this.shape = shape;
		this.note = note;
	}

	public int getId() {
		return id;
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

	public String getNote() {
		return note;
	}

}