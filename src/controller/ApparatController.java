package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Apparat;

/**
 * ApparatController
 * 
 */
public class ApparatController {

	public static List<Apparat> findAll(Connection connection) {
		List<Apparat> apparater = new ArrayList<>();

		String query = "select * from Apparat";

    	try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("ApparatID");
				String navn = rs.getString("Navn");
				String beskrivelse = rs.getString("Beskrivelse");

				Apparat apparat = new Apparat(id, navn, beskrivelse);
				apparater.add(apparat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return apparater;
	}

	public static Apparat findById(Connection connection, int id) {
		Apparat apparat = null;

		String query = "select * from Apparat where ApparatID = " + id;

		try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String navn = rs.getString("Navn");
				String beskrivelse = rs.getString("Beskrivelse");

				apparat = new Apparat(id, navn, beskrivelse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return apparat;
	}

}