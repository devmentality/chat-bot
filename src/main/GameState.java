package main;

public class GameState implements IState {
    private Bot bot;

    @Override
    public void setContext(Bot bot)
    {
        this.bot = bot;
    }

    @Override
    public String initialize()
    {
        return "Let's play. I have made a number.";
    }

    @Override
    public String respond(String request)
    {
        return "Unfortunately, game is not ready now:(";
    }
}
