package main.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class ConcurrentNewInMemoryRepo implements INewRepository
{
    private HashMap<Integer, User> users;

    public ConcurrentNewInMemoryRepo()
    {
        users = new HashMap<>();
    }

    @Override
    public synchronized ArrayList<User> getAll()
    {
        ArrayList<User> allUsers = new ArrayList<>();
        for(User user: users.values())
            allUsers.add((User)user.clone());
        return allUsers;
    }

    @Override
    public synchronized User getUser(int id)
    {
        if (!users.containsKey(id))
            throw new IllegalArgumentException("user with this id does not exist");
        return (User)users.get(id).clone();
    }

    @Override
    public synchronized void updateUser(User user)
    {
        if (user == null)
            throw new IllegalArgumentException("null is not allowed");
        if (!users.containsKey(user.id))
            throw new IllegalArgumentException("user with this id does not exist");
        users.put(user.id, (User)user.clone());
    }

    @Override
    public synchronized void addUser(User user)
    {
        if (user == null)
            throw new IllegalArgumentException("null is not allowed");
        if (users.containsKey(user.id))
            throw new IllegalArgumentException("user with this id already exists");
        users.put(user.id, (User)user.clone());
    }
}
