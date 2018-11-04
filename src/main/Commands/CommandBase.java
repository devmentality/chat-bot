package main.Commands;

import main.Data.IAppRepository;
import main.Data.INewRepository;
import main.IStateMachine;
import main.Response;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class CommandBase implements ICommand
{
    protected String commandName;
    protected IStateMachine stateMachine;
    protected INewRepository repository;

    public CommandBase(IStateMachine stateMachine, INewRepository repository, String commandName)
    {
        this.stateMachine = stateMachine;
        this.repository = repository;
        this.commandName = commandName;
    }

    @Override
    public String getName()
    {
        return commandName;
    }
}
