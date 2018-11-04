package main.States;

import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Game;
import main.IStateMachine;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;
import java.util.ArrayList;

public class ChooseGameDifficultyState extends StateBase
{
    public ChooseGameDifficultyState(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository);
    }

    @Override
    protected ArrayList<Response> handleNoncommandRequest(User user, String request)
    {
        int numberOfDigits;
        if (request.equals("easy"))
            numberOfDigits = 2;
        else if (request.equals("medium"))
            numberOfDigits = 4;
        else
            numberOfDigits = 6;

        user.unfinishedGame = new Game(numberOfDigits);
        stateMachine.changeState(new GameIsOnState(stateMachine, repository));
        return Response.compose(new PlainResponse(user.id, String.format(Strings.newGamePhrase, numberOfDigits)));
    }
}
