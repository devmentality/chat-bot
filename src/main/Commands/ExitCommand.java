package main.Commands;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;

public class ExitCommand extends CommandBase
{
    public ExitCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer, "exit");
    }

    @Override
    public void execute()
    {
        writer.write("Goodbye!");
        stateMachine.signalToTerminate();
    }
}
