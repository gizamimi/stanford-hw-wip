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
	public void init() {
		/**init method initializes the canvas 
		  * and adds it to the window 
		  * prior to the run method being executed;*/
        canvas = new HangmanCanvas();
        add(canvas);
	 }
	 
	public void run(){
		setUp();
		play();
	}
	
	private void setUp(){
		canvas.reset();
		println("Welcome to Hangman!");
		
		/**step1: randomly choose a word from lexicon
		 * lexicon.getWord(i) 是class HangmanLexicon里自己设置的一个method，可以调用
		 * lexicon.getWordCount()是另一个method，共10个。index从0开始计数所以max要－1
		 * 从这10个数里随机抽选一个（index 0-9)，用到rgen.nextInt
		 */
		word = lexicon.getWord(rgen.nextInt(0,(lexicon.getWordCount() - 1)));
		
		/**step 2: setting hidden hyphens
		* the game starts with # of hyphens equals word.length()
		*/
		hidden = "";
		for (int i = 0; i < word.length(); i++){
			hidden =  hidden + "-";
		}
		canvas.displayWord(hidden); //初始setup时候就应当在canvas画上所有hyphens
	}
	
	private void play(){
		while (!gameEnd){
			println ("The word now looks like this: " + hidden);
			checkLife(); 
			userTypeIn();//检查用户输入的格式是否正确
			checkLetter(); //检查格式正确的ch是否存在于答案word里
			checkEndStatus(); //win or lose
		}
	}
	
	private void checkLife(){
		/**Beginning life = 8 
		 * + can converts any operands that are not strings to their string representation
		 * so can use println to add integer
		 */
		if (lives > 1) {
			println("You have "+ lives +" guesses left.");
		} else if (lives ==1){
			println("You have only 1 guess left.");
		}
	}
	
	private void userTypeIn(){
		while(true){
			/**目标是一个单字符的A-Z字母*/
			guess = readLine("Your guess: ");
			
			/**add guess==null and guess.equals("") to avoid player press enter instantly
			 * 如果不加这个if, guess.charAt(0)会报错因为guess是个空集，什么都没有
			 * ch在0位置，默认guess至少有1个以上字符
			 */
			if(guess==null||guess.equals("")){
				println("Invalid! You must type something anyway.");
			} else {
				ch = guess.charAt(0);
				if(ch >= 'a' && ch <= 'z') ch = Character.toUpperCase(ch);
				//also can written as if(Character.isLowerCase(ch))
				
				if (guess.length() == 1 && Character.isLetter(ch)){ 
					/**hidden.indexOf(ch) != -1 means user has already guessed this correct character
					 * however, guessing the same incorrect character twice will count as another wrong guess 
					 */
					if (hidden.indexOf(ch) != -1) { 
						println("You already guessed that letter.");
			        	} else {
						break; //user made a valid guess and jump out of the loop
					}
				}else{//incorrect format: 空格。数字。（bug：回车报错！！！）
					println("Invalid! Type a single letter from A-Z. ");
				}
			}
		}
	}
	
	private void checkLetter(){
		if(word.indexOf(ch) == -1) { //ch不存在于答案word中
			canvas.noteIncorrectGuess(ch); //每次错误，在canvas最下方更新输入的错误字母
			println("There are no "+ ch +"'s in the word");
			lives --;
		} else { //ch存在于word里
			println("That guess is correct.");
		}
		/** display the correct guessed character instead of hyphens */
		for (int i = 0; i < word.length(); i++){ 
			if (ch==word.charAt(i)){
				hidden = hidden.substring(0, i) + ch + hidden.substring(i+1);
			}
			canvas.displayWord(hidden); //每次正确，把canvas上的hyphens替换掉
		}
	}
	
	private void checkEndStatus(){
		/**RESULT 1: YOU LOSE*/
		if (lives == 0){
			gameEnd = true; //boolean的脱出必须要进行完当次while循环。break可以直接停止
			println("You're completely hung.");
			println("The word was: " + word);
			println("You lose.");
		} 
		/**RESULT 2:YOU WIN*/
		if (hidden.equals(word)){
			gameEnd = true;
			println("You guessed the word: " + word);
			println("You win.");
		} 
	}
	
	/**create a new HangmanLexicon and store it in an instance variable*/
	private HangmanLexicon lexicon = new HangmanLexicon();
	private HangmanCanvas canvas;
	private int lives = 8; //Beginning life = 8 
	private char ch; //character user typed in
	private String guess;
	private String word;
	private String hidden;
	private boolean gameEnd = false;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
