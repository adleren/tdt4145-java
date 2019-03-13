package app;

import java.sql.SQLException;
import java.util.List;

import controller.OvelseController;
import model.ApparatOvelse;
import model.FriOvelse;
import model.Ovelse;
import util.DatabaseManager;

public class App {

    private DatabaseManager dbManager;

    public App() {
        try {
            this.dbManager = new DatabaseManager();

            List<Ovelse> ovelser = OvelseController.findAll(this.dbManager.getConnection());

            System.out.println("Liste av alle øvelser\nID\tNavn\t\tType\n");
            ovelser.stream().forEach(o -> {
                if (o instanceof FriOvelse) {
                    System.out.println(o.getId() + "\t" + o.getNavn() + "\t\tFriØvelse");
                } else if (o instanceof ApparatOvelse) {
                    System.out.println(o.getId() + "\t" + o.getNavn() + "\t\tApparatØvelse");
                    System.out.println("Bruker apparat: " + ((ApparatOvelse) o).getApparat().getNavn());
                } else {
                    System.out.println("Something went wrong...");
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}