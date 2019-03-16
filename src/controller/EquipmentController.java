package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public static void create(Connection connection, Equipment equipment) {
		String query = "insert into Equipment (Name, Description) values ("
		+ equipment.getName() + ","
		+ equipment.getDescription() + ");";

		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateByName(Connection connection, String name, Equipment updatedEquipment) {
		String query = "UPDATE Equipment "
		+ "SET "
		+ "Name = " + updatedEquipment.getName() + ","
		+ "Description = " + updatedEquipment.getDescription() + " "
		+ "WHERE Name = " + name + ";";

		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteByName(Connection connection, String name) {
		String query = "delete from Equipment where Name = "
		+ name + ");";

		try (Statement stmt = connection.createStatement()) {

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}