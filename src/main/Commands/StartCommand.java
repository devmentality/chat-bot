package main.Commands;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.States.StartedState;

public class StartCommand extends CommandBase
{
    public StartCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer, "start");
    }

    @Override
    public void execute(String... value)
    {
        stateMachine.changeState(new StartedState(stateMachine, repository, writer));
    }
}
