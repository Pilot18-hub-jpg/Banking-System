package banking.view;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;

public class TransactionListView {

    private TableView<TransactionRow> table;
    private Button backButton;
    private VBox layout;

    public TransactionListView() {

        Label title = new Label("Transaction History");

        table = new TableView<>();

        TableColumn<TransactionRow, String> idColumn =
                new TableColumn<>("Transaction ID");
        idColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getTransactionId()));

        TableColumn<TransactionRow, String> accountColumn =
                new TableColumn<>("Account Number");
        accountColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getAccountNumber()));

        TableColumn<TransactionRow, String> dateColumn =
                new TableColumn<>("Date");
        dateColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDate()));

        TableColumn<TransactionRow, String> typeColumn =
                new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getType()));

        TableColumn<TransactionRow, String> amountColumn =
                new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getAmount()));

        TableColumn<TransactionRow, String> balanceColumn =
                new TableColumn<>("Balance After");
        balanceColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getBalance()));

        table.getColumns().addAll(
                idColumn, accountColumn, dateColumn,
                typeColumn, amountColumn, balanceColumn);

        backButton = new Button("Back to Dashboard");

        layout = new VBox(12, title, table, backButton);
        layout.setStyle("-fx-padding: 20;");
    }

    public void setTransactions(ObservableList<TransactionRow> transactions) {
        table.setItems(transactions);
    }

    public Button getBackButton() {
        return backButton;
    }

    public VBox getLayout() {
        return layout;
    }
}