package main.GameLogic;

public class Statistics
{
    private int victories;
    private int losses;
    private int total;

    public Statistics(int total, int victories, int losses)
    {
        this.victories = victories;
        this.losses = losses;
        this.total = total;
    }

    public int getVictories() {
        return victories;
    }

    public int getLosses() {
        return losses;
    }

    public int getTotal()
    {
        return total;
    }
}
