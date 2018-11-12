package main.Commands;

import main.Bot;
import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.GameResult;
import main.IStateMachine;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;
import main.States.InitializedState;

import java.util.ArrayList;
import java.util.Arrays;

public class ResignCommand extends CommandBase
{
    public ResignCommand(Bot bot, INewRepository repository)
    {
        super(bot, repository, "resign");
    }

    @Override
    public ArrayList<Response> execute(User user, String... value)
    {
        if (user.unfinishedGame == null)
            return Response.compose(new PlainResponse(user.id, Strings.noSavedGames));

        user.unfinishedGame = null;
        user.gameResults.add(new GameResult(false));

        repository.updateUser(user);
        bot.changeState(bot.initializedState);
        return Response.compose(new PlainResponse(user.id, Strings.onGameResign));
    }
}
