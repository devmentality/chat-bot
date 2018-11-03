package main.States;

import main.Commands.*;
import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Game;
import main.GameLogic.GameController;
import main.GameLogic.GameResult;
import main.GameLogic.GuessResult;
import main.IStateMachine;
import main.Resources.Strings;

import java.util.ArrayList;
import java.util.Arrays;

public class GameIsOnState extends StateBase
{
    public GameIsOnState(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository);
        commands = new ICommand[]
        {
                new HelpCommand(stateMachine, repository),
                new ResignCommand(stateMachine, repository),
                new StopGameCommand(stateMachine, repository),
                new AttemptsCommand(stateMachine, repository)
        };
    }

    @Override
    protected ArrayList<String> handleNoncommandRequest(User user, String request)
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
            return new ArrayList<>(Arrays.asList(
                    String.format(Strings.guessFormatFail, user.unfinishedGame.digitsToGuess.length)));
        }
    }

    private ArrayList<String> respondOnGameAttempt(User user, int[] guessedDigits, GuessResult result)
    {
    	ArrayList<String> output = new ArrayList<>();
        output.add(String.format(Strings.guessResultTemplate, result.amountOfBulls, result.amountOfCows));
        if (GameController.isVictoriousGuess(result, guessedDigits.length))
        {
            output.add(Strings.congratulations);
            user.gameResults.add(new GameResult(true));
            stateMachine.changeState(new InitializedState(stateMachine, repository));
        }
        else if(GameController.isInLosingState(user.unfinishedGame))
        {
            output.add(Strings.losePhrase);
            user.gameResults.add(new GameResult(false));
            stateMachine.changeState(new InitializedState(stateMachine, repository));
        }
        return output;
    }
}
