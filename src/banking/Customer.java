
package banking;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer extends User {

    private String customerId;
    private String address;
    private List<Account> accounts;

    public Customer(String username, String password,
                    String customerId, String address) {

        super(username, password);

        this.customerId = customerId;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    public void openAccount(Account acc) {

        if (acc == null) {
            System.out.println("Invalid account.");
            return;
        }

        accounts.add(acc);

        System.out.println("Account " + acc.getAccountNumber()
                + " opened for " + getDisplayName());
    }

    public void closeAccount(Account acc) {
        accounts.remove(acc);
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public boolean authenticate(String pwd) {
        return pwd != null && getPassword().equals(pwd);
    }

    public abstract String getDisplayName();

    public String getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }
}
