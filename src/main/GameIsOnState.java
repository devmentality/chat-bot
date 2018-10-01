package main;

import main.Commands.ExitCommand;
import main.Commands.HelpCommand;
import main.Commands.ICommand;
import main.Commands.ResignCommand;
import main.Data.IAppRepository;
import main.GameLogic.Game;
import main.GameLogic.GameController;
import main.GameLogic.GameResult;
import main.GameLogic.GuessResult;
import main.IO.IMessageWriter;

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
                new ResignCommand(stateMachine, repository, writer, session)

        };
    }

    @Override
    protected void handleNoncommandRequest(String request)
    {
        doGameAttempt(request);
    }

    private void doGameAttempt(String attemptString)
    {
        try
        {
            int[] guessedDigits = GameController.parseGuess(attemptString);
            GuessResult result = game.respondOnGuess(guessedDigits);
            writer.write(String.format("%d bulls and %d cows", result.amountOfBulls, result.amountOfCows));
            if (result.amountOfBulls == 4)
            {
                writer.write("Congrats, you have won!");
                repository.addGameResult(session.getUsername(), new GameResult(true));
                stateMachine.changeState(new InitializedState(stateMachine, repository, writer, session));
            }
        }
        catch (Exception ex)
        {
            writer.write("You have to provide 4 digits");
        }
    }
}
