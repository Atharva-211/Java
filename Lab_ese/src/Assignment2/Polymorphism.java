package Assignment2;

abstract class Account {
	protected double balance;
	
	public Account(double balance) {
		this.balance = balance;
	}
	
	public void deposit (double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println("Deposite : " + amount);
		} else {
			System.out.println("enter valid amount");
		}
	}
	
	public abstract void withdraw(double amount);
}

class SavingsAccount extends Account {
	
	public SavingsAccount(double balance) {
		super(balance);
	}
	
	@Override
	public void withdraw(double amount) {
		if (amount >0 && amount <= balance ) {
			balance -= amount;
			System.out.println("Withdraw : " + amount);
		} else {
			System.out.println("enter valid amount");
		}
	}
	
	//overloadding
	public void withdraw(double amount, String reason) {
		if (amount >0 && amount <= balance ) {
			balance -= amount;
			System.out.println("Withdraw : " + amount + reason);
		} else {
			System.out.println("enter valid amount or " + reason);
		}
	}
	
}

class CheckingAccount extends Account {
	private double overdraft;
	
	public CheckingAccount(double balance, double overdraft) {
		super(balance);
		this.overdraft= overdraft;
	}
	
	@Override
	public void withdraw(double amount) {
		if (amount >0 && (balance + overdraft) >= amount ) {
			balance -= amount;
			System.out.println("Withdraw : " + amount);
			if (balance < 0) {
				System.out.println("overdraft applied " + balance);
			}
		}else {
			System.out.println("invalid funds");
		}
	}
	
	
}

public class Polymorphism {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account Saving = new SavingsAccount(500);
		Account Checking = new CheckingAccount (500 ,200);
		
		System.out.println("Saving account");
		Saving.withdraw(100);
		
		((SavingsAccount)Saving).withdraw(200, "hii");
		
		System.out.println("Checking account");
		Checking.withdraw(600);
	
	}

}