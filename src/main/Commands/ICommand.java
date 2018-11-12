package main.Commands;

import main.Data.User;
import main.Response;

import java.util.ArrayList;

public interface ICommand
{
    ArrayList<Response> execute(User user, String... value);
    String getName();
    int getAmountOfArgs();
}
