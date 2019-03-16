package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Group;


public class GroupController {

	public static List<Group> findAll(Connection connection) {
		List<Group> groups = new ArrayList<>();

		String query = "select * from ExerciseGroup";

    	try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("GroupID");
				String name = rs.getString("Name");

				Group group = new Group(id, name);
				groups.add(group);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return groups;
	}

	public static Group findById(Connection connection, int id) {
		Group group = null;

		String query = "select * from ExerciseGroup where GroupID = " + id;

		try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString("Name");

				group = new Group(id, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return group;
	}
}