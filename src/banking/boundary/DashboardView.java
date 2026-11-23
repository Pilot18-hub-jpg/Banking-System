
package banking.boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardView {

    private VBox layout;
    private Button registerCustomerButton;
    private Button viewCustomersButton;

    public DashboardView() {

        Label heading = new Label("BANKING SYSTEM");
        Label subtitle = new Label("Administrator Dashboard");

        registerCustomerButton = new Button("Register Customer");
        viewCustomersButton = new Button("View Customers");
        Button openAccountButton = new Button("Open Account");
        Button viewAccountsButton = new Button("View Accounts");
        Button transactionsButton = new Button("Transactions");
        Button logoutButton = new Button("Logout");

        layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        layout.getChildren().addAll(
                heading,
                subtitle,
                registerCustomerButton,
                viewCustomersButton,
                openAccountButton,
                viewAccountsButton,
                transactionsButton,
                logoutButton
        );
    }

    public VBox getLayout() {
        return layout;
    }
    public Button getRegisterCustomerButton() {
        return registerCustomerButton;
    }

}
