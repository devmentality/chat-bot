package main;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;

public abstract class CommandBase implements ICommand
{
    protected String commandName;
    protected IStateMachine stateMachine;
    protected IAppRepository repository;
    protected IMessageWriter writer;

    public CommandBase(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        this.stateMachine = stateMachine;
        this.repository = repository;
        this.writer = writer;
    }

    @Override
    public String getName()
    {
        return commandName;
    }
}
