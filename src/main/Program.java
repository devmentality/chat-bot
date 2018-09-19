package main;

public class Program
{
    public static void main(String[] args)
    {
        IMessageWriter writer = new ConsoleWriter();
        IMessageReader reader = new ConsoleReader();
        IState state = new BasicState();

        Bot bot = new Bot(reader, writer, state);
        bot.execute();
    }
}
