package app;

import java.sql.SQLException;

import util.DatabaseManager;

public class App {

    private DatabaseManager dbManager;

    public App() {
        try {
            this.dbManager = new DatabaseManager();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}