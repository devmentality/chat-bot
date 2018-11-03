package main.States;

import main.Data.User;

import java.util.ArrayList;

public interface IState
{
    ArrayList<String> processRequest(User user, String request);
    ArrayList<String> getAvailableCommands();
}
