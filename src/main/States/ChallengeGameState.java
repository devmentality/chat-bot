package main.States;

import main.Bot;
import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.GameController;
import main.GameLogic.GameResult;
import main.GameLogic.GuessResult;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;
import java.util.ArrayList;

public class ChallengeGameState extends StateBase
{
    public ChallengeGameState(Bot bot, INewRepository repository)
    {
        super(bot, repository);
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

            return respondOnGameAttempt(user, guessedDigits, result);
        }
        catch (Exception ex)
        {
            return Response.compose(new PlainResponse(user.id,
                    String.format(Strings.guessFormatFail, user.unfinishedGame.digitsToGuess.length)));
        }
    }

    private ArrayList<Response> respondOnGameAttempt(User user, int[] guessedDigits, GuessResult result)
    {
        PlainResponse responseToPlayer = new PlainResponse(user.id);
        PlainResponse responseToCreator = new PlainResponse(user.challengeDescription.creatorId);
        responseToPlayer.addMessageToContent(
                String.format(Strings.guessResultTemplate, result.amountOfBulls, result.amountOfCows));
        if (GameController.isVictoriousGuess(result, guessedDigits.length))
        {
            responseToPlayer.addMessageToContent(Strings.congratulations);
            user.gameResults.add(new GameResult(true));

            responseToCreator.addMessageToContent("Your challenge was successfully passed");

            bot.changeState(bot.initializedState);
        }
        else if(GameController.isInLosingState(user.unfinishedGame))
        {
            responseToPlayer.addMessageToContent(Strings.losePhrase);
            user.gameResults.add(new GameResult(false));

            responseToCreator.addMessageToContent("Your challenge was not passed");

            bot.changeState(bot.initializedState);
        }
        return Response.compose(responseToCreator, responseToPlayer);
    }
}
