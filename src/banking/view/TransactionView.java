
package banking.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class TransactionView {

    private TextField accountNumberField;
    private TextField amountField;
    private ComboBox<String> transactionTypeBox;
    private Button processButton;
    private Button backButton;
    private VBox layout;

    public TransactionView() {

        Label title = new Label("Deposit / Withdraw");

        accountNumberField = new TextField();
        accountNumberField.setPromptText("Account Number");

        amountField = new TextField();
        amountField.setPromptText("Amount");

        transactionTypeBox = new ComboBox<>();
        transactionTypeBox.getItems().addAll("Deposit", "Withdraw");
        transactionTypeBox.setPromptText("Select Transaction Type");

        processButton = new Button("Process Transaction");
        backButton = new Button("Back to Dashboard");

        layout = new VBox(12,
                title,
                new Label("Account Number"),
                accountNumberField,
                new Label("Amount"),
                amountField,
                transactionTypeBox,
                processButton,
                backButton
        );

        layout.setPadding(new Insets(25));
    }

    public String getAccountNumber() {
        return accountNumberField.getText();
    }

    public String getAmount() {
        return amountField.getText();
    }

    public String getTransactionType() {
        return transactionTypeBox.getValue();
    }

    public Button getProcessButton() {
        return processButton;
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
