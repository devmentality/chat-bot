package main.GameLogic;

import java.util.HashSet;

public class GameController
{
    public static int[] parseGuess(String guess)
    {
        int[] digits = new int[guess.length()];
        for(int index = 0; index < guess.length(); index++)
            digits[index] = Integer.parseInt(String.valueOf(guess.charAt(index)));
        return digits;
    }

    public static boolean isVictoriousGuess(GuessResult guessResult, int amountOfDigits)
    {
        return guessResult.amountOfBulls == amountOfDigits;
    }

    public static boolean isInLosingState(Game game)
    {
        return game.attempts.size() >= Game.attemptsToLose;
    }

    public static boolean checkUniqueDigits(int [] sampleDigits)
    {
        int digits = 10;
        int[] count = new int[digits];
        for(int i = 0; i < digits; i++)
            count[i] = 0;
        for(int i = 0; i < sampleDigits.length; i++)
            count[sampleDigits[i]]++;
        for(int i = 0; i < digits; i++)
        {
            if (count[i] > 1)
                return false;
        }
        return true;
    }


}
