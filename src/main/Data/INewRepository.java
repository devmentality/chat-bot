package main.Data;

import java.util.ArrayList;

public interface INewRepository
{
    ArrayList<User> getAll();
    User getUser(long id);
    void updateUser(User user);
    void addUser(User user);
}
