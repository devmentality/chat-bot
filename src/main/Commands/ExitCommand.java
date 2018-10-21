package main.Commands;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;

import java.util.ArrayList;
import java.util.List;

public class ExitCommand extends CommandBase
{
    public ExitCommand(IStateMachine stateMachine, IAppRepository repository)
    {
        super(stateMachine, repository, "exit");
    }

    @Override
    public ArrayList<String> execute(String... value)
    {
        stateMachine.signalToTerminate();
        return constructOutput(Strings.goodbye);
    }
}
