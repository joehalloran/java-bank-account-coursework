//********************************************************************
//  Account.java       Author: Lewis/Loftus
//
//
//  Represents a bank account with methods deposit and withdraw.
//********************************************************************


import java.text.NumberFormat;
import java.util.Date;

public class Account
{
    int acctNumber;
    double balance;
    String name;
    Date creationDate;
    double overdraftLimit;      // Task 10

    //-----------------------------------------------------------------
    //  Sets up the account by defining its owner's name and account
    //  number only.
    //  Task 6
    //-----------------------------------------------------------------
    public Account (String x, int y)
    {
        name = x;
        acctNumber = y;
        balance = 0;
        creationDate = new Date();      // Task 9
        overdraftLimit = 100;           // Task 10
    }


    //-----------------------------------------------------------------
    //  Sets up the account by defining its owner's name, account
    //  number, and initial balance.
    //-----------------------------------------------------------------
    public Account (String x, int y, double z)
    {
        name = x;
        acctNumber = y;
        balance = z;
        creationDate = new Date();      // Task 9
        overdraftLimit = 0;             // Task 10
    }

    //-----------------------------------------------------------------
    //  Deposits the specified amount x into the account.
    //-----------------------------------------------------------------
    public void deposit (double x)
    {
        balance = balance + x;
    }

    //-----------------------------------------------------------------
    //  Withdraws the specified amount from the account for no fee.
    //
    //-----------------------------------------------------------------
    public void withdraw (double x)
    {
        withdraw(x, 0);
    }           // Task 4

    //-----------------------------------------------------------------
    //  Withdraws the specified amount from the account and applies
    //  the fee.
    //-----------------------------------------------------------------
    public void withdraw (double x, double fee)
    {
        if (balance + overdraftLimit > (x + fee) ){                  // Task 10
            balance = balance - x - fee;
        } else {
            System.out.println("You have insufficient funds to make this withdrawal"); // Task 3
        }
    }


    //-----------------------------------------------------------------
    //  Returns the current balance of the account.
    //-----------------------------------------------------------------
    public double getBalance ()
    {
        return balance;
    }

    //-----------------------------------------------------------------
    //  Set balance to a specified value.
    //  An additional method to restore balance to a cached value,
    //  in case of incomplete transaction.
    //-----------------------------------------------------------------
    public void setBalance (double value) {
        balance = value;
    }

    //-----------------------------------------------------------------
    //  Returns the creation date of the account                        // Task 9
    //-----------------------------------------------------------------
    public Date getCreationDate ()
    {
        return creationDate;
    }

    //-----------------------------------------------------------------
    //  Returns a one-line description of the account as a string.
    //-----------------------------------------------------------------
    public String toString ()
    {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return (acctNumber + "\t" + name + "\t" + fmt.format(balance));
    }
}