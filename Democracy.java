/*
 * File: Democracy.java
 * ----------------------------
 * problem set 1
 */

import stanford.karel.*;

public class Democracy extends SuperKarel {
	public void run(){
		while (frontIsClear()){
			move();
			checkBeeper();
			move();
		}
	}
	private void checkBeeper() {
		if (noBeepersPresent()){
			removeUpBeeper();
			removeDownBeeper();
		}
	}
	private void removeUpBeeper() {
		turnLeft();
		move();
		while (beepersPresent()){
			pickBeeper();
		}
	}
	private void removeDownBeeper() {
		turnAround();
		move();
		move();
		while (beepersPresent()){
			pickBeeper();
		}
		turnAround();
		move();
		turnRight();
	}
}
