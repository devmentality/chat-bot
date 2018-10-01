package main;

import main.Commands.ICommand;
import main.Data.IAppRepository;
import main.IO.IMessageWriter;

public abstract class StateBase implements IState
{
    protected IAppRepository repository;
    protected IStateMachine stateMachine;
    protected IMessageWriter writer;
    protected ICommand[] commands;

    public StateBase(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        this.stateMachine = stateMachine;
        this.repository = repository;
        this.writer = writer;
    }

    @Override
    public void processRequest(String request)
    {
        for(ICommand command: commands)
        {
            if (request.equals(command.getName()))
            {
                command.execute();
                return;
            }
        }
        handleNoncommandRequest(request);
    }

    protected void handleNoncommandRequest(String request)
    {
        writer.write("I don't understand:(");
    }
}
