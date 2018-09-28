package main;

import main.Data.IAppRepository;
import main.Data.InMemoryRepository;

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
