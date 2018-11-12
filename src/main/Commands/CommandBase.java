package main.Commands;

import main.Bot;
import main.Data.INewRepository;

public abstract class CommandBase implements ICommand
{
    protected String commandName;
    protected Bot bot;
    protected INewRepository repository;

    public CommandBase(Bot bot, INewRepository repository, String commandName)
    {
        this.bot = bot;
        this.repository = repository;
        this.commandName = commandName;
    }

    @Override
    public String getName()
    {
        return commandName;
    }

    @Override
    public int getAmountOfArgs()
    {
        return 0;
    }
}
