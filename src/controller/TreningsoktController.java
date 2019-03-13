package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Treningsokt;

/**
 * TreningsoktController
 */
public class TreningsoktController {

	public static List<Treningsokt> findAll(Connection connection) {
		List<Treningsokt> okter = new ArrayList<>();

		String query = "select * from Treningsøkt";

    	try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("TreningsøktID");
				Date datotid = rs.getDate("Datotid");
				Time varighet = rs.getTime("Varighet");
				int form = rs.getInt("Form");
				String notat = rs.getString("Notat");

				Treningsokt okt = new Treningsokt(id, datotid, varighet, form, notat);
				okter.add(okt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return okter;
	}

	public static Treningsokt findById(Connection connection, int id) {
		Treningsokt okt = null;

		String query = "select * from Treningsøkt where TreningsøktID = " + id;

		try (Statement stmt = connection.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Date datotid = rs.getDate("Datotid");
				Time varighet = rs.getTime("Varighet");
				int form = rs.getInt("Form");
				String notat = rs.getString("Notat");

				okt = new Treningsokt(id, datotid, varighet, form, notat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return okt;
	}
}