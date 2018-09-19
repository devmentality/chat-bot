package main;

import java.util.Scanner;

public class ConsoleReader implements IMessageReader
{
    private Scanner in;

    public ConsoleReader()
    {
        in = new Scanner(System.in);
    }

    @Override
    public String read()
    {
        return in.nextLine();
    }
}
