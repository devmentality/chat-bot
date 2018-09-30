package main;

import main.Data.IAppRepository;
import main.Data.InMemoryRepository;
import main.IO.ConsoleReader;
import main.IO.ConsoleWriter;
import main.IO.IMessageReader;
import main.IO.IMessageWriter;

public class Program
{
    public static void main(String[] args)
    {
        IMessageWriter writer = new ConsoleWriter();
        IMessageReader reader = new ConsoleReader();
        IAppRepository repository = new InMemoryRepository();

        while(true)
        {
            Bot bot = new Bot(reader, writer, repository);
            bot.execute();
        }
    }
}
