package main.States;

import main.Bot;
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
    public ChooseGameDifficultyState(Bot bot, INewRepository repository)
    {
        super(bot, repository);
    }

    @Override
    protected ArrayList<Response> handleNoncommandRequest(User user, String request)
    {
        int numberOfDigits;
        switch(request)
        {
            case "easy":
                numberOfDigits = 2;
                break;
            case "medium":
                numberOfDigits = 4;
                break;
            case "hard":
                numberOfDigits = 6;
                break;
            default:
                return Response.compose(new PlainResponse(user.id, Strings.noSuchMode));
        }

        user.unfinishedGame = new Game(numberOfDigits);
        repository.updateUser(user);

        bot.changeState(bot.gameIsOnState);
        return Response.compose(new PlainResponse(user.id, String.format(Strings.newGamePhrase, numberOfDigits)));
    }
}
