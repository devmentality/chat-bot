package main.Commands;

import main.Data.IAppRepository;
import main.IStateMachine;
import main.Resources.Strings;
import java.util.ArrayList;

public class HelpCommand extends CommandBase
{
    public HelpCommand(IStateMachine stateMachine, IAppRepository repository)
    {
        super(stateMachine, repository, "help");
    }

    @Override
    public ArrayList<String> execute(String... value)
    {
        return constructOutput(Strings.help);
    }
}
