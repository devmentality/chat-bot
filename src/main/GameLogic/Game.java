package main.GameLogic;

import java.util.ArrayList;

public class Game {
    private static final int amountOfDigits = 4;
    private int[] digitsToGuess;
    public ArrayList<int []> attempts;
    public ArrayList<GuessResult> results;
    
    public Game() {
        digitsToGuess = new int[amountOfDigits];
        digitsToGuess = SampleGenerator.setDigitsToGuess(amountOfDigits);
        attempts = new ArrayList<int[]>();
        results = new ArrayList<GuessResult>();
    }
    
    public Game(int[] guess)
    {
    	digitsToGuess = guess;
        attempts = new ArrayList<int[]>();
        results = new ArrayList<GuessResult>();
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
        attempts.add(guess);
        results.add(new GuessResult(bulls, cows));
        return new GuessResult(bulls, cows);
    }

    private boolean isCow(int guessedDigit)
    {
        for(int digit: digitsToGuess)
        	if (guessedDigit == digit)
                return true;
        return false;
    }
}
