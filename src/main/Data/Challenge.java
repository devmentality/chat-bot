package main.Data;

import main.GameLogic.Game;

public class Challenge
{
    public Game game;          //Game created for another user(not a specific user)
    public int points;         //Player can win or lose points
    public long creatorId;

    public Challenge(long creatorId, Game game, int points)
    {
        this.creatorId = creatorId;
        this.game = game;
        this.points = points;
    }

    public Challenge(long creatorId, int[] digits, int points)
    {
        this.creatorId = creatorId;
        this.game = new Game(digits);
        this.points = points;
    }
}
