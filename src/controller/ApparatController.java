package controller;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Apparat;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ApparatController
 * 
 */
public class ApparatController {

	private Connection connection = null;

	public ApparatController(Connection c) {
		this.connection = c;
	}

	public List<Apparat> getAll() {
		List<Apparat> apparater = new ArrayList<>();
		
		Statement stmt = null;
		String query = "select * from Apparat";

    	try {
        	stmt = this.connection.createStatement();
        	ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("ApparatID");
				String navn = rs.getString("Navn");
				String beskrivelse = rs.getString("Beskrivelse");
				
				Apparat apparat = new Apparat(id, navn, beskrivelse);
				apparater.add(apparat);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return apparater;
	}
}