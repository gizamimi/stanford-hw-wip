/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 2;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 3;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setUp();
		play();
	}
	
	private void setUp(){
		createBricks();
		createPaddle();		
	}
	
	private void createBricks(){
		//starting from the top left first brick (bx, by), needs to leave white space for the leftmost sides of first column
		double bx = (APPLICATION_WIDTH - (NBRICKS_PER_ROW *BRICK_WIDTH) - (NBRICKS_PER_ROW-1) *BRICK_SEP)/2;
		double by = BRICK_Y_OFFSET;
		for (int column = 0; column < NBRICKS_PER_ROW; column++) { //brickNum increasing in column
			for (int row = 0; row < NBRICK_ROWS; row++) { //brickNum increasing in row
				double x = bx + column* (BRICK_WIDTH + BRICK_SEP);
				double y = by + row * (BRICK_HEIGHT + BRICK_SEP);
				GRect brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				add(brick);
				switch (row/2){  //every two lines have the same color
					case 0: brick.setColor(Color.RED); break; //line 0-1
					case 1: brick.setColor(Color.ORANGE); break; //line 2-3,etc.
					case 2: brick.setColor(Color.YELLOW); break;
					case 3: brick.setColor(Color.GREEN); break;
					case 4: brick.setColor(Color.CYAN); break;
				}
			}
		}
	}
	
	private void createPaddle(){
		paddle = new GRect (WIDTH/2 - PADDLE_WIDTH/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT); 
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
	}
	public void mouseMoved(MouseEvent e){ //mouse event can't be private!!!!!
		//mouse is in the middle of paddle
		if ((e.getX() <= WIDTH - PADDLE_WIDTH/2) && (e.getX() >= PADDLE_WIDTH/2)){
			paddle.setLocation (e.getX() - PADDLE_WIDTH/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);   //acm.graphics method page 45
		}
	}
	
	private void play(){
		createBall();
		getVelocity(); //don't set it up inside while! or vy vx will always return to the initial
		while (!gameEnd){
			moveBall();
		}
	}
	
	private void createBall(){
		ball = new GOval(WIDTH/2 - BALL_RADIUS, HEIGHT/2 - BALL_RADIUS, BALL_RADIUS * 2,BALL_RADIUS * 2);
		ball.setFilled(true);
		add(ball);
	}
		
	private void getVelocity(){	
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5))vx = -vx;
		vy += 3.0;
	}
	
	private void moveBall(){
		ball.move(vx, vy);
		bounceOffWall();
		checkForCollisions();
		checkEndStatus();
		pause(15);
	}
	
	private void bounceOffWall(){
		if (ball.getX() <= 0 || ball.getX() >= WIDTH - BALL_RADIUS * 2){ //ball touch left or right wall
			vx = -vx;
		} else if (ball.getY() <= 0){ //ball touch wall top
			vy = -vy;
		}
	}
	
	private void checkForCollisions(){
		GObject collider = getCollidingObject(ball.getX(), ball.getY());
		if (collider == paddle){
			vy = -vy;
		}else if (collider !=null){ //collider == brick
			remove (collider);
			vy = -vy;
			brickNum --;
		}
	}
	
	private GObject getCollidingObject(double x, double y){ 
		if (getElementAt(x, y) != null) { //Is top left of the ball touching GOject(paddle,brick)?
			return getElementAt(x, y);
		} else if (getElementAt(x + 2 * BALL_RADIUS, y) != null) { //Is top right of the ball touching GOject?
			return getElementAt(x + 2 * BALL_RADIUS, y);
		} else if (getElementAt(x, y + 2 * BALL_RADIUS) != null) { //Is bottom left of the ball touching GOject?
			return getElementAt(x, y + 2 * BALL_RADIUS);
		} else if (getElementAt(x + 2 * BALL_RADIUS, y + 2 * BALL_RADIUS) != null) { //Is bottom right of the ball touching GOject?
			return getElementAt(x + 2 * BALL_RADIUS, y + 2 * BALL_RADIUS);
		} else {
			return null; //no collision
		}	
	}
	
	private void checkEndStatus(){
		if (ball.getY() + vy> paddle.getY()){ //lose: when the ball fall below the paddle
			count--;
			remove(ball);
			GLabel lose = new GLabel ("YOU LOSE... " + count + " LEFT");
			lose.setLocation((WIDTH - lose.getWidth()) / 2, HEIGHT / 2);
			lose.setFont("Helvetica-25");
			add(lose);
			if (count == 0) {
				gameEnd = true;
				removeAll();
				GLabel gameOver = new GLabel ("NO COUNT LEFT, GAMEOVER");
				gameOver.setLocation((WIDTH - gameOver.getWidth()) / 2, HEIGHT / 2);
				gameOver.setFont("Helvetica-25");
				add(gameOver);
			} else {
			restart(); 
			}
		}
		if (brickNum == 0){
			gameEnd = true;
			remove(ball);
			GLabel win = new GLabel ("YOU WIN!");
			win.setLocation((WIDTH - win.getWidth()) / 2, HEIGHT / 2);
			win.setFont("Helvetica-25");
			add(win);
		}
	}
	
	private void restart(){
		waitForClick();
		createBall();
		vy = -vy; //makes the new ball drop downwards instead of upwards
		moveBall();	
	}
	
	private int brickNum = NBRICKS_PER_ROW * NBRICK_ROWS;
	private int count = NTURNS;
	private GRect paddle;
	private GOval ball;
	/** Starting X and Y Velocties */
	private double vx, vy;
	private boolean gameEnd = false;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
