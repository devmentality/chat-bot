package main.Commands;

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
    public ContinueGameCommand(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository,"continue");
    }

    @Override
    public ArrayList<Response> execute(User user, String... value)
    {
        if (user.unfinishedGame == null)
            return Response.compose(new PlainResponse(user.id, Strings.noSavedGames));

        stateMachine.changeState(new GameIsOnState(stateMachine, repository));
        return Response.compose(new PlainResponse(user.id, Strings.continueGamePhrase));
    }
}
