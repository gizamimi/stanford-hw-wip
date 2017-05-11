/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	public void run() {
		turnRight();
		move();
		turnLeft();
		fast();
		pickBeeper();
		turnAround();
		fast();
		turnRight();
		move();
		turnRight();
	}
	private void fast() {
		for(int i=0;i<3;i++){
			move();
		}
	}
}
