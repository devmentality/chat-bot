package main.Commands;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;

public class ExitCommand extends CommandBase
{
    public ExitCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer, "exit");
    }

    @Override
    public void execute(String... value)
    {
        writer.write(Strings.goodbye);
        stateMachine.signalToTerminate();
    }
}
