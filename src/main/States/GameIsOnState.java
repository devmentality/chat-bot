package main.States;

import main.Bot;
import main.Commands.*;
import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.GameController;
import main.GameLogic.GameResult;
import main.GameLogic.GuessResult;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;
import java.util.ArrayList;

public class GameIsOnState extends StateBase
{
    public GameIsOnState(Bot bot, INewRepository repository)
    {
        super(bot, repository);
        commands = new ICommand[]
        {
                new ResignCommand(bot, repository),
                new AttemptsCommand(bot, repository)
        };
    }

    @Override
    protected ArrayList<Response> handleNoncommandRequest(User user, String request)
    {
        int[] guessedDigits;
        GuessResult result;
        try
        {
            guessedDigits = GameController.parseGuess(request);
            result = user.unfinishedGame.respondOnGuess(guessedDigits, user.unfinishedGame.digitsToGuess.length);

            return Response.compose(respondOnGameAttempt(user, guessedDigits, result));
        }
        catch (Exception ex)
        {
            return Response.compose(new PlainResponse(user.id,
                    String.format(Strings.guessFormatFail, user.unfinishedGame.digitsToGuess.length)));
        }
    }

    private Response respondOnGameAttempt(User user, int[] guessedDigits, GuessResult result)
    {
        PlainResponse response = new PlainResponse(user.id);
        response.addMessageToContent(
                String.format(Strings.guessResultTemplate, result.amountOfBulls, result.amountOfCows));
        if (GameController.isVictoriousGuess(result, guessedDigits.length))
        {
            response.addMessageToContent(Strings.congratulations);
            user.gameResults.add(new GameResult(true));
            bot.changeState(bot.initializedState);
        }
        else if(GameController.isInLosingState(user.unfinishedGame))
        {
            response.addMessageToContent(Strings.losePhrase);
            user.gameResults.add(new GameResult(false));
            bot.changeState(bot.initializedState);
        }
        repository.updateUser(user);
        return response;
    }
}
