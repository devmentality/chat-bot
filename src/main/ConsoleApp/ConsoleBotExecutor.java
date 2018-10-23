package main.ConsoleApp;

import main.Bot;
import main.Data.ConcurrentInMemoryRepo;
import main.Data.InMemoryRepository;
import main.IO.IMessageReader;
import main.IO.IMessageWriter;
import main.Resources.Strings;
import main.Session;

import java.util.ArrayList;

public class ConsoleBotDecorator
{
    private InMemoryRepository repository;
    private IMessageWriter writer;
    private IMessageReader reader;

    public ConsoleBotDecorator(InMemoryRepository repository, IMessageReader reader, IMessageWriter writer)
    {
        this.repository = repository;
        this.reader = reader;
        this.writer = writer;
    }

    public void execute()
    {
        writer.write(Strings.nameRequest);
        String username = reader.read();

        Bot chatBot = new Bot(repository, new Session(username));
        writer.write(Strings.introduction);

        while (!chatBot.isTerminated())
        {
            String message = reader.read();
            ArrayList<String> replies = chatBot.processRequest(message);
            for(String reply: replies)
                writer.write(reply);
        }
    }
}
