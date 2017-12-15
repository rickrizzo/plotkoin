package edu.rpi.itws.webscience.utilities;

import java.util.ArrayList;
import java.util.List;

public class StringShuffle {
	
	/*
	 * FOUND ON STACK OVERFLOW
	 * https://stackoverflow.com/a/3316696/4062901
	 */
	
	public static String shuffle(String input) {
		List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
	}
}
