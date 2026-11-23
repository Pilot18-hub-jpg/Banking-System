package banking;

public class BankAdministrator extends User {

    private String staffId;
    private String branch;

    public BankAdministrator(String username, String password,
                             String staffId, String branch) {
        super(username, password);
        this.staffId = staffId;
        this.branch = branch;
    }

    public void registerCustomer(Customer c) {
        System.out.println("Admin (" + staffId
                + ") registered customer: " + c.getDisplayName());
    }

    public void openAccountFor(Customer c, Account a) {
        c.openAccount(a);
    }

    public void closeAccount(Customer c, Account a) {
        c.closeAccount(a);
        System.out.println("Admin closed account: " + a.getAccountNumber());
    }
}