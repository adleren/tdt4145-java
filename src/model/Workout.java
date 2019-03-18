package model;


public class Workout extends Model{

	private String datetime;
	private String duration;
	private int shape;
	private int performance;
	private String notes;

	public Workout(int id, String datetime, String duration, int shape, int performance, String notes) {
		super(id);

		this.datetime = datetime;
		this.duration = duration;
		this.shape = shape;
		this.performance = performance;
		this.notes = notes;
	}

	public String getDatetime() {
		return datetime;
	}

	public String getDuration() {
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