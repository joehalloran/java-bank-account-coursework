import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.DoubleSummaryStatistics;

/**
 * Created by j.halloran on 31/05/2017.
 */
public class ManageAccount {
    // TODO Check all cash values as some changed during tests
    Account trump = new Account("Donald Trump", 20230715, 40);
    Account gates = new Account("Bill Gates", 31558040, 500);
    Account cruise = new Account("Tom Cruise", 44003050, 600);
    Account inlandRevenue = new Account("Inland Revenue", 11223344);

    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    NumberFormat stringFormat = NumberFormat.getCurrencyInstance();

    public static void main(String[] args) {
        // create accounts
        ManageAccount accounts = new ManageAccount();
    }

    public ManageAccount() {
        trump.deposit(50);
        System.out.println (trump.toString());

        gates.withdraw(400,0);
        System.out.println (gates.toString());

        trump.deposit(70);
        System.out.println (trump.toString());

        System.out.println();
        System.out.println (trump.toString());
        System.out.println (gates.toString());
        System.out.println (cruise.toString());

        System.out.println (getTotalDeposits());

        deductTax(trump, inlandRevenue);
        deductTax(gates, inlandRevenue);
        deductTax(cruise, inlandRevenue);

        double interestRate = 0.015;
        addInterest(trump, interestRate);
        addInterest(gates, interestRate);
        addInterest(cruise, interestRate);
        addInterest(inlandRevenue, interestRate);

        System.out.println("Trump creation date:\t\t" + trump.getCreationDate());
        System.out.println("Gate creation date:\t\t" + gates.getCreationDate());
        System.out.println("Cruise creation date:\t\t" + cruise.getCreationDate());

    }

    public String getTotalDeposits() {
        double total = trump.getBalance() +  gates.getBalance() + cruise.getBalance();
        return "Total deposits:\t\t" + toString(total);
    }

    public double calculateTax(Account account) {
        double tax = calculatePercentage(account.getBalance(), 0.15);
        return tax;
    }

    public void deductTax(Account citizen, Account taxMan) {
        double citizenReset = citizen.getBalance();
        double taxManReset = taxMan.getBalance();
        try {
            double tax = calculateTax(citizen);
            citizen.withdraw(tax);
            taxMan.deposit(tax);
            System.out.println("Tax paid by " + citizen.name + ":\t\t" + toString(tax));
        } catch (Exception e) {
            System.out.println("Tax payment could not be processed");
            citizen.setBalance(citizenReset);
            taxMan.setBalance(taxManReset);
        }


    }

    public void addInterest(Account account, double interestRate) {
        double interest = calculatePercentage(account.getBalance(), interestRate);
        account.deposit(interest);
        System.out.println("Interest paid to " + account.name + ":\t\t" + toString(interest));
    }

    private double calculatePercentage(double value, double percentage) {
        double output = Double.valueOf(decimalFormat.format(value * percentage));
        return output;
    }

    private String toString (double value) {
        return stringFormat.format(value);
    }

}
