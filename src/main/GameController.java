package main;

public class GameController implements IController
{
    private Bot bot;

    public GameController(Bot bot)
    {
        this.bot = bot;
    }

    public boolean processRequest(String request)
    {
        if (request.equals("play"))
            bot.sendMessage("Let's play");
        else
            return false;
        return true;
    }
}
