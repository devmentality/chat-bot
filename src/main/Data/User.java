package main.Data;

import main.GameLogic.Game;
import main.GameLogic.GameResult;

import java.util.ArrayList;

public class User implements Cloneable
{
    public long id;
    public String username;
    public Game unfinishedGame;
    public ArrayList<GameResult> gameResults;
    public ChallengeDescription challengeDescription;

    public User()
    {
        gameResults = new ArrayList<>();
    }

    @Override
    public Object clone()
    {
        try
        {
            User clone = (User) super.clone();
            if (unfinishedGame != null)
                clone.unfinishedGame = (Game)unfinishedGame.clone();
            ArrayList<GameResult> clonedResults = new ArrayList<>();
            for (GameResult result: gameResults)
                clonedResults.add((GameResult)result.clone());
            clone.gameResults = clonedResults;
            return clone;
        }
        catch (CloneNotSupportedException ex)
        {
            return null;
        }
    }
}
