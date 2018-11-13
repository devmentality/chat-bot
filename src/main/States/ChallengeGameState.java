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
            user.unfinishedGame = null;
            user.points += user.challengeDescription.points;
            user.challengeDescription = null;

            responseToCreator.addMessageToContent(Strings.yourChallengePassed);

            bot.changeState(bot.initializedState);
        }
        else if(GameController.isInLosingState(user.unfinishedGame))
        {
            responseToPlayer.addMessageToContent(Strings.losePhrase);
            user.gameResults.add(new GameResult(false));
            user.unfinishedGame = null;
            user.points -= user.challengeDescription.points;

            User opponent = repository.getUser(user.challengeDescription.creatorId);
            opponent.points += user.challengeDescription.points;
            repository.updateUser(opponent);

            user.challengeDescription = null;

            responseToCreator.addMessageToContent(Strings.yourChallengeNotPassed);

            bot.changeState(bot.initializedState);
        }
        repository.updateUser(user);
        return Response.compose(responseToCreator, responseToPlayer);
    }
}
