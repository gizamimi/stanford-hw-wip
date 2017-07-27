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
			userTypeIn();  //user type in ch
			checkLetter(); //check if the 
			checkEndStatus(); //
			println ("The word now looks like this: " + hidden);
			if (lives > 1) {
				println("You have "+ lives +" guesses left.");
			} else if (lives ==1){
				println("You have only 1 guess left.");
			}
		}
	}
	private void userTypeIn(){
		String Line = readLine("Your guess: ");
		while (true){
			if (Line.length()!= 1){
				Line = readLine("Invalid! Please type in one character: ");
			}
			if (!Character.isLetter(ch)) {
				Line = readLine("Invalid! Please type in only letter format.");
			}else if(Line.length() = 1){
				
			}
		}
		ch = Line.charAt(0);
		if(ch >= 'a' && ch <= 'z') ch = Character.toUpperCase(ch);
		//also can written as if(Character.isLowerCase(ch))
	}
	private void checkLetter(){
		if(word.indexOf(ch) == -1) {
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
		/**RESULT 1: YOU LOSE*/
		if (lives == 0){
			gameEnd = true;
			println("You're completely hung.");
			println("The word was: " + word);
			println("You lose.");
		} 
		/**RESULT 2:YOU WIN*/
		if (hidden.equals(word)){
			gameEnd = true;
			println("You guessed the word " + word);
			println("You win.");
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
