/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to computer Pythagorean theorem.");
		int a = readInt("a: ");
		int b = readInt("b: ");
		double c = readDouble("c = " + Math.sqrt(a*a + b*b));
	}
}
