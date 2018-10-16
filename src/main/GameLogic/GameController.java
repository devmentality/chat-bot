package main.GameLogic;

public class GameController
{
    public static int[] parseGuess(String guess)
    {
        int[] digits = new int[guess.length()];
        for(int index = 0; index < guess.length(); index++)
            digits[index] = Integer.parseInt(String.valueOf(guess.charAt(index)));
        return digits;
    }

    public static boolean isVictoriousGuess(GuessResult guessResult)
    {
        return guessResult.amountOfBulls == Game.amountOfDigits;
    }

    public static boolean isInLosingState(Game game)
    {
        return game.attempts.size() >= Game.attemptsToLose;
    }
}
