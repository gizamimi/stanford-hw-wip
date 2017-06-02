/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		//define vertically: number of rows
		for (int row = 0; row < BRICKS_IN_BASE; row++) { 
			/* row 0(base row) has 14 bricksInRow. 
			 * from bottom to top: row 1 has 13 bricks, row 2 has 12 bricks...top row 13 has 1 brick
			 * thus bricksInRow is Bricks in row 0(BRICKS_IN_BASE = 14) - row number.
			 */
			int bricksInRow = BRICKS_IN_BASE - row; 
			//define horizontally: number of bricks per row
			for (int brickNum = 0; brickNum < bricksInRow; brickNum++){
				int x = getWidth()/2 - bricksInRow * BRICK_WIDTH / 2 + brickNum * BRICK_WIDTH;
				//brickNum starts from 0
				int y = getHeight() - (row + 1) * BRICK_HEIGHT;
				/* x,y are coordinates of the upper left corner
				 * row ＋1 because row starts from 0 and ends 13
				 * we need total of 14 rows so add the 0 row
				 * base row 0 upper left height: total height - 1 brick height
				 */
				GRect brick = new GRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
				add(brick);
			}
		}
	}
}
