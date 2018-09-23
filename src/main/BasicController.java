package main;

public class BasicController implements IController
{
    private Bot bot;

    public BasicController(Bot bot)
    {
        this.bot = bot;
    }

    public boolean processRequest(String request)
    {
        if (request.equals("start"))
            bot.sendMessage(getGreeting());
        else if (request.equals("help"))
            bot.sendMessage(getHelp());
        else if (request.equals("exit"))
            bot.sendMessage(getGoodbye());
        else
            return false;
        return true;
    }

    private String getGreeting()
    {
        return "Hello!";
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
