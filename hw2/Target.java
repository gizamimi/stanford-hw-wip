/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	/** Radius of outer red circle in pixels */
	private static final int OUTER_RADIUS = 1 * 72; //1 inch = 72 pixels//
			
	/** Radius of middle white circle in pixels */
	private static final double WHITE_RADIUS = 0.65 *72;
	
	/** Radius of inner red circle in pixels */
	private static final double INNER_RADIUS = 0.3 * 72;

	
	public void run() {
		drawOuterOval();
		drawWhiteOval();
		drawInnerOval();
	}
	
	private void drawOuterOval(){
		int d1 = 2 * OUTER_RADIUS;
		int x1 = getWidth() / 2 - OUTER_RADIUS;
		int y1 = getHeight() / 2 - OUTER_RADIUS;
		GOval outer = new GOval(x1,y1,d1,d1);
		outer.setFilled(true);
		outer.setColor(Color.RED);
		add(outer);
	}
	
	private void drawWhiteOval(){
		double d2 = 2 * WHITE_RADIUS;
		double x2 = getWidth() / 2 - WHITE_RADIUS;
		double y2 = getHeight() / 2 - WHITE_RADIUS;
		GOval white = new GOval(x2,y2,d2,d2);
		white.setFilled(true);
		white.setColor(Color.white);
		add(white);
	}	
	
	private void drawInnerOval(){
		double d3 = 2 * INNER_RADIUS;
		double x3 = getWidth() / 2 - INNER_RADIUS;
		double y3 = getHeight() / 2 - INNER_RADIUS;
		GOval inner = new GOval(x3,y3,d3,d3);
		inner.setFilled(true);
		inner.setColor(Color.red);
		add(inner);
	}	
}
