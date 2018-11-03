package main.Data;

import main.GameLogic.Game;

public class Challenge
{
    private Game game;          //Game created for another user(not a specific user)
    private int points;         //Player can win or lose points

    public Challenge(Game game, int points)
    {
        this.game = game;
        this.points = points;
    }

    public Challenge(int[] digits, int points)
    {
        this.game = new Game(digits);
        this.points = points;
    }
}
