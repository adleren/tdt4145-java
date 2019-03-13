package app;

import java.sql.SQLException;
import java.util.List;

import controller.ApparatController;
import model.Apparat;

public class App {

    private DatabaseManager dbManager;

    public App() {
        try {
            this.dbManager = new DatabaseManager();

            ApparatController apc = new ApparatController(this.dbManager.getConnection());
            List<Apparat> apparater = apc.getAll();

            System.out.println("Liste av alle apparater\nID\tNavn\n");
            apparater.stream().forEach(a -> System.out.println(a.getId() + "\t" + a.getNavn()));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}