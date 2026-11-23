package banking;

public class Individual extends Customer {
    private String firstName;
    private String surname;
    private String idNumber;
    private boolean employed;

    public Individual(String username, String password, String customerId, String address, 
                      String firstName, String surname, String idNumber, boolean employed) {
        super(username, password, customerId, address);
        this.firstName = firstName;
        this.surname = surname;
        this.idNumber = idNumber;
        this.employed = employed;
    }

    @Override

    public String getDisplayName() {
        return firstName + " " + surname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public boolean isEmployed() {
        return employed;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }
}