
package banking.view;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class CustomerListView {

    private TableView<CustomerRow> table;
    private VBox layout;
    private Button backButton;

    public CustomerListView() {

        table = new TableView<>();

        TableColumn<CustomerRow, String> idColumn =
                new TableColumn<>("Customer ID");
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<CustomerRow, String> nameColumn =
                new TableColumn<>("Customer Name");
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<CustomerRow, String> addressColumn =
                new TableColumn<>("Address");
        addressColumn.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        table.getColumns().addAll(
                idColumn, nameColumn, addressColumn);

        layout = new VBox(table);
        layout.setSpacing(10);
        backButton = new Button("Back to Dashboard");

        layout.getChildren().add(backButton);
    }

    public void setCustomers(ObservableList<CustomerRow> customers) {
        table.setItems(customers);
    }

    public VBox getLayout() {
        return layout;
    }

    public Button getBackButton() {
        return backButton;
    }
}
