package main.GameLogic;

public class GuessResult
{
    public int amountOfBulls;
    public int amountOfCows;

    public GuessResult(int bulls, int cows)
    {
        amountOfCows = cows;
        amountOfBulls = bulls;
    }

    @Override
    public int hashCode() {
        return amountOfBulls ^ amountOfCows * 11;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GuessResult))
            return false;
        GuessResult other = (GuessResult)obj;
        return other.amountOfBulls == amountOfBulls &&
                other.amountOfCows == amountOfCows;
    }
}
