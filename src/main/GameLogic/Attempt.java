package main.GameLogic;

public class Attempt
{
    private int[] guess;
    private GuessResult result;

    public Attempt(int[] guess, GuessResult result)
    {
        this.guess = guess;
        this.result = result;
    }

    public GuessResult getResult() {
        return result;
    }

    public int[] getGuess() {
        return guess;
    }
}
