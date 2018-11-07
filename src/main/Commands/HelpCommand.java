package main.Commands;

import main.Bot;
import main.Data.INewRepository;
import main.Data.User;
import main.IStateMachine;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;

import java.util.ArrayList;

public class HelpCommand extends CommandBase
{
    public HelpCommand(Bot bot, INewRepository repository)
    {
        super(bot, repository,"help");
    }

    @Override
    public ArrayList<Response> execute(User user, String... value)
    {
        return Response.compose(new PlainResponse(user.id, Strings.help));
    }
}
