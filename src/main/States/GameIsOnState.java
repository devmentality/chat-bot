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

public class GameIsOnState extends StateBase
{
    private Game game;
    private Session session;

    public GameIsOnState(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer,
                         Game game, Session session)
    {
        super(stateMachine, repository, writer);
        this.game = game;
        this.session = session;
        commands = new ICommand[]
        {
                new ExitCommand(stateMachine, repository, writer),
                new HelpCommand(stateMachine, repository, writer),
                new ResignCommand(stateMachine, repository, writer, session),
                new StopGameCommand(stateMachine, repository, writer, session, game),
                new AttemptsCommand(stateMachine, repository, writer, game)
        };
    }

    @Override
    protected void handleNoncommandRequest(String request)
    {
        int[] guessedDigits;
        try
        {
            guessedDigits = GameController.parseGuess(request);
        }
        catch (Exception ex)
        {
            writer.write(Strings.guessFormatFail);
            return;
        }

        respondOnGameAttempt(guessedDigits);
    }

    private void respondOnGameAttempt(int[] guessedDigits)
    {
    	GuessResult result;
    	try
    	{
    		result = game.respondOnGuess(guessedDigits);
    	}
    	catch (Exception ex)
    	{
    		writer.write(Strings.guessFormatFail);
            return;
    	}
        writer.write(String.format(Strings.guessResultTemplate, result.amountOfBulls, result.amountOfCows));
        if (GameController.isVictoriousGuess(result))
        {
            writer.write(Strings.congratulations);
            repository.addGameResult(session.getUsername(), new GameResult(true));
            stateMachine.changeState(new InitializedState(stateMachine, repository, writer, session));
        }
        else if(GameController.isInLosingState(game))
        {
            writer.write(Strings.losePhrase);
            repository.addGameResult(session.getUsername(), new GameResult(false));
            stateMachine.changeState(new InitializedState(stateMachine, repository, writer, session));
        }
    }
}
