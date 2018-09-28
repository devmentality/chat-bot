package main;

import main.Data.IAppRepository;

public class InitializedState extends ControllerStateBase
{
    public InitializedState(IController controller, Bot bot, IAppRepository repository)
    {
        super(controller, bot, repository);
    }

    @Override
    public boolean processRequest(String request)
    {
        if (request.equals("help"))
            bot.sendMessage(getHelp());
        else if (request.equals("exit"))
            bot.sendMessage(getGoodbye());
        else
            return false;
        return true;
    }

    private String getHelp()
    {
        return "Bot help";
    }

    private String getGoodbye()
    {
        return "Goodbye";
    }
}
