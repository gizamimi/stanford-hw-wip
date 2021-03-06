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
		putBeeper();
		while (frontIsClear()){
			moveOn();
			oddToEven();
			moveOn();
			evenToOdd();
		}
	}
	private void moveOn(){
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
			if (beepersPresent()){
				move();
				turnLeft();
				move();
				putBeeper();
			} else{
				move();
				turnLeft();
				putBeeper();
			}
		}
	}
	private void evenToOdd(){
		turnRight();
		if (frontIsClear()){
			if (beepersPresent()){
				move();
				turnRight();
				move();
				putBeeper();
			} else{
				move();	
				turnRight();
				putBeeper();
			}
		}
	}
}
