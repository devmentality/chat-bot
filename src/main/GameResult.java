package main;

public class GameResult
{
    private int attempts;
    private boolean isVictory;

    public GameResult(int attempts, boolean isVictory)
    {
        this.attempts = attempts;
        this.isVictory = isVictory;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean isVictory() {
        return isVictory;
    }
}
