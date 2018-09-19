package main;

public class BasicState implements IState
{
    private Bot bot;

    @Override
    public void setContext(Bot bot)
    {
        this.bot = bot;
    }

    @Override
    public String initialize()
    {
        return "Hello!";
    }

    @Override
    public String respond(String request)
    {
        if (request.equals("help"))
            return getHelp();
        if (request.equals("play"))
            return bot.setAndInitializeState(new GameState());
        if (request.equals("exit"))
            return getGoodbye();
        return "I don't understand:(";
    }

    private String getHelp()
    {
        return "I'm chat-bot, playing \"bulls and cows\" game.\n" +
            "I understand these commands:\n" +
            "1. help - to see this message\n" +
            "2. play - to start new \"bulls and cows\" game\n" +
            "3. exit - to say goodbye to me";
    }

    private String getGoodbye()
    {
        return "Goodbye!";
    }
}
