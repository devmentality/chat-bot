package main;

import java.util.Random;

public class Game {
    private static final int amountOfDigits = 4;
    private int[] digitsToGuess;

    public Game() {
        digitsToGuess = new int[amountOfDigits];
        digitsToGuess = SampleGenerator.setDigitsToGuess(amountOfDigits);
    }
    
    public Game(int[] guess)
    {
    	digitsToGuess = guess;
    }

    public GuessResult respondOnGuess(int[] guess) {
        if (guess.length != amountOfDigits)
            throw new IllegalArgumentException();
        return countBullsAndCows(guess);
    }

    private GuessResult countBullsAndCows(int[] guess) {
        int bulls = 0;
        int cows = 0;
        for (int index = 0; index < guess.length; index++)
            if (guess[index] == digitsToGuess[index])
                bulls++;
            else if (isCow(guess[index]))
                cows++;
        return new GuessResult(bulls, cows);
    }

    private boolean isCow(int guessedDigit)
    {
        for(int digit: digitsToGuess)
        	if (guessedDigit == digit)
                return true;
        return false;
    }

    public static int[] parseGuess(String guess)
    {
        int[] digits = new int[guess.length()];
        for(int index = 0; index < guess.length(); index++)
            digits[index] = Integer.parseInt(String.valueOf(guess.charAt(index)));
        return digits;
    }
}
