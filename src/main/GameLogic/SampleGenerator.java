package main.GameLogic;

import java.util.HashSet;
import java.util.Random;

public class SampleGenerator {
	
	public static int [] setDigitsToGuess(int amountOfDigits)
    {
		int maxValue = 10;
		HashSet<Integer> digitsToGuess = new HashSet<>();
		Random rnd = new Random();
		while(digitsToGuess.size() != amountOfDigits)
        {
        	digitsToGuess.add(rnd.nextInt(maxValue));
        }
		return digitsToGuess.stream().mapToInt(Integer::intValue).toArray();		
    }
}
