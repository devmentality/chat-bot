package main;

import java.util.ArrayList;

public interface IAppRepository
{
    void addUser(String username);
    boolean hasUser(String username);
    void addGameResult(String playerName, GameResult gameResult);
    ArrayList<GameResult> getGameResults(String playerName);
}
