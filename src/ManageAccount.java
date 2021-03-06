import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * ManageAccount class manages 4 accounts (trump, gate, cruise, inlandRevenue)
 * based on instructions from Task 2: Bank Account Managenment
 */
public class ManageAccount {
    // Initialise formators used for cash values
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    NumberFormat stringFormat = NumberFormat.getCurrencyInstance();

    // Initialise accouts                                       // Task 1.i
    Account trump = new Account("Donald Trump", 20230715, 400);
    Account gates = new Account("Bill Gates", 31558040, 500);
    Account cruise = new Account("Tom Cruise", 44003050, 600);
    Account inlandRevenue = new Account("Inland Revenue", 11223344); // Task 6 - no initial deposit


    public static void main(String[] args) {
        // create accounts
        ManageAccount accounts = new ManageAccount();
    }

    /**
     * Executes of tasks
     */
    public ManageAccount() {
        trump.deposit(50);                      // Task 1.ii
        System.out.println (trump.toString());

        gates.withdraw(400,0);              // Task 1.iii
        System.out.println (gates.toString());

        trump.deposit(75);                      // Task 1.iv
        System.out.println (trump.toString());

        System.out.println();                      // Task 1.v
        System.out.println (trump.toString());
        System.out.println (gates.toString());
        System.out.println (cruise.toString());

        System.out.println(" ");                    // Task 2 - total deposits
        System.out.println (getTotalDeposits());

        System.out.println();                       // Task 7 - pay taxes
        deductTax(trump, inlandRevenue);
        deductTax(gates, inlandRevenue);
        deductTax(cruise, inlandRevenue);

        System.out.println();                       // Task 8 - add interest
        double interestRate = 0.015;
        addInterest(trump, interestRate);
        addInterest(gates, interestRate);
        addInterest(cruise, interestRate);
        addInterest(inlandRevenue, interestRate);

        System.out.println();                       // Task 9 - creation date
        System.out.println("Trump creation date:\t\t" + trump.getCreationDate());
        System.out.println("Gate creation date:\t\t" + gates.getCreationDate());
        System.out.println("Cruise creation date:\t\t" + cruise.getCreationDate());

    }

    //-----------------------------------------------------------------
    //  gets total deposits of trump, gates, and cruise accounts combined
    //  Task 2
    //-----------------------------------------------------------------
    public String getTotalDeposits() {
        double total = trump.getBalance() +  gates.getBalance() + cruise.getBalance();
        return "Total deposits:\t\t" + toString(total);
    }

    //-----------------------------------------------------------------
    //  Caculates the tax due for a given account
    //  Task 5
    //-----------------------------------------------------------------
    public double calculateTax(Account account) {
        double tax = calculatePercentage(account.getBalance(), 0.15);
        return tax;
    }

    //-----------------------------------------------------------------
    //  Withdraws tax from citizen and pays to taxMan.
    //  Uses a try - catch statement (with resets) to eliminate the possibilty
    //  that the withdrawal from citizen may complete, but deposit in taxMan fails.
    //  Task 5
    //-----------------------------------------------------------------
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

    //-----------------------------------------------------------------
    //  Adds interest to account
    //  Task 8
    //-----------------------------------------------------------------
    public void addInterest(Account account, double interestRate) {
        double interest = calculatePercentage(account.getBalance(), interestRate);
        account.deposit(interest);
        System.out.println("Interest paid to " + account.name + ":\t\t" + toString(interest));
    }

    //-----------------------------------------------------------------
    //  Utility function to handle correct rounding to 2 decimal places
    //  when calculating the percentage of a cash value
    //  Used in Task 5 (tax) and Task 8 (interest)
    //-----------------------------------------------------------------
    private double calculatePercentage(double value, double percentage) {
        double output = Double.valueOf(decimalFormat.format(value * percentage));
        return output;
    }

    //-----------------------------------------------------------------
    //  Utility function to correctly format money for console printing
    //-----------------------------------------------------------------
    private String toString (double value) {
        return stringFormat.format(value);
    }

}
