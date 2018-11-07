package main.Commands;

import main.Bot;
import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Game;
import main.IStateMachine;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;
import main.States.InitializedState;
import java.util.ArrayList;
import java.util.Arrays;

public class StopGameCommand extends CommandBase
{
    public StopGameCommand(Bot bot, INewRepository repository)
    {
        super(bot, repository, "stop");
    }

    @Override
    public ArrayList<Response> execute(User user, String... value)
    {
        bot.changeState(bot.initializedState);
        return Response.compose(new PlainResponse(user.id, Strings.onGameStop));
    }
}
