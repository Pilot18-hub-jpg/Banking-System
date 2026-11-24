
package banking;

import banking.view.LoginView;
import banking.controller.LoginController;
import banking.dao.DatabaseManager;
import banking.model.BankAdministrator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BankingApp extends Application {

    @Override
    public void start(Stage stage) {
        DatabaseManager.initializeDatabase();
        DatabaseManager.displayDatabaseRecords();

        // Create a test administrator
        BankAdministrator admin = new BankAdministrator(
                "admin",
                "1234",
                "ST001",
                "Francistown"
        );

        // Create the login screen
        LoginView loginView = new LoginView();

        // Connect the screen to the controller
        new LoginController(loginView, admin, stage);

        // Display the application
        Scene scene = new Scene(loginView.getLayout(), 500, 400);

        stage.setTitle("Banking System - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
