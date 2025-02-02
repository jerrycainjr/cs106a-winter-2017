/*
 * File: CarbonDating.java
 * --------------------
 * This program calculates the age of a sample based on how much
 * Carbon-14 is remaining. See:
 * http://hyperphysics.phy-astr.gsu.edu/hbase/Nuclear/cardat.html
 */

import acm.program.*;

public class CarbonDating extends ConsoleProgram {

	public void run() {
		// print some introduction information
		println("Radioactive molecule = C14");
		println("Halflife = " + 5730 + " years");
		println("C14 in living organisms = " + 13.6 + " dpm");
		println("-----");
		println(""); // adds an extra new-line
		
		// get c14 from the user
		double amount = readDouble("How much c14 in your sample? ");
		
		// calculate the age
		double age = Math.log(amount / 13.6) / Math.log(0.5) * 5730;
		println("Your sample is " + age + " years old");
		

	}













	/**
	 * Method: Init
	 * ------------
	 * This is some special code I added so that the font in
	 * the programs is larger. You can ignore it, and you don't
	 * need an init in your programs!
	 */
	public void init() {
		// used to make the font visible in lecture...
		setFont("Courier-24");
	}
}

