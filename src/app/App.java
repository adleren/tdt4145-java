package app;

import java.util.Scanner;

import util.DatabaseManager;

public class App {

    private DatabaseManager dbManager;
    private CLIController cliController;

    public App() {
        try (Scanner scanner = new Scanner(System.in)) {
            this.dbManager = new DatabaseManager();
            this.cliController = new CLIController(this.dbManager.getConnection());

            System.out.println("Welcome to your workout diary!\n");

            while (scanner.hasNextLine()) {
                cliController.validateString(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}