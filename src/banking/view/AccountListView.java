
package banking.view;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;

public class AccountListView {

    private TableView<AccountRow> table;
    private Button backButton;
    private VBox layout;

    public AccountListView() {

        Label title = new Label("Bank Accounts");

        table = new TableView<>();

        TableColumn<AccountRow, String> numberColumn =
                new TableColumn<>("Account Number");
        numberColumn.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getAccountNumber()));

        TableColumn<AccountRow, String> typeColumn =
                new TableColumn<>("Account Type");
        typeColumn.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getAccountType()));

        TableColumn<AccountRow, String> customerColumn =
                new TableColumn<>("Customer ID");
        customerColumn.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getCustomerId()));

        TableColumn<AccountRow, String> balanceColumn =
                new TableColumn<>("Balance");
        balanceColumn.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getBalance()));

        table.getColumns().addAll(
                numberColumn,
                typeColumn,
                customerColumn,
                balanceColumn);

        backButton = new Button("Back to Dashboard");

        layout = new VBox(12, title, table, backButton);
        layout.setStyle("-fx-padding: 20;");
    }

    public void setAccounts(ObservableList<AccountRow> accounts) {
        table.setItems(accounts);
    }

    public Button getBackButton() {
        return backButton;
    }

    public VBox getLayout() {
        return layout;
    }
}
