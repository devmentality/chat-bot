package main.Data;

import java.util.ArrayList;

import main.GameLogic.Game;
import main.GameLogic.GameResult;

public interface IAppRepository
{
    void addUser(String username);
    boolean hasUser(String username);
    void addGameResult(String playerName, GameResult gameResult);
    ArrayList<GameResult> getGameResults(String playerName);
    void deleteUnfinishedGame(String playerName);
    void addUnfinishedGame(String playerName, Game game);
    boolean hasUnfinishedGame(String playerName);
    Game getUnfinishedGame(String playerName);
}
