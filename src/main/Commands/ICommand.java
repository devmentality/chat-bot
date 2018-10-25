package main.Commands;

import java.util.ArrayList;

public interface ICommand
{
    ArrayList<String> execute(String... value);
    String getName();
}
