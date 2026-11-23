
package banking.controller;

import banking.BankAdministrator;
import banking.boundary.LoginView;
import banking.boundary.DashboardView;
import banking.boundary.RegistrationView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    private LoginView loginView;
    private BankAdministrator admin;
    private Stage stage;

    public LoginController(LoginView loginView,
                       BankAdministrator admin, Stage stage) {

        this.loginView = loginView;
        this.admin = admin;
        this.stage = stage;

        loginView.getLoginButton().setOnAction(event -> login());
    }

    private void login() {

        String username = loginView.getUsername();
        String password = loginView.getPassword();

        boolean successful = admin.login(username, password);

        if (successful) {

            DashboardView dashboard = new DashboardView();

            dashboard.getRegisterCustomerButton().setOnAction(event -> {

                RegistrationView registrationView = new RegistrationView();

                new RegistrationController(registrationView, admin);

                Scene registrationScene =
                        new Scene(registrationView.getLayout(), 500, 650);

                stage.setTitle("Banking System - Register Customer");
                stage.setScene(registrationScene);
            });

            Scene scene = new Scene(dashboard.getLayout(), 600, 500);

            stage.setTitle("Banking System - Dashboard");
            stage.setScene(scene);

        } else {

            loginView.showMessage("Invalid username or password.");
        }
    }
}