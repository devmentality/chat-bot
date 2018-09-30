package main;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;

public class ExitCommand extends CommandBase
{
    public ExitCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer);
        commandName = "exit";
    }

    @Override
    public void execute()
    {
        writer.write("Goodbye!");
        stateMachine.signalToTerminate();
    }
}
