package main.Data;

import java.util.ArrayList;

import main.Game;
import main.GameResult;

public interface IAppRepository
{
    void addUser(String username);
    boolean hasUser(String username);
    void addGameResult(String playerName, GameResult gameResult);
    ArrayList<GameResult> getGameResults(String playerName);
    void deleteUnfinishedGameResult(String playerName);
    void addUnfinishedGameResult(String playerName, Game game);
    boolean hasUnfinishedGame(String playerName);
    Game getUnfinishedGame(String playerName);
}
