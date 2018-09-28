package main.Data;

import main.GameResult;

import java.util.HashMap;
import java.util.ArrayList;


public class InMemoryRepository implements IAppRepository
{
    private HashMap<String, ArrayList<GameResult>> repository;

    public InMemoryRepository()
    {
        repository = new HashMap<>();
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
            throw new IllegalArgumentException("User with this name does't exist.");
        repository.get(playerName).add(gameResult);
    }

    @Override
    public ArrayList<GameResult> getGameResults(String playerName)
    {
        if (!hasUser(playerName))
            throw new IllegalArgumentException("User with this name does't exist.");
        return repository.get(playerName);
    }
}
