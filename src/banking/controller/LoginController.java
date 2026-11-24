
package banking.controller;

import banking.model.BankAdministrator;
import banking.model.Customer;
import banking.model.Account;
import banking.model.SavingsAccount;
import banking.model.ChequeAccount;
import banking.model.InvestmentAccount;
import banking.dao.DatabaseManager;

import banking.view.LoginView;
import banking.view.DashboardView;
import banking.view.RegistrationView;
import banking.view.CustomerListView;
import banking.view.OpenAccountView;
import banking.view.AccountListView;
import banking.view.TransactionView;
import banking.view.TransactionListView;

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
            Scene scene = new Scene(dashboard.getLayout(), 600, 500);

            // LOGOUT
            dashboard.getLogoutButton().setOnAction(event -> {

                LoginView newLoginView = new LoginView();

                new LoginController(newLoginView, admin, stage);

                stage.setTitle("Banking System - Login");
                stage.setScene(new Scene(newLoginView.getLayout(), 400, 300));
            });

            // REGISTER CUSTOMER
            dashboard.getRegisterCustomerButton().setOnAction(event -> {

                RegistrationView registrationView = new RegistrationView();

                new RegistrationController(registrationView, admin);

                Scene registrationScene =
                        new Scene(registrationView.getLayout(), 500, 650);

                stage.setTitle("Banking System - Register Customer");
                stage.setScene(registrationScene);
            });

            // VIEW CUSTOMERS
            dashboard.getViewCustomersButton().setOnAction(event -> {

                CustomerListView customerView = new CustomerListView();

                customerView.setCustomers(DatabaseManager.getCustomers());

                Scene customerScene =
                        new Scene(customerView.getLayout(), 600, 500);

                stage.setTitle("Banking System - Customers");
                stage.setScene(customerScene);

                customerView.getBackButton().setOnAction(e -> {
                    stage.setTitle("Banking System - Dashboard");
                    stage.setScene(scene);
                });
            });

            // OPEN ACCOUNT
            dashboard.getOpenAccountButton().setOnAction(event -> {

                OpenAccountView openAccountView = new OpenAccountView();

                Scene openAccountScene =
                        new Scene(openAccountView.getLayout(), 500, 500);

                stage.setTitle("Banking System - Open Account");
                stage.setScene(openAccountScene);

                openAccountView.getBackButton().setOnAction(e -> {
                    stage.setTitle("Banking System - Dashboard");
                    stage.setScene(scene);
                });

                openAccountView.getOpenButton().setOnAction(e -> {
                    openAccount(openAccountView);
                });
            });

            // VIEW ACCOUNTS
            dashboard.getViewAccountsButton().setOnAction(event -> {

                AccountListView accountView = new AccountListView();

                accountView.setAccounts(DatabaseManager.getAccounts());

                Scene accountScene =
                        new Scene(accountView.getLayout(), 700, 500);

                stage.setTitle("Banking System - Accounts");
                stage.setScene(accountScene);

                accountView.getBackButton().setOnAction(e -> {
                    stage.setTitle("Banking System - Dashboard");
                    stage.setScene(scene);
                });
            });

            // VIEW TRANSACTIONS
            dashboard.getTransactionsButton().setOnAction(event -> {

                TransactionListView transactionView =
                        new TransactionListView();

                transactionView.setTransactions(
                        DatabaseManager.getTransactions());

                Scene transactionScene =
                        new Scene(transactionView.getLayout(), 800, 500);

                stage.setTitle("Banking System - Transactions");
                stage.setScene(transactionScene);

                transactionView.getBackButton().setOnAction(e -> {
                    stage.setTitle("Banking System - Dashboard");
                    stage.setScene(scene);
                });
            });

            // DEPOSIT / WITHDRAW
            dashboard.getDepositWithdrawButton().setOnAction(event -> {

                TransactionView transactionView = new TransactionView();

                Scene transactionScene =
                        new Scene(transactionView.getLayout(), 500, 500);

                stage.setTitle("Banking System - Deposit / Withdraw");
                stage.setScene(transactionScene);

                transactionView.getBackButton().setOnAction(e -> {
                    stage.setTitle("Banking System - Dashboard");
                    stage.setScene(scene);
                });

                transactionView.getProcessButton().setOnAction(e -> {

                    try {

                        String accountNumber =
                                transactionView.getAccountNumber().trim();

                        String type =
                                transactionView.getTransactionType();

                        String amountText =
                                transactionView.getAmount().trim();

                        if (accountNumber.isEmpty()
                                || type == null
                                || amountText.isEmpty()) {

                            transactionView.showMessage(
                                    "Please fill in all fields.");
                            return;
                        }

                        java.math.BigDecimal amount =
                                new java.math.BigDecimal(amountText);

                        String result = DatabaseManager.processTransaction(
                                accountNumber, type, amount);

                        transactionView.showMessage(result);

                    } catch (NumberFormatException ex) {

                        transactionView.showMessage(
                                "Enter a valid amount.");
                    }
                });
            });

            stage.setTitle("Banking System - Dashboard");
            stage.setScene(scene);

        } else {

            loginView.showMessage("Invalid username or password.");
        }
    }

    // OPEN ACCOUNT METHOD
    private void openAccount(OpenAccountView view) {

        String customerId = view.getCustomerId().trim();
        String accountNumber = view.getAccountNumber().trim();
        String accountType = view.getAccountType();

        if (customerId.isEmpty() || accountNumber.isEmpty()
                || accountType == null) {

            view.showMessage("Please fill in all required fields.");
            return;
        }

        Customer customer =
                DatabaseManager.findCustomerById(customerId);

        if (customer == null) {
            view.showMessage("Customer not found. Check the Customer ID.");
            return;
        }

        Account account;

        if (accountType.equals("Savings")) {

            account = new SavingsAccount(
                    accountNumber, admin.getBranch(), customer);

        } else if (accountType.equals("Cheque")) {

            String employer = view.getEmployerName().trim();
            String address = view.getEmployerAddress().trim();

            if (employer.isEmpty() || address.isEmpty()) {
                view.showMessage("Enter employer name and address.");
                return;
            }

            account = new ChequeAccount(
                    accountNumber, admin.getBranch(),
                    customer, employer, address);

        } else {

            account = new InvestmentAccount(
                    accountNumber, admin.getBranch(), customer);
        }

        boolean saved = DatabaseManager.saveAccount(account);

        if (saved) {
            admin.openAccountFor(customer, account);
            view.showMessage("Account opened successfully!");
        } else {
            view.showMessage(
                    "Account could not be opened. The account number may already exist."
            );
        }
    }
}
