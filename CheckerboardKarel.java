/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run(){		
		while (frontIsClear()){
			moveOn();
			oddToEven();
			moveOn();
			evenToOdd();
		}
	}
	private void moveOn(){
		putBeeper();
		while (frontIsClear()){
			if (frontIsClear()){
				move();
			}
			if (frontIsClear()){
				move();
				putBeeper();
			}
		}
	}
	private void oddToEven(){
		turnLeft();
		if (frontIsClear()){
			move();
			turnLeft();
		}
	}
	private void evenToOdd(){
		turnRight();
		if (frontIsClear()){
			move();	
			turnRight();
		}
	}
}
