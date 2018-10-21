package main.States;

import main.Commands.ICommand;
import main.Commands.StartCommand;
import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;

import java.util.ArrayList;
import java.util.Arrays;

public class BeforeStartState extends StateBase
{
    public BeforeStartState(IStateMachine stateMachine, IAppRepository repository)
    {
        super(stateMachine, repository);
        commands = new ICommand[]
        {
                new StartCommand(stateMachine, repository)
        };
    }

    @Override
    protected ArrayList<String> handleNoncommandRequest(String request)
    {
        return new ArrayList<>(Arrays.asList(Strings.startRequest));
    }
}
