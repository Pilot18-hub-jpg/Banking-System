
package banking.view;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class OpenAccountView {

    private TextField customerIdField;
    private ComboBox<String> accountTypeBox;
    private TextField accountNumberField;
    private TextField employerNameField;
    private TextField employerAddressField;
    private Button openButton;
    private Button backButton;
    private VBox layout;

    public OpenAccountView() {

        Label title = new Label("Open New Account");

        customerIdField = new TextField();
        customerIdField.setPromptText("Customer ID");

        accountNumberField = new TextField();
        accountNumberField.setPromptText("Account Number");

        accountTypeBox = new ComboBox<>();
        accountTypeBox.getItems().addAll(
                "Savings", "Cheque", "Investment");
        accountTypeBox.setPromptText("Select Account Type");

        employerNameField = new TextField();
        employerNameField.setPromptText("Employer Name (Cheque only)");

        employerAddressField = new TextField();
        employerAddressField.setPromptText("Employer Address (Cheque only)");

        openButton = new Button("Open Account");
        backButton = new Button("Back to Dashboard");

        layout = new VBox(12,
                title,
                customerIdField,
                accountNumberField,
                accountTypeBox,
                employerNameField,
                employerAddressField,
                openButton,
                backButton);

        layout.setStyle("-fx-padding: 25;");
    }

    public String getCustomerId() {
        return customerIdField.getText();
    }

    public String getAccountNumber() {
        return accountNumberField.getText();
    }

    public String getAccountType() {
        return accountTypeBox.getValue();
    }

    public String getEmployerName() {
        return employerNameField.getText();
    }

    public String getEmployerAddress() {
        return employerAddressField.getText();
    }

    public Button getOpenButton() {
        return openButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public VBox getLayout() {
        return layout;
    }

    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
