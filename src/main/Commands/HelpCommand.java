package main.Commands;

import main.Data.INewRepository;
import main.Data.User;
import main.IStateMachine;
import main.Resources.Strings;
import java.util.ArrayList;

public class HelpCommand extends CommandBase
{
    public HelpCommand(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository,"help");
    }

    @Override
    public ArrayList<String> execute(User user, String... value)
    {
        return constructOutput(Strings.help);
    }
}
