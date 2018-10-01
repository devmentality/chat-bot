package main.Commands;

import main.Commands.CommandBase;
import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;

public class HelpCommand extends CommandBase
{
    public HelpCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer, "help");
    }

    @Override
    public void execute()
    {
        writer.write("Bot help");
    }
}
