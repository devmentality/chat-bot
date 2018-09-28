package main;

import main.Data.IAppRepository;

public class StartedState extends ControllerStateBase
{
    public StartedState(IController controller, Bot bot, IAppRepository repository)
    {
        super(controller, bot, repository);
    }

    @Override
    public boolean processRequest(String request)
    {
        String name = request;
        bot.setUserName(name);
        if (repository.hasUser(name))
            bot.sendMessage(String.format("Hi, my old friend, %s", name));
        else
        {
            bot.sendMessage(String.format("Hello, %s", name));
            repository.addUser(name);
        }
        controller.changeState(new InitializedState(controller, bot, repository));
        return true;
    }
}
