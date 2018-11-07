package main.Commands;

import main.Bot;
import main.Data.INewRepository;
import main.Data.User;
import main.PlainResponse;
import main.Response;
import main.States.GameIsOnState;
import main.IStateMachine;
import main.Resources.Strings;

import java.util.ArrayList;

public class ContinueGameCommand extends CommandBase
{
    public ContinueGameCommand(Bot bot, INewRepository repository)
    {
        super(bot, repository,"continue");
    }

    @Override
    public ArrayList<Response> execute(User user, String... value)
    {
        if (user.unfinishedGame == null)
            return Response.compose(new PlainResponse(user.id, Strings.noSavedGames));

        bot.changeState(bot.gameIsOnState);
        return Response.compose(new PlainResponse(user.id, Strings.continueGamePhrase));
    }
}
