package main.GameLogic;

public class Attempt implements Cloneable
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

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Attempt clonedAttempt = (Attempt)super.clone();
        clonedAttempt.result = (GuessResult)result.clone();
        clonedAttempt.guess = guess.clone();
        return clonedAttempt;
    }
}
