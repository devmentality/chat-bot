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
        return new ArrayList<>(users.values());
    }

    @Override
    public synchronized User getUser(int id)
    {
        if (!users.containsKey(id))
            throw new IllegalArgumentException("user with this id does not exist");
        return users.get(id);
    }

    @Override
    public synchronized User getUser(String username) {
        return null;
    }

    @Override
    public synchronized void updateUser(User user) {

    }

    @Override
    public synchronized void addUser(User user)
    {
        if (user == null)
            throw new IllegalArgumentException("null is not allowed");
        if (users.containsKey(user.id))
            throw new IllegalArgumentException("user with this id already exist");
        users.put(user.id, user);
    }
}
