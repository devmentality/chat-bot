package main.GameLogic;

public class GameResult
{
    private int attempts;
    private boolean isVictory;

    public GameResult(boolean isVictory)
    {
        this.isVictory = isVictory;
    }

    public boolean isVictory() {
        return isVictory;
    }
}
