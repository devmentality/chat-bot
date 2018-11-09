package main.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentNewInMemoryRepo implements INewRepository
{
    private HashMap<Long, User> users;
    private ReentrantLock repositoryLock;

    public ConcurrentNewInMemoryRepo()
    {
        users = new HashMap<>();
        repositoryLock = new ReentrantLock();
    }

    @Override
    public ArrayList<User> getAll()
    {
        repositoryLock.lock();

        ArrayList<User> allUsers = new ArrayList<>();
        for(User user: users.values())
            allUsers.add((User)user.clone());

        repositoryLock.unlock();
        return allUsers;
    }

    @Override
    public User getUser(long id)
    {
        repositoryLock.lock();

        if (!users.containsKey(id))
            throw new IllegalArgumentException("user with this id does not exist");
        User requestedUser = (User)users.get(id).clone();

        repositoryLock.unlock();
        return requestedUser;
    }

    @Override
    public void updateUser(User user)
    {
        repositoryLock.lock();

        if (user == null)
            throw new IllegalArgumentException("null is not allowed");
        if (!users.containsKey(user.id))
            throw new IllegalArgumentException("user with this id does not exist");
        users.put(user.id, (User)user.clone());

        repositoryLock.unlock();
    }

    @Override
    public void addUser(User user)
    {
        repositoryLock.lock();

        if (user == null)
            throw new IllegalArgumentException("null is not allowed");
        if (users.containsKey(user.id))
            throw new IllegalArgumentException("user with this id already exists");
        users.put(user.id, (User)user.clone());

        repositoryLock.unlock();
    }
}
