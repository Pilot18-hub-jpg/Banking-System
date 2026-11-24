
package banking.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardView {

    private VBox layout;
    private Button registerCustomerButton;
    private Button viewCustomersButton;
    private Button openAccountButton;
    private Button viewAccountsButton;
    private Button transactionsButton;
    private Button depositWithdrawButton;
    private Button logoutButton;

    public DashboardView() {

        Label heading = new Label("BANKING SYSTEM");
        Label subtitle = new Label("Administrator Dashboard");

        registerCustomerButton = new Button("Register Customer");
        viewCustomersButton = new Button("View Customers");
        openAccountButton = new Button("Open Account");
        viewAccountsButton = new Button("View Accounts");
        transactionsButton = new Button("Transaction History");
        depositWithdrawButton = new Button("Deposit / Withdraw");
        logoutButton = new Button("Logout");

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
                depositWithdrawButton,
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

    public Button getViewCustomersButton() {
        return viewCustomersButton;
    }

    public Button getOpenAccountButton() {
        return openAccountButton;
    }

    public Button getViewAccountsButton() {
        return viewAccountsButton;
    }

    public Button getTransactionsButton() {
        return transactionsButton;
    }

    public Button getDepositWithdrawButton() {
        return depositWithdrawButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}
