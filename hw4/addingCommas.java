/*
 * File: addingCommas.java
 * ------------------
 * This program will eventually add commas to numeric strings from
 * Section Handout #4: String Processing.
 */

import acm.program.*;

public class addingCommas extends ConsoleProgram {

	public void run() {
        while (true) {
        	String digits = readLine("Enter a numeric string: "); 
        	if (digits.length() == 0) break; //直接按了enter，啥都没有反应。
        	println(addCommasToNumericString(digits));
		}
	}
	
	private String addCommasToNumericString(String digits){
		String result = ""; //starts from empty
		int len = digits.length();
		int ndigits = 0;
		/**starts from left side to the right side of the string. 
		 * character index(charAt) starts from 0: i=len-1就是各位数的char index
		 */
		for (int i = len-1; i >=0;i--){ //for example 17 has 0 and 1.
			result = digits.charAt(i) + result; //17: result= 7 when i=1. then get 1 when i = 0 and plus result 7 to get 17.
			ndigits++;
			if (((ndigits % 3)==0) && (i>0)){ //100,digit 3, 到百位数1时％3=0但i=0, so no ",". 1001, digit 4,到百位数1时％3=0且i=1。result前可＋“，”
				result = "," + result;
			}
		}
		return result; //println (result)
	}
}
