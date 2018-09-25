package main;

import java.util.Random;

public class Game {
    private static final int amountOfDigits = 4;
    private int[] digitsToGuess;

    public Game() {
        digitsToGuess = new int[amountOfDigits];
        setDigitsToGuess();
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

    private void setDigitsToGuess()
    {
        int[] digits = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffle(digits);
        System.arraycopy(digits, 0, digitsToGuess, 0, amountOfDigits);
    }

    private void shuffle(int [] array)
    {
        Random rnd = new Random();
        for(int index = 0; index < array.length; index++)
        {
            int pairIndex = rnd.nextInt(array.length);

            int temporal = array[index];
            array[index] = array[pairIndex];
            array[pairIndex] = temporal;
        }
    }
}
