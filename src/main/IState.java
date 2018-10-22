package main;

import java.util.ArrayList;

public interface IState
{
    ArrayList<String> processRequest(String request);
    ArrayList<String> getAvailableCommands();
}
