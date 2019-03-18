package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.EquipmentExercise;
import model.Exercise;
import model.FreeExercise;
import model.Workout;


public class WorkoutController {

	public static List<Workout> findAll(Connection connection) {
		List<Workout> workouts = new ArrayList<>();

		String query = "select * from Workout";

    	try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("WorkoutID");
				String datetime = rs.getString("Datetime");
				String duration = rs.getString("Duration");
				int shape = rs.getInt("Shape");
				int performance = rs.getInt("Performance");
				String notes = rs.getString("Notes");

				Workout workout = new Workout(id, datetime, duration, shape, performance, notes);
				workouts.add(workout);
			}

			rs.close();

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
				String datetime = rs.getString("Datetime");
				String duration = rs.getString("Duration");
				int shape = rs.getInt("Shape");
				int performance = rs.getInt("Performance");
				String notes = rs.getString("Notes");

				workout = new Workout(id, datetime, duration, shape, performance, notes);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return workout;
	}

	public static List<Workout> findLatest(Connection connection, int n) {
		List<Workout> workouts = new ArrayList<>();

		String query = "select * from Workout ORDER BY Datetime DESC LIMIT " + n;

    	try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("WorkoutID");
				String datetime = rs.getString("Datetime");
				String duration = rs.getString("Duration");
				int shape = rs.getInt("Shape");
				int performance = rs.getInt("Performance");
				String notes = rs.getString("Notes");

				Workout workout = new Workout(id, datetime, duration, shape, performance, notes);
				workouts.add(workout);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return workouts;
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

	public static boolean addExerciseToWorkout(Connection connection, int workoutId, Exercise exercise) {
		String query = "insert into ExerciseInWorkout ";

		if (exercise instanceof FreeExercise) {
			query += "(ExerciseID, WorkoutID) values ('"
			+ exercise.getId() + "', '"
			+ workoutId + "')";
		} else if (exercise instanceof EquipmentExercise) {
			query += "(ExerciseID, WorkoutID, Kilos, Sets) values ('"
			+ exercise.getId() + "', '"
			+ workoutId + "', '"
			+ ((EquipmentExercise) exercise).getKilos() + "', '"
			+ ((EquipmentExercise) exercise).getSets() + "')";
		} else {
			return false;
		}
		
		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean deleteExerciseFromWorkout(Connection connection, int workoutId, int exerciseId) {
		String query = "delete from ExerciseInWorkout where ExerciseID = " + exerciseId + " AND GroupID = " + workoutId;
		
		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}