package space.jeywhite.morthageapp;

import java.lang.Math;

public class Mortgage {
	private static double amount;
	private static double interest;
	private double periods;
	private double multiplier;

    Mortgage(double amount, double interest, double periods) {
    	this.amount=amount;
    	this.interest=interest;
    	this.periods=periods;
		double pc=(this.interest/100)/12;
    	this.multiplier = (pc * Math.pow( (1+pc), periods))/(Math.pow( (1+pc), periods)-1);
		System.out.println(this.multiplier);
		System.out.println(this.multiplier*this.amount);
    }
    double getAmount(){
    	return amount;
	}

	double getMultiplier(){
    	return multiplier;
    }

	public double getInterest() {
    	return interest;
	}

	public double getPeriods() {
    	return periods;
	}
}

