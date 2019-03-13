package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Apparat;
import model.ApparatOvelse;
import model.FriOvelse;
import model.Ovelse;

/**
 * OvelseController
 */
public class OvelseController {

	public static List<Ovelse> findAll(Connection connection) {
		List<Ovelse> ovelser = new ArrayList<>();
		
		// Finn fri-øvelser
		try (Statement stmt = connection.createStatement()) {

			String query = "select * from Øvelse natural join FriØvelse";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("ØvelseID");
				String navn = rs.getString("Navn");
				String beskrivelse = rs.getString("Beskrivelse");

				Ovelse ovelse = new FriOvelse(id, navn, beskrivelse);
				ovelser.add(ovelse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Finn apparat-øvelser
		try (Statement stmt = connection.createStatement()) {

			String query = "select * from Øvelse natural join ApparatØvelse";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("ØvelseID");
				String navn = rs.getString("Navn");
				int kilo = rs.getInt("AntallKilo");
				int sett = rs.getInt("AntallSett");
				int apparatId = rs.getInt("ApparatID");

				Apparat apparat = ApparatController.findById(connection, apparatId);
				
				Ovelse ovelse = new ApparatOvelse(id, navn, kilo, sett, apparat);
				ovelser.add(ovelse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ovelser;
	}

	public static Ovelse findById(Connection connection, int id) {
		Ovelse ovelse = null;

		// Finn fri-øvelse
		try (Statement stmt = connection.createStatement()) {

			String query = "select * from Øvelse natural join FriOvelse where ØvelseID = " + id;
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String navn = rs.getString("Navn");
				String beskrivelse = rs.getString("Beskrivelse");

				ovelse = new FriOvelse(id, navn, beskrivelse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Returner hvis vi fant en
		if (ovelse != null) {
			return ovelse;
		}

		// Hvis ikke, let etter apparat-øvelse
		try (Statement stmt = connection.createStatement()) {

			String query = "select * from Øvelse natural join ApparatØvelse where ØvelseID = " + id;
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String navn = rs.getString("Navn");
				int kilo = rs.getInt("AntallKilo");
				int sett = rs.getInt("AntallSett");
				int apparatId = rs.getInt("ApparatID");

				Apparat apparat = ApparatController.findById(connection, apparatId);

				ovelse = new ApparatOvelse(id, navn, kilo, sett, apparat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ovelse;
	}
}