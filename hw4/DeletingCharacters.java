/*
 * File: DeletingCharacters.java
 * ------------------
 * This program will eventually deleting characters from a string from
 * Section Handout #4: String Processing.
 */

import acm.program.*;

public class DeletingCharacters extends ConsoleProgram {

	public String removeAllOccurrences(String str, char ch){
		String result = "";
		for (int i = 0; i<str.length();i++){
			if (str.charAt(i) != ch){ //i=index number.if at this number char != ch given.
				result += str.charAt(i); //add this character to result.
			}
		}
		return result;
	}
}
