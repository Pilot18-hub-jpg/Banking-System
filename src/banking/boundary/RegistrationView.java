
package banking.boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class RegistrationView {

    private VBox layout;

    private ComboBox<String> customerTypeBox;

    private TextField usernameField;
    private PasswordField passwordField;
    private TextField customerIdField;
    private TextField addressField;

    private TextField firstNameField;
    private TextField surnameField;
    private TextField idNumberField;
    private CheckBox employedCheckBox;

    private TextField companyNameField;
    private TextField registrationNoField;
    private TextField contactPersonField;

    private Button registerButton;
    private Label messageLabel;

    public RegistrationView() {

        Label heading = new Label("REGISTER CUSTOMER");

        customerTypeBox = new ComboBox<>();
        customerTypeBox.getItems().addAll("Individual", "Company");
        customerTypeBox.setPromptText("Select customer type");

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        customerIdField = new TextField();
        customerIdField.setPromptText("Customer ID");

        addressField = new TextField();
        addressField.setPromptText("Address");

        firstNameField = new TextField();
        firstNameField.setPromptText("First name");

        surnameField = new TextField();
        surnameField.setPromptText("Surname");

        idNumberField = new TextField();
        idNumberField.setPromptText("ID number");

        employedCheckBox = new CheckBox("Employed");

        companyNameField = new TextField();
        companyNameField.setPromptText("Company name");

        registrationNoField = new TextField();
        registrationNoField.setPromptText("Registration number");

        contactPersonField = new TextField();
        contactPersonField.setPromptText("Contact person");

        registerButton = new Button("REGISTER CUSTOMER");

        messageLabel = new Label("");
        individualFields = new VBox(10);
        individualFields.getChildren().addAll(
            firstNameField,
            surnameField,
            idNumberField,
            employedCheckBox
        );

        companyFields = new VBox(10);
        companyFields.getChildren().addAll(
            companyNameField,
            registrationNoField,
            contactPersonField
        );

        layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));

       layout.getChildren().addAll(
                heading,
                customerTypeBox,
                usernameField,
                passwordField,
                customerIdField,
                addressField,
                individualFields,
                companyFields,
                registerButton,
                messageLabel
        );
        individualFields.setVisible(false);
        individualFields.setManaged(false);

        companyFields.setVisible(false);
        companyFields.setManaged(false);

        customerTypeBox.setOnAction(event -> {

            boolean isIndividual =
                    "Individual".equals(customerTypeBox.getValue());

            individualFields.setVisible(isIndividual);
            individualFields.setManaged(isIndividual);

            companyFields.setVisible(!isIndividual);
            companyFields.setManaged(!isIndividual);
        });
    }

    public VBox getLayout() {
        return layout;
    }
    private VBox individualFields;
    private VBox companyFields;

    public String getCustomerType() {
        return customerTypeBox.getValue();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public String getCustomerId() {
        return customerIdField.getText();
    }

    public String getAddress() {
        return addressField.getText();
    }

    public String getFirstName() {
        return firstNameField.getText();
    }

    public String getSurname() {
        return surnameField.getText();
    }

    public String getIdNumber() {
        return idNumberField.getText();
    }

    public boolean isEmployed() {
        return employedCheckBox.isSelected();
    }

    public String getCompanyName() {
        return companyNameField.getText();
    }

    public String getRegistrationNo() {
        return registrationNoField.getText();
    }

    public String getContactPerson() {
        return contactPersonField.getText();
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public void showMessage(String message) {
        messageLabel.setText(message);
    }

}
