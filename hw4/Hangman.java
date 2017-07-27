/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	public void run(){
		setUp();
		play();
	}
	
	private void setUp(){
		println("Welcome to Hangman!");
		
		/**step1: randomly choose a word from lexicon
		 * lexicon.getWord(i) 是class HangmanLexicon里自己设置的一个method，可以调用
		 * lexicon.getWordCount()是另一个method，共10个。index从0开始计数所以max要－1
		 * 从这10个数里随机抽选一个（index 0-9)，用到rgen.nextInt
		 */
		word = lexicon.getWord(rgen.nextInt(0,(lexicon.getWordCount() - 1)));
		
		/**step 2: setting hidden dashes
		* the game starts with # of dashes equals word.length()
		*/
		hidden = "";
		for (int i = 0; i < word.length(); i++){
			hidden =  hidden + "-";
		}
		println ("The word now looks like this: " + hidden);
		
		/**step 3: setting beginning life = 8 
		 * + can converts any operands that are not strings to their string representation
		 * so can use println to add integer
		 */
		println("You have "+ lives +" guesses left.");
	}
	
	private void play(){
		while (!gameEnd){
			userTypeIn(); //user type in ch
			checkLetter(); //check if user has guessed correctly//
			checkEndStatus(); 
		}
	}
	
	private void userTypeIn(){
		String Line = readLine("Your guess: ");
		ch = Line.charAt(0);
		while(true){ //check if the line user typed is in correct format
			if ((Line.length() != 1) || (!Character.isLetter(ch))){ //incorrect format: 空格。数字。（bug：回车报错！！！）
				Line = readLine("Invalid! Please type in again: ");
				ch = Line.charAt(0);
			} else{
				break;
			}
		}
		if(ch >= 'a' && ch <= 'z') ch = Character.toUpperCase(ch);
		//also can written as if(Character.isLowerCase(ch))
	}
	
	private void checkLetter(){
		if(word.indexOf(ch) == -1) { //this character does not exist in word
			println("There are no "+ ch +"'s in the word");
			lives --;
		} else { //ch exists in word
			println("That guess is correct.");
		}
		for (int i = 0; i < word.length(); i++){ //show the correct character instead of dash in hidden word
			if (ch==word.charAt(i)){
				hidden = hidden.substring(0, i) + ch + hidden.substring(i+1);
			}
		}
	}
	
	private void checkEndStatus(){
		if (lives == 0){
			/**RESULT 1: YOU LOSE*/
			gameEnd = true;
			println("You're completely hung.");
			println("The word was: " + word);
			println("You lose.");
		} else if (hidden.equals(word)){
			/**RESULT 2:YOU WIN*/
			gameEnd = true;
			println("You guessed the word " + word);
			println("You win.");
		} else {
			/**RESULT 3: TBC*/
			println ("The word now looks like this: " + hidden);
			checkLife();
		}
	}
	private void checkLife(){
		if (lives > 1) {
			println("You have "+ lives +" guesses left.");
		} else if (lives ==1){
			println("You have only 1 guess left.");
		}
	}
	
	/**create a new HangmanLexicon and store it in an instance variable*/
	private HangmanLexicon lexicon = new HangmanLexicon();
	private int lives = 8;
	/**character user typed in*/
	private char ch;
	private String word;
	private String hidden;
	private boolean gameEnd = false;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
