package main.Data;

import java.util.ArrayList;
import main.GameResult;

public interface IAppRepository
{
    void addUser(String username);
    boolean hasUser(String username);
    void addGameResult(String playerName, GameResult gameResult);
    ArrayList<GameResult> getGameResults(String playerName);
}
