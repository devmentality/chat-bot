package main;

import main.Data.IAppRepository;

public class BeforeStartState implements IControllerState
{
    private Bot bot;
    private BasicController controller;
    private IAppRepository repository;

    public BeforeStartState(BasicController controller, Bot bot, IAppRepository repository)
    {
        this.controller = controller;
        this.bot = bot;
        this.repository = repository;
    }

    @Override
    public boolean processRequest(String request) {
        if (request.equals("start"))
        {
            bot.sendMessage("Hello");
            bot.sendMessage("What is your name?");
            controller.changeState(new StartedState(controller, bot, repository));
        }
        else
            bot.sendMessage("Send 'start' to start dialog");
        return true;
    }
}
