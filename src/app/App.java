package app;

import java.sql.SQLException;

import controller.ApparatController;

public class App {

    private DatabaseManager dbManager;

    public App() {
        try {
            this.dbManager = new DatabaseManager();

            ApparatController apc = new ApparatController(this.dbManager.getConnection());
            apc.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}