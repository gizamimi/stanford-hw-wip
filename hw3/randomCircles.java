/*
* File: randomCircles.java
* -------------------
* This program is for section hw 3-3. */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class randomCircles extends GraphicsProgram {
	private static final int MIN_RADIUS = 5;
	private static final int MAX_RADIUS = 50;
	private static final int NCIRCLE = 10;
	
	public void run (){
		for (int i = 0; i < NCIRCLE; i++){
			double radius = rgen.nextDouble(MIN_RADIUS, MAX_RADIUS);
			double x = rgen.nextDouble(0, getWidth() - 2 * radius);
			double y = rgen.nextDouble(0, getHeight() - 2 * radius);
			GOval circle = new GOval(x, y, 2 * radius, 2 * radius);
			add(circle);
			circle.setFilled(true);
			circle.setColor(rgen.nextColor());
		}
	}
	
/* Private instance variables */
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
