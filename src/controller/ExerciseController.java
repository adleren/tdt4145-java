package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Equipment;
import model.EquipmentExercise;
import model.FreeExercise;
import util.CLIPrinter;
import model.Exercise;


public class ExerciseController {

	public static List<Exercise> findAll(Connection connection) {
		List<Exercise> exercises = new ArrayList<>();
		
		// Finn fri-øvelser
		try (Statement stmt = connection.createStatement()) {

			String query = "select * from Exercise natural join FreeExercise";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("ExerciseID");
				String name = rs.getString("Name");
				String description = rs.getString("Description");

				Exercise exercise = new FreeExercise(id, name, description);
				exercises.add(exercise);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Finn apparat-øvelser
		try (Statement stmt = connection.createStatement()) {

			String query = "select * from Exercise natural join EquipmentExercise";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("ExerciseID");
				String name = rs.getString("Name");
				int kilos = rs.getInt("Kilos");
				int sets = rs.getInt("Sets");
				int equipmentId = rs.getInt("EquipmentID");

				Equipment equipment = EquipmentController.findById(connection, equipmentId);
				
				Exercise exercise = new EquipmentExercise(id, name, kilos, sets, equipment);
				exercises.add(exercise);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		exercises.sort((Exercise e1, Exercise e2) -> e2.getId() - e1.getId());

		return exercises;
	}

	public static Exercise findById(Connection connection, int id) {
		Exercise exercise = null;

		// Finn fri-øvelse
		try (Statement stmt = connection.createStatement()) {

			String query = "select * from Exercise natural join FreeExercise where ExerciseID = " + id;
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString("Name");
				String description = rs.getString("Description");

				exercise = new FreeExercise(id, name, description);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Returner hvis vi fant en
		if (exercise != null) {
			return exercise;
		}

		// Hvis ikke, let etter apparat-øvelse
		try (Statement stmt = connection.createStatement()) {

			String query = "select * from Exercise natural join EquipmentExercise where ExerciseID = " + id;
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString("Name");
				int kilos = rs.getInt("Kilos");
				int sets = rs.getInt("Sets");
				int equipmentId = rs.getInt("EquipmentID");

				Equipment equipment = EquipmentController.findById(connection, equipmentId);

				exercise = new EquipmentExercise(id, name, kilos, sets, equipment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exercise;
	}

	public static void create(Connection connection, Exercise exercise) {
		try (Statement stmt = connection.createStatement()) {
			if (exercise instanceof FreeExercise) {

			} else if (exercise instanceof EquipmentExercise) {

			} else {
				CLIPrinter.print("Equipment type must be specified!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}