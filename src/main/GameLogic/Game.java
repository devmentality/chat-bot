package main.GameLogic;

import java.util.ArrayList;

public class Game
{
    /*
        Класс, реализующий основную логику игры быки-коровы
     */
    public static final int attemptsToLose = 100;
    public int[] digitsToGuess;
    public ArrayList<Attempt> attempts;
    
    public Game(int amountOfDigits) {
        digitsToGuess = new int[amountOfDigits];
        digitsToGuess = SampleGenerator.setDigitsToGuess(amountOfDigits);
        attempts = new ArrayList<>();
    }
    
    public Game(int[] guess)
    {
    	digitsToGuess = guess;
        attempts = new ArrayList<>();
    }

    public GuessResult respondOnGuess(int[] guess, int amountOfDigits) {
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
