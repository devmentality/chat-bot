package main.States;

import main.Commands.*;
import main.Data.IAppRepository;
import main.GameLogic.Game;
import main.GameLogic.GameController;
import main.GameLogic.GameResult;
import main.GameLogic.GuessResult;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;
import main.Session;

import java.util.ArrayList;
import java.util.Arrays;

public class GameIsOnState extends StateBase
{
    private Game game;
    private Session session;

    public GameIsOnState(IStateMachine stateMachine, IAppRepository repository,
                         Game game, Session session)
    {
        super(stateMachine, repository);
        this.game = game;
        this.session = session;
        commands = new ICommand[]
        {
                new ExitCommand(stateMachine, repository),
                new HelpCommand(stateMachine, repository),
                new ResignCommand(stateMachine, repository, session),
                new StopGameCommand(stateMachine, repository, session, game),
                new AttemptsCommand(stateMachine, repository, game)
        };
    }

    @Override
    protected ArrayList<String> handleNoncommandRequest(String request)
    {
        int[] guessedDigits;
        try
        {
            guessedDigits = GameController.parseGuess(request);
        }
        catch (Exception ex)
        {
            return new ArrayList<>(Arrays.asList(Strings.guessFormatFail));
        }

        return respondOnGameAttempt(guessedDigits);
    }

    private ArrayList<String> respondOnGameAttempt(int[] guessedDigits)
    {
    	GuessResult result;
    	try
    	{
    		result = game.respondOnGuess(guessedDigits, game.digitsToGuess.length);
    	}
    	catch (Exception ex)
    	{
            return new ArrayList<>(Arrays.asList(String.format(Strings.guessFormatFail, game.digitsToGuess.length)));
    	}
    	ArrayList<String> output = new ArrayList<>();
        output.add(String.format(Strings.guessResultTemplate, result.amountOfBulls, result.amountOfCows));
        if (GameController.isVictoriousGuess(result, guessedDigits.length))
        {
            output.add(Strings.congratulations);
            repository.addGameResult(session.getUsername(), new GameResult(true));
            stateMachine.changeState(new InitializedState(stateMachine, repository, session));
        }
        else if(GameController.isInLosingState(game))
        {
            output.add(Strings.losePhrase);
            repository.addGameResult(session.getUsername(), new GameResult(false));
            stateMachine.changeState(new InitializedState(stateMachine, repository, session));
        }
        return output;
    }
}
