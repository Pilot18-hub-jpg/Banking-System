package banking.model;

import java.util.List;

public class InterestScheduler {

    private String schedule;

    public InterestScheduler(String schedule) {
        this.schedule = schedule;
    }

    public void runMonthlyInterest(List<Account> accounts) {

        System.out.println("Running scheduler: " + schedule);

        for (Account account : accounts) {

            if (account instanceof InterestBearing) {

                InterestBearing interestAccount =
                        (InterestBearing) account;

                interestAccount.payInterest();
            }
        }

        System.out.println("Monthly interest processing completed.");
    }
}