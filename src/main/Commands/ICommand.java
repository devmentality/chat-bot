package main.Commands;

import main.Data.User;
import java.util.ArrayList;

public interface ICommand
{
    ArrayList<String> execute(User user, String... value);
    String getName();
}
