package controller;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ApparatController
 */
public class ApparatController {

	private Connection connection;

	public ApparatController(Connection c) {
		this.connection = c;
	}

	public void getAll() {
		Statement stmt = null;
		String query = "select * from Apparat";

    	try {
        	stmt = this.connection.createStatement();
        	ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String navn = rs.getString("Navn");
				System.out.println("Apparat: " + navn);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
    	}
	}
}