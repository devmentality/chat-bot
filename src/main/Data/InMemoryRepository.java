package main.Data;

import main.GameLogic.Game;
import main.GameLogic.GameResult;

import java.util.HashMap;
import java.util.ArrayList;


public class InMemoryRepository implements IAppRepository
{
    private HashMap<String, ArrayList<GameResult>> repository;
    private HashMap<String, Game> unfinishedGames;
    
    public InMemoryRepository()
    {
        repository = new HashMap<>();
        unfinishedGames = new HashMap<>();
    }

    @Override
    public void addUser(String username)
    {
        if (hasUser(username))
            throw new IllegalArgumentException("User with this name is already added.");
        repository.put(username, new ArrayList<>());
    }

    @Override
    public boolean hasUser(String username)
    {
        return repository.containsKey(username);
    }
    
    @Override
    public void addGameResult(String playerName, GameResult gameResult)
    {
    	if (!hasUser(playerName))
            addUser(playerName);
        repository.get(playerName).add(gameResult);
    }

    @Override
    public ArrayList<GameResult> getGameResults(String playerName)
    {
        if (!hasUser(playerName))
            throw new IllegalArgumentException("User with this name doesn't exist.");
        return repository.get(playerName);
    }
    
    @Override
    public void addUnfinishedGame(String playerName, Game game)
    {
    	unfinishedGames.put(playerName, game);
    }
    
    @Override
    public void deleteUnfinishedGame(String playerName)
    {
    	if (!unfinishedGames.containsKey(playerName))
    		throw new IllegalArgumentException("User doesn't have unfinished game.");
    	unfinishedGames.remove(playerName);
    }
    
    @Override
    public boolean hasUnfinishedGame(String playerName)
    {
    	return unfinishedGames.containsKey(playerName);
    }
    
    @Override
    public Game getUnfinishedGame(String playerName)
    {
    	if (!hasUnfinishedGame(playerName))
    		throw new IllegalArgumentException("User doesn't have unfinished game.");
    	Game unfinishedGame = unfinishedGames.get(playerName);
    	deleteUnfinishedGame(playerName);
    	return unfinishedGame;
    }
}
