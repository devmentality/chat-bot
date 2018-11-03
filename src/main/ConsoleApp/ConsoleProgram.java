package main.ConsoleApp;

import main.ConsoleApp.ConsoleBotExecutor;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.InMemoryRepository;
import main.IO.ConsoleReader;
import main.IO.ConsoleWriter;
import main.IO.IMessageReader;
import main.IO.IMessageWriter;
import main.Resources.Strings;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleProgram
{
    private static void printReplies(ArrayList<String> replies)
    {
        for(String reply: replies)
            System.out.println(reply);
    }

    public static void main(String[] args)
    {
        ConcurrentNewInMemoryRepo repository = new ConcurrentNewInMemoryRepo();
        IMessageReader reader = new ConsoleReader();
        IMessageWriter writer = new ConsoleWriter();

        while (true)
        {
            ConsoleBotExecutor executor = new ConsoleBotExecutor(repository, reader, writer);
            executor.execute();
        }
    }
}
