/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		println("This program finds the largest and smallest numbers.");
		int firstInput = readInt("? ");
		
		if (firstInput == sentinel){
			println("Not valid first number!");
		}
		int smallest = firstInput;
		int largest = firstInput;
		
		while (firstInput != sentinel){
			int nextInput = readInt("? ");
			if (nextInput == sentinel){ //inside while loop, must end in this condition (when press 0) //
				println ("smallest: " + smallest);
				println ("largest: " + largest);
				firstInput = sentinel; //break while loop, println//
			} else if (nextInput < smallest){ 
				smallest = nextInput; //return to while loop//
			} else if (nextInput>largest){
				largest = nextInput; //return to while loop//
			}
		}
		
	}
	private static final int sentinel = 0;
}
