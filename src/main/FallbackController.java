package main;

public class FallbackController implements IController
{
    private Bot bot;

    public FallbackController(Bot bot)
    {
        this.bot = bot;
    }

    public boolean processRequest(String request)
    {
        bot.sendMessage("Sorry, I don't understand :(");
        return true;
    }
}
