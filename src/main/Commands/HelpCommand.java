package main.Commands;

import main.Commands.CommandBase;
import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;

public class HelpCommand extends CommandBase
{
    public HelpCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer, "help");
    }

    @Override
    public void execute(String... value)
    {
        writer.write(Strings.help);
    }
}
