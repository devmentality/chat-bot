package main;

public class Program
{
    public static void main(String[] args)
    {
        IMessageWriter writer = new ConsoleWriter();
        IMessageReader reader = new ConsoleReader();

        Bot bot = new Bot(reader, writer);
        bot.execute();
    }
}
