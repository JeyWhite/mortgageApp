package space.jeywhite.morthageapp;

import java.lang.Math;

public class Payment {

	private int num;
	private double pay;
	private double loanAmount;
	private double interestAmount;
	private double balance;

	public Payment(int num, double multiplier,double amount,double interest, double prevbalance){
		this.num= num;
		pay=multiplier * amount;
		interestAmount = prevbalance *interest/12/100;
		loanAmount = pay - interestAmount;
		balance = prevbalance - loanAmount;
	}

	public void	show() {
		System.out.printf("%.2f\n", pay);
		System.out.printf("%.2f\n", loanAmount);
		System.out.printf("%.2f\n", interestAmount);
		System.out.printf("%.2f\n", balance);
	}

	public int getNum(){
		return num;
	}

	public double getInterestAmount() {
		return interestAmount;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public double getPay() {
		return pay;
	}

	public double getBalance() {
		return balance;
	}
}
