//4.	Create a base class Account featuring methods deposit() and withdraw(). 
//	These methods should be overridden by derived classes to execute specific 
//	actions tailored to different account types.
//
//a.	Method Overloading:
//	Derive a class SavingsAccount from Account. Implement method overloading for withdraw() by introducing 
//	an additional parameter to specify the reason for withdrawal.
//
//b.	Method Overriding:
//
//	Derive a class CheckingAccount from Account. Implement method overriding for withdraw() within the 
//	CheckingAccount class, introducing overdraft protection.
//
//c.	Polymorphic Demonstration:
//	i.	Instantiate objects for both SavingsAccount and CheckingAccount.
//	
//	ii.	Illustrate polymorphism by invoking the withdraw() method on both objects, highlighting distinct behaviors 
//	based on the account type.


package assignment;

class Account {
    protected double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
        displayBalance();
    }

    // Method to be overridden by derived classes
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds");
        }
        displayBalance();
    }

    public void displayBalance() {
        System.out.println("Current Balance: $" + balance);
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    // Method overloading to add a reason parameter
    public void withdraw(double amount, String reason) {
        super.withdraw(amount);
        System.out.println("Reason for withdrawal: " + reason);
    }
}

class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(double initialBalance, double overdraftLimit) {
        super(initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    // Method overriding with overdraft protection
    @Override
    public void withdraw(double amount) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Overdraft limit exceeded");
        }
        displayBalance();
    }
}

public class ass1d {
    public static void main(String[] args) {
        // Polymorphic demonstration

        // Instantiate SavingsAccount
        SavingsAccount savingsAccount = new SavingsAccount(1000);

        // Invoke withdraw() with reason
        savingsAccount.withdraw(500, "Emergency");

        System.out.println();

        // Instantiate CheckingAccount with overdraft limit of $200
        CheckingAccount checkingAccount = new CheckingAccount(1000, 200);

        // Invoke withdraw() with overdraft protection
        checkingAccount.withdraw(1200);
    }
}
