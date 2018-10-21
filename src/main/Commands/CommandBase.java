package main.Commands;

import main.Data.IAppRepository;
import main.IStateMachine;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class CommandBase implements ICommand
{
    protected String commandName;
    protected IStateMachine stateMachine;
    protected IAppRepository repository;

    public CommandBase(IStateMachine stateMachine, IAppRepository repository, String commandName)
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

    protected ArrayList<String> constructOutput(String... values)
    {
        return new ArrayList<>(Arrays.asList(values));
    }
}
