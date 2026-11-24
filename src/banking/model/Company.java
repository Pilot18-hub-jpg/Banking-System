package banking.model;

public class Company extends Customer {

    private String companyName;
    private String registrationNo;
    private String contactPerson;

    public Company(String username, String password,
                   String customerId, String address,
                   String companyName, String registrationNo,
                   String contactPerson) {

        super(username, password, customerId, address);

        this.companyName = companyName;
        this.registrationNo = registrationNo;
        this.contactPerson = contactPerson;
    }

    @Override
    public String getDisplayName() {
        return companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public String getContactPerson() {
        return contactPerson;
    }
}