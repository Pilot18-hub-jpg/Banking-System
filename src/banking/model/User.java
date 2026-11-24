package banking.model;

public abstract class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String enteredUsername, String enteredPassword) {
        if (username.equals(enteredUsername)
                && password.equals(enteredPassword)) {
            System.out.println(username + " successfully logged in.");
            return true;
        }

        System.out.println("Invalid username or password.");
        return false;
    }

    public void logout() {
        System.out.println(username + " logged out.");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}