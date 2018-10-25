package main.Commands;

import main.Data.IAppRepository;
import main.IStateMachine;
import main.Resources.Strings;
import main.States.StartedState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartCommand extends CommandBase
{
    public StartCommand(IStateMachine stateMachine, IAppRepository repository)
    {
        super(stateMachine, repository, "start");
    }

    @Override
    public ArrayList<String> execute(String... value)
    {
        stateMachine.changeState(new StartedState(stateMachine, repository));
        return new ArrayList<>(Arrays.asList(Strings.nameRequest));
    }
}
