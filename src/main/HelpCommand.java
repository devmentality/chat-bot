package main;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;

public class HelpCommand extends CommandBase
{
    public HelpCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer);
        commandName = "help";
    }

    @Override
    public void execute()
    {
        writer.write("Bot help");
    }
}
