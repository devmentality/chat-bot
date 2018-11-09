package main.Data;

public class ChallengeDescription implements Cloneable
{
    public long creatorId;
    public int points;

    public ChallengeDescription(long creatorId, int points)
    {
        this.creatorId = creatorId;
        this.points = points;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return  super.clone();
    }
}


