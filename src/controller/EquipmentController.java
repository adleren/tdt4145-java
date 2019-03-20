package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Equipment;


public class EquipmentController {

	public static List<Equipment> findAll(Connection connection) {
		List<Equipment> equipments = new ArrayList<>();

		String query = "select * from Equipment";

    	try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("EquipmentID");
				String name = rs.getString("Name");
				String description = rs.getString("Description");

				Equipment equipment = new Equipment(id, name, description);
				equipments.add(equipment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return equipments;
	}

	public static Equipment findById(Connection connection, int id) {
		Equipment equipment = null;

		String query = "select * from Equipment where EquipmentID = " + id;

		try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString("Name");
				String description = rs.getString("Description");

				equipment = new Equipment(id, name, description);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return equipment;
	}

	public static boolean create(Connection connection, Equipment equipment) {
		String query = "insert into Equipment (Name, Description) values ('"
		+ equipment.getName() + "','"
		+ equipment.getDescription() + "');";

		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean updateById(Connection connection, int id, Equipment updatedEquipment) {
		String query = "UPDATE Equipment "
		+ "SET "
		+ "Name = " + updatedEquipment.getName() + ","
		+ "Description = " + updatedEquipment.getDescription() + " "
		+ "WHERE EquipmentId = " + id + ";";

		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean deleteById(Connection connection, int id) {
		String query = "delete from Equipment where EquipmentId = "
		+ id + ";";

		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static Map<Equipment, Integer> findFavouriteEquipment(Connection connection) {
		Map<Equipment, Integer> equipments = new HashMap<>();

		String query = 
		"SELECT Equipment.EquipmentID AS ID, Equipment.Name AS Name, COUNT(Equipment.EquipmentID) AS Occurances "
		+ "FROM Equipment "
		+ "INNER JOIN EquipmentExercise "
		+ "INNER JOIN Exercise "
		+ "INNER JOIN ExerciseInWorkout "
		+ "GROUP BY ID "
		+ "ORDER BY Occurances "
		+ "LIMIT 3;";

		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				int occurances = rs.getInt("Occurances");
				
				Equipment equipment = new Equipment(id, name, "");
				equipments.put(equipment, occurances);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipments;
	}

}