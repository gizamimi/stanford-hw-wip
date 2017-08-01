/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		drawScaffold();
	}
	private void drawScaffold(){
		/**redraw scaffold*/
		double scaffoldX = getWidth()/2-BEAM_LENGTH;
		double scaffoldUpperY = getHeight()/2-BODY_LENGTH-HEAD_RADIUS*2-ROPE_LENGTH;
		double scaffoldLowerY = scaffoldUpperY + SCAFFOLD_HEIGHT;
		GLine scaffold = new GLine (scaffoldX, scaffoldUpperY,scaffoldX, scaffoldLowerY);
		add(scaffold);
		/**create beam*/
		GLine beam = new GLine (scaffoldX,scaffoldUpperY,getWidth()/2,scaffoldUpperY);
		add(beam);
		/**create rope*/
		double ropeLowerY = scaffoldUpperY + ROPE_LENGTH;
		GLine rope = new GLine (getWidth()/2,scaffoldUpperY, getWidth()/2, ropeLowerY);
		add(rope);	
	}
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) { //string word对应hangman中的hidden：当前已被猜出的字符和隐藏的hyphens
		double x = getWidth()/2 - BEAM_LENGTH;
		double y = getHeight()*7/8;
		GLabel hidden = new GLabel (word,x,y);
		hidden.setFont("Halvetica-24");
		
		/**remove过去的hidden label因为每次在hangman中displayWord(hidden)一次，
		 * 这个hidden label都会覆盖一次在原来的上面
		 * hangman除了setup时使用该method初始了所有hyphens，
		 * 还在while loop的每次正确回答时候使用了。所以必须删掉原来的，画一个新label防止覆盖。
		 * 同理下面的incorrect guess
		 */
		GObject previousHidden = getElementAt (x,y);
		if (previousHidden != null) { 
			remove(previousHidden);
		}
		add(hidden);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {//对应每次猜错的ch
		incorrectGuess += letter; 
		double x = getWidth()/2 - BEAM_LENGTH;
		double y = getHeight()*15/16;
		GLabel IncorrectGuess = new GLabel(incorrectGuess, x,y);
		GObject previousIncorrect = getElementAt (x,y);
		if (previousIncorrect != null) { 
			remove(previousIncorrect);
		}
		add(IncorrectGuess);
		
		/**incorrectGuess.length() is the # of incorrect guess user has made
		 * use this method when the length >= 1 so starts from case 1.
		 */
		switch(incorrectGuess.length()){
			case 1: drawHead(); break;
			case 2: drawBody(); break;
			case 3: drawLeftArm(); break;
			case 4: drawRightArm(); break;
			case 5: drawLeftLeg(); break;
			case 6: drawRightLeg(); break;
			case 7: drawLeftFoot(); break;
			case 8: drawRightFoot(); break;
		}
	}
	private void drawHead(){
		double headX = getWidth()/2 - HEAD_RADIUS;
		double headY = getHeight()/2 - BODY_LENGTH - HEAD_RADIUS*2;
		GOval head = new GOval(headX,headY, HEAD_RADIUS*2, HEAD_RADIUS*2);
		add(head);
	}
	private void drawBody(){
		double bodyUpperY = getHeight()/2 - BODY_LENGTH;
		GLine body = new GLine (getWidth()/2, bodyUpperY, getWidth()/2, getHeight()/2);
		add(body);
	}
	private void drawLeftArm(){
		lowerArmUpperY = getHeight()/2-BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
		lowerArmLowerY = lowerArmUpperY + LOWER_ARM_LENGTH;
		double leftLowerArmX = getWidth()/2 - UPPER_ARM_LENGTH;
		GLine leftLowerArm = new GLine (leftLowerArmX,lowerArmUpperY,leftLowerArmX,lowerArmLowerY);
		GLine leftUpperArm = new GLine (leftLowerArmX,lowerArmUpperY,getWidth()/2,lowerArmUpperY);
		add(leftLowerArm);
		add(leftUpperArm);
	}
	private void drawRightArm(){
		double rightLowerArmX= getWidth()/2 + UPPER_ARM_LENGTH;
		GLine rightLowerArm = new GLine (rightLowerArmX,lowerArmUpperY,rightLowerArmX,lowerArmLowerY);
		GLine rightUpperArm = new GLine (rightLowerArmX,lowerArmUpperY,getWidth()/2,lowerArmUpperY);
		add(rightLowerArm);
		add(rightUpperArm);
	}
	private void drawLeftLeg(){
		leftHipX= getWidth()/2 - HIP_WIDTH/2;
		GLine leftHip = new GLine(leftHipX,getHeight()/2,getWidth()/2,getHeight()/2);
		legLowerY = getHeight()/2 + LEG_LENGTH;
		GLine leftLeg = new GLine(leftHipX,getHeight()/2,leftHipX,legLowerY);
		add(leftHip);
		add(leftLeg);
	}
	private void drawRightLeg(){
		rightHipX= getWidth()/2 + HIP_WIDTH/2;
		GLine rightHip = new GLine (rightHipX,getHeight()/2,getWidth()/2,getHeight()/2);
		GLine rightLeg = new GLine (rightHipX,getHeight()/2, rightHipX,legLowerY);
		add(rightHip);
		add(rightLeg);
	}
	private void drawLeftFoot(){
		double leftFootLeftX = leftHipX - FOOT_LENGTH;
		GLine leftFoot = new GLine (leftFootLeftX,legLowerY,leftHipX,legLowerY);
		add(leftFoot);
	}
	private void drawRightFoot(){
		double rightFootLeftX = rightHipX + FOOT_LENGTH;
		GLine rightFoot = new GLine (rightFootLeftX,legLowerY,rightHipX,legLowerY);
		add(rightFoot);
	}

	
/* instance variables */
	private double lowerArmUpperY;
	private double lowerArmLowerY;
	private double legLowerY;
	private double leftHipX;
	private double rightHipX;
	/**must put outside of method noteIncorrectGuess to avoid restart from empty string*/
	private String incorrectGuess = ""; 
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
}
