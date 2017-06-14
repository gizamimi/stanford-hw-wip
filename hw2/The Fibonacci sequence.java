/*
 * File: The Fibonacci sequence.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Section Handout #2 - 1 problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class FibonacciSequence extends ConsoleProgram {
	private static final int MAX_TERM_VALUE = 10000;
	public void run() {
		println("This program lists the Fibonacci sequence.");
		/* 
		 * Fib(0) = 0
		 * Fib(1) = 1
		 * Fib(2) = 1 (0 + 1) = fib(0) + fib(1)
		 * Fib(3) = 2 (1 + 1) = fib(1) + fib(2)
		 * Fib(4) = 3 (1 + 2) = fib(2) + fib(3) 
		 * Fib(5) = 5 (2 + 3) = fib(3) + fib(4) 
		 */
		int fib0 = 0;
		int fib1 = 1;

		while (fib0 < MAX_TERM_VALUE) {
			println(fib0); //print 0//
			int sum = fib0 + fib1; //get fib2 = (0 + 1)//
			fib0 = fib1; //sum = (fib0[change to 1 and now can print as 1] + fib1)
			fib1 = sum; //sum = (fib0[change to 1] + fib1[change to 1])
		}
		
	}
}
