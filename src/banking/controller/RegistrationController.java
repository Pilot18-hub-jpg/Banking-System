
package banking.controller;

import banking.*;
import banking.boundary.RegistrationView;

public class RegistrationController {

    private RegistrationView registrationView;
    private BankAdministrator admin;

    // Constructor
    public RegistrationController(RegistrationView registrationView,
                                  BankAdministrator admin) {

        this.registrationView = registrationView;
        this.admin = admin;

        registrationView.getRegisterButton()
                .setOnAction(event -> registerCustomer());
    }

    // Registration method
    private void registerCustomer() {

        String type = registrationView.getCustomerType();

        if (type == null) {
            registrationView.showMessage("Please select customer type.");
            return;
        }

        String username = registrationView.getUsername();
        String password = registrationView.getPassword();
        String customerId = registrationView.getCustomerId();
        String address = registrationView.getAddress();

        if (username.isEmpty() || password.isEmpty()
                || customerId.isEmpty() || address.isEmpty()) {

            registrationView.showMessage(
                    "Please fill in all required fields.");
            return;
        }

        if (type.equals("Individual")) {

            String firstName = registrationView.getFirstName();
            String surname = registrationView.getSurname();
            String idNumber = registrationView.getIdNumber();

            if (firstName.isEmpty() || surname.isEmpty()
                    || idNumber.isEmpty()) {

                registrationView.showMessage(
                        "Please complete all individual details.");
                return;
            }

            Individual individual = new Individual(
                    username,
                    password,
                    customerId,
                    address,
                    firstName,
                    surname,
                    idNumber,
                    registrationView.isEmployed()
            );

            admin.registerCustomer(individual);
            DatabaseManager.saveCustomer(individual);

            registrationView.showMessage(
                    "Individual customer registered successfully!");

        } else if (type.equals("Company")) {

            String companyName = registrationView.getCompanyName();
            String registrationNo = registrationView.getRegistrationNo();
            String contactPerson = registrationView.getContactPerson();

            if (companyName.isEmpty() || registrationNo.isEmpty()
                    || contactPerson.isEmpty()) {

                registrationView.showMessage(
                        "Please complete all company details.");
                return;
            }

            Company company = new Company(
                    username,
                    password,
                    customerId,
                    address,
                    companyName,
                    registrationNo,
                    contactPerson
            );

            admin.registerCustomer(company);
            DatabaseManager.saveCustomer(company);

            registrationView.showMessage(
                    "Company registered successfully!");
        }
    }
}
