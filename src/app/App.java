package app;

import java.sql.SQLException;
import java.util.List;

import controller.ExerciseController;
import model.EquipmentExercise;
import model.FreeExercise;
import model.Exercise;
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