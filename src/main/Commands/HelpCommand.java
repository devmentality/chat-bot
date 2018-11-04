package main.Commands;

import main.Data.INewRepository;
import main.Data.User;
import main.IStateMachine;
import main.Resources.Strings;
import main.Response;

import java.util.ArrayList;

public class HelpCommand extends CommandBase
{
    public HelpCommand(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository,"help");
    }

    @Override
    public ArrayList<Response> execute(User user, String... value)
    {
        return Response.compose(new Response(user.id, Strings.help));
    }
}
