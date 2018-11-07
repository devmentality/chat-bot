package main.Commands;

import main.Bot;
import main.Data.IAppRepository;
import main.Data.INewRepository;
import main.IStateMachine;
import main.Response;

import java.util.ArrayList;
import java.util.Arrays;

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
}
