//********************************************************************
//  Account.java       Author: Lewis/Loftus
//
//
//  Represents a bank account with methods deposit and withdraw.
//********************************************************************

import java.text.NumberFormat;

public class Account
{
    int acctNumber;
    double balance;
    String name;

    //-----------------------------------------------------------------
    //  Sets up the account by defining its owner's name and account
    //   number only.
    //-----------------------------------------------------------------
    public Account (String x, int y)
    {
        name = x;
        acctNumber = y;
        balance = 0;
    }


    //-----------------------------------------------------------------
    //  Sets up the account by defining its owner's name, account
    //   number, and initial balance.
    //-----------------------------------------------------------------
    public Account (String x, int y, double z)
    {
        name = x;
        acctNumber = y;
        balance = z;
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
        int fee = 0;
        withdraw(x, fee);
    }

    //-----------------------------------------------------------------
    //  Withdraws the specified amount from the account and applies
    //  the fee.
    //-----------------------------------------------------------------
    public void withdraw (double x, double fee)
    {
        if (balance > (x + fee) ){
            balance = balance - x - fee;
        } else {
            System.out.println("You have insufficient funds to make this withdrawal");
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
    //  Returns a one-line description of the account as a string.
    //-----------------------------------------------------------------
    public String toString ()
    {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return (acctNumber + "\t" + name + "\t" + fmt.format(balance));
    }
}