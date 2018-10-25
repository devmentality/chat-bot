package main.GameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SampleGenerator
{
	/*
		Генератор примеров для игры быки-коровы
	*/
	public static int [] setDigitsToGuess(int amountOfDigits)
    {
		int maxValue = 10;
		HashSet<Integer> digitsToGuess = new HashSet<>();
		Random rnd = new Random();
		while(digitsToGuess.size() != amountOfDigits)
        {
        	digitsToGuess.add(rnd.nextInt(maxValue));
        }
		ArrayList<Integer> arrayOfDigits = new ArrayList<Integer>();
		for(Integer digit : digitsToGuess)
		{
			arrayOfDigits.add(digit);
		}
		Collections.shuffle(arrayOfDigits);
		return arrayOfDigits.stream().mapToInt(Integer::intValue).toArray();		
    }
}
