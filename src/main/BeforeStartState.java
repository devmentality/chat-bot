package main;

import main.Commands.ICommand;
import main.Commands.StartCommand;
import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.Resources.Strings;

public class BeforeStartState extends StateBase
{
    public BeforeStartState(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer);
        commands = new ICommand[]
        {
                new StartCommand(stateMachine, repository, writer)
        };
    }

    @Override
    public void activate()
    {
        writer.write(Strings.startMessage);
    }

    @Override
    protected void handleNoncommandRequest(String request)
    {
        writer.write(Strings.startRequest);
    }
}
