package main.GameLogic;

public class GameResult implements Cloneable
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

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
