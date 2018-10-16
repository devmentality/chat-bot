package main.GameLogic;

import java.util.ArrayList;

public class Game {
    public static final int amountOfDigits = 4;
    public static final int attemptsToLose = 100;
    private int[] digitsToGuess;
    public ArrayList<Attempt> attempts;
    
    public Game() {
        digitsToGuess = new int[amountOfDigits];
        digitsToGuess = SampleGenerator.setDigitsToGuess(amountOfDigits);
        attempts = new ArrayList<>();
    }
    
    public Game(int[] guess)
    {
    	digitsToGuess = guess;
        attempts = new ArrayList<>();
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
        attempts.add(new Attempt(guess, new GuessResult(bulls, cows)));
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
