package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

		if (exercise != null) {
			return exercise;
		}

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

	public static boolean create(Connection connection, Exercise exercise) {
		String queryGeneral = "insert into Exercise (Name) values ('" + exercise.getName() + "');";
		String querySpecific = "insert into ";

		int exerciseId = 0;

		try (PreparedStatement stmt = connection.prepareStatement(queryGeneral, new String[]{"ExerciseID"})) {
			stmt.executeUpdate();
			exerciseId = stmt.getGeneratedKeys().getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		try (Statement stmt = connection.createStatement()) {
			if (exercise instanceof FreeExercise) {
				querySpecific += "FreeExercise (ExerciseID, Description) values ('"
				+ exerciseId + "', '"
				+ ((FreeExercise) exercise).getDescription() + "')";
			} else if (exercise instanceof EquipmentExercise) {
				querySpecific += "EquipmentExercise (ExerciseID, Kilos, Sets, EquipmentID) values ('"
				+ exerciseId + "', '"
				+ ((EquipmentExercise) exercise).getKilos() + "', '"
				+ ((EquipmentExercise) exercise).getSets() + "', '"
				+ ((EquipmentExercise) exercise).getEquipment().getId() + "')";
			} else {
				CLIPrinter.print("Equipment type must be specified!");
			}

			stmt.execute(querySpecific);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean deleteById(Connection connection, int id) {
		String query = "delete from Exercise where ExerciseID = " + id;
		
		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}