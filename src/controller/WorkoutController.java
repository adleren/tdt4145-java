package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Workout;


public class WorkoutController {

	public static List<Workout> findAll(Connection connection) {
		List<Workout> workouts = new ArrayList<>();

		String query = "select * from Workout";

    	try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("WorkoutID");
				Date datetime = rs.getDate("Datetime");
				Time duration = rs.getTime("Duration");
				int shape = rs.getInt("Shape");
				int performance = rs.getInt("Performance");
				String notes = rs.getString("Notes");

				Workout workout = new Workout(id, datetime, duration, shape, performance, notes);
				workouts.add(workout);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return workouts;
	}

	public static Workout findById(Connection connection, int id) {
		Workout workout = null;

		String query = "select * from Workout where WorkoutID = " + id;

		try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Date datetime = rs.getDate("Datetime");
				Time duration = rs.getTime("Duration");
				int shape = rs.getInt("Shape");
				int performance = rs.getInt("Performance");
				String notes = rs.getString("Notes");

				workout = new Workout(id, datetime, duration, shape, performance, notes);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return workout;
	}

	public static boolean create(Connection connection, Workout workout) {
		String query = "insert into Workout (Datetime,Duration,Shape,Performance,Notes) values ('"
		+ workout.getDatetime() + "','"
		+ workout.getDuration() + "','"
		+ workout.getShape() + "','"
		+ workout.getPerformance() + "','"
		+ workout.getNotes() + "')";
		
		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean deleteById(Connection connection, int id) {
		String query = "delete from Workout where WorkoutID = " + id;
		
		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}