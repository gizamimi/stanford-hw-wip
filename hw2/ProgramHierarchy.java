/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final int width = 120;
	private static final int height = 50;
			
	public void run() {
		drawProgram();
		drawGraphicsLine();
		drawGraphics();
		drawConsoleLine();
		drawConsole();
		drawDialogLine();
		drawDialog();
	}
	private void drawProgram(){
		int x = getWidth()/2 - width/2;
		int y = getHeight()/2- height;
		GRect rectProgram = new GRect (x,y,width,height);
		add(rectProgram);
		GLabel program = new GLabel ("program", x,y);
		add(program);
		double dx = width/2 - program.getWidth()/2; //see pic 2-3//
		double dy = height/2 + program.getAscent()/2;
		program.move(dx, dy);
	}
	private void drawGraphicsLine(){
		int x1 = getWidth()/2; //same for the rest lines//
		int y1 = getHeight()/2; //same for the rest lines//
		int x2 = x1 - 3*(width/2); //assume the width distance between boxes is 1 width//
		int y2 = y1 + height; //assume the height distance between boxes is 1 height//
		GLine graphicsLine = new GLine(x1,y1,x2,y2);
		add(graphicsLine);
	}
	private void drawGraphics(){
		int x = getWidth()/2- 2*width;
		int y = getHeight()/2 + height; //y = y1//
		GRect rectGraphics = new GRect(x,y,width,height);
		add(rectGraphics);
		GLabel graphics = new GLabel("GraphicsProgram",x,y);
		add(graphics);
		double dx = width/2 - graphics.getWidth()/2; //see pic 2-3//
		double dy = height/2 + graphics.getAscent()/2;
		graphics.move(dx, dy);
	}
	private void drawConsoleLine(){
		int x1 = getWidth()/2; //same as above//
		int y1 = getHeight()/2; //same as above//
		int x2 = getWidth()/2;
		int y2 = getHeight()/2 + height;
		GLine consoleLine = new GLine (x1,y1,x2,y2);
		add(consoleLine);
	}
	private void drawConsole(){
		int x = getWidth()/2-width/2;
		int y = getHeight()/2 + height;
		GRect rectConsole = new GRect(x,y,width,height);
		add(rectConsole);
		GLabel console = new GLabel("ConsoleProgram",x,y);
		add(console);
		double dx = width/2 - console.getWidth()/2;
		double dy = height/2 + console.getHeight()/2;
		console.move(dx, dy);
	}
	private void drawDialogLine(){
		int x1 = getWidth()/2;
		int y1 = getHeight()/2;
		int x2 = getWidth()/2 + 3 * (width/2);
		int y2 = getHeight()/2 + height;
		GLine dialogLine = new GLine (x1,y1,x2,y2);
		add(dialogLine);
	}
	private void drawDialog(){
		int x = getWidth()/2 + width;
		int y = getHeight()/2 +height;
		GRect rectDialog = new GRect(x,y,width,height);
		add(rectDialog);
		GLabel dialog = new GLabel("DialogProgram", x, y);
		add(dialog);
		double dx = width/2 - dialog.getWidth()/2;
		double dy = height/2 + dialog.getHeight()/2;
		dialog.move(dx, dy);
	}
}

