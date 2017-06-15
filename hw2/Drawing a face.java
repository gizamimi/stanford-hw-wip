/*
 * File: Drawing a face.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Section Handout #2—Simple Java -2.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class DrawFace extends GraphicsProgram {

	private static final int HEAD_WIDTH = 80;
	private static final int HEAD_HEIGHT = 130;
	private static final int EYE_RADIUS = 10;
	private static final int MOUTH_WIDTH = 50;
	private static final int MOUTH_HEIGHT = 20;
	
	public void run() {
		drawHead();
		drawEyes();
		drawMouth();
	}
	private void drawHead(){
		int x = getWidth()/2 - HEAD_WIDTH/2;
		int y = getHeight()/2 - HEAD_HEIGHT/2;
		GRect head = new GRect(x,y, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.gray);
		add(head);
	}
	
	private void drawEyes(){
		drawLeftEye();
		drawRightEye();
	}
	
	private void drawLeftEye(){
		int d = 2 * EYE_RADIUS;
		int x = (getWidth()/2 - HEAD_WIDTH/2) + (HEAD_WIDTH/4 - EYE_RADIUS);
		int y = (getHeight()/2 - HEAD_HEIGHT/2) + (HEAD_HEIGHT/4 - EYE_RADIUS);
		GOval leftEye = new GOval (x,y,d,d);
		leftEye.setFilled(true);
		leftEye.setColor(Color.yellow);
		add(leftEye);
	}
	private void drawRightEye(){
		int d = 2 * EYE_RADIUS;
		int x = (getWidth()/2 + HEAD_WIDTH/2) - (HEAD_WIDTH/4 + EYE_RADIUS);
		int y = (getHeight()/2 - HEAD_HEIGHT/2) + (HEAD_HEIGHT/4 - EYE_RADIUS);
		GOval rightEye = new GOval (x,y,d,d);
		rightEye.setFilled(true);
		rightEye.setColor(Color.yellow);
		add(rightEye);
	}
	private void drawMouth(){
		int x = getWidth()/2 - MOUTH_WIDTH/2;
		int y = getHeight()/2 + HEAD_HEIGHT/4 - MOUTH_HEIGHT/2;
		GRect mouth = new GRect(x,y,MOUTH_WIDTH,MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setColor(Color.white);
		add(mouth);
	}
}
