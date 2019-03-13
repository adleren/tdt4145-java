package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Gruppe;

/**
 * GruppeController
 */
public class GruppeController {

	public static List<Gruppe> findAll(Connection connection) {
		List<Gruppe> grupper = new ArrayList<>();

		String query = "select * from Gruppe";

    	try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("GruppeID");
				String navn = rs.getString("Navn");

				Gruppe gruppe = new Gruppe(id, navn);
				grupper.add(gruppe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return grupper;
	}

	public static Gruppe findById(Connection connection, int id) {
		Gruppe gruppe = null;

		String query = "select * from Gruppe where GruppeId = " + id;

		try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String navn = rs.getString("Navn");

				gruppe = new Gruppe(id, navn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return gruppe;
	}
}