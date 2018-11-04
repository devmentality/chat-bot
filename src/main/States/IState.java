package main.States;

import main.Data.User;
import main.Response;

import java.util.ArrayList;

public interface IState
{
    ArrayList<Response> processRequest(User user, String request);
    ArrayList<String> getAvailableCommands();
}
