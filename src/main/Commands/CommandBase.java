package main.Commands;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;

public abstract class CommandBase implements ICommand
{
    protected String commandName;
    protected IStateMachine stateMachine;
    protected IAppRepository repository;
    protected IMessageWriter writer;

    public CommandBase(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer, String commandName)
    {
        this.stateMachine = stateMachine;
        this.repository = repository;
        this.writer = writer;
        this.commandName = commandName;
    }

    @Override
    public String getName()
    {
        return commandName;
    }
}
