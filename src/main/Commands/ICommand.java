package main.Commands;

public interface ICommand
{
    void execute(String... value);
    String getName();
}
