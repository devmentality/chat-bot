package main;

public class ConsoleWriter implements IMessageWriter
{
    @Override
    public void write(String message)
    {
        System.out.println(message);
    }
}
