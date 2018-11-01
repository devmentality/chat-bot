package main.Data;

import java.util.ArrayList;

public interface INewRepository
{
    ArrayList<User> getAll();
    User getUser(int id);
    User getUser(String username);
    void updateUser(User user);
    void addUser(User user);
}
