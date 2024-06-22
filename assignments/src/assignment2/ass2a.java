package assignment2;

class Account {
    protected double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit: " + amount);
        displayBalance();
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal: " + amount);
        } else {
            System.out.println("Insufficient funds");
        }
        displayBalance();
    }

    protected void displayBalance() {
        System.out.println("Current balance: " + balance);
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        withdraw(amount, "General Withdraw");
    }

    public void withdraw(double amount, String reason) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdraw: " + amount + " for " + reason);
        } else {
            System.out.println("Insufficient funds");
        }
        displayBalance();
    }
}

class CheckingAccount extends Account {
    private double overdraftLimits;

    public CheckingAccount(double initialBalance, double overdraftLimits) {
        super(initialBalance);
        this.overdraftLimits = overdraftLimits;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance + overdraftLimits) {
            balance -= amount;
            System.out.println("Withdraw: " + amount + " with overdraft protection");
        } else {
            System.out.println("Overdraft limit exceeded");
        }
        displayBalance();
    }
}

public class ass2a {
    public static void main(String[] args) {
        // Polymorphic Demonstration
        Account savingAccount = new SavingsAccount(1000);
        Account checkingAccount = new CheckingAccount(1500, 500);

        System.out.println("Savings account withdrawal: ");
        savingAccount.withdraw(200);
        System.out.println();

        System.out.println("Checking account withdrawal: ");
        checkingAccount.withdraw(200);
    }
}
