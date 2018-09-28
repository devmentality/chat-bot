package main;

import main.Data.IAppRepository;

public class BasicController implements IController
{
    private Bot bot;
    private IControllerState state;
    private IAppRepository repository;

    public BasicController(Bot bot, IAppRepository repository)
    {
        this.bot = bot;
        this.repository = repository;
        state = new BeforeStartState(this, this.bot, repository);
    }

    public boolean processRequest(String request)
    {
        return state.processRequest(request);
    }

    public void changeState(IControllerState newState)
    {
        state = newState;
    }
}
