
package banking.boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginView {

    private VBox layout;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label messageLabel;

    public LoginView() {

        Label heading = new Label("BANKING SYSTEM");
        Label subtitle = new Label("Please login to continue");

        usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        loginButton = new Button("LOGIN");

        messageLabel = new Label("");

        layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        layout.getChildren().addAll(
                heading,
                subtitle,
                usernameField,
                passwordField,
                loginButton,
                messageLabel
        );
    }

    public VBox getLayout() {
        return layout;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void showMessage(String message) {
        messageLabel.setText(message);
    }
}
