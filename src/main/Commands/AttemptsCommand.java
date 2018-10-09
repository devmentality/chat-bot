package main.Commands;

import java.util.Arrays;

import main.IStateMachine;
import main.Resources.Strings;
import main.Session;
import main.Data.IAppRepository;
import main.GameLogic.Game;
import main.GameLogic.GuessResult;
import main.IO.IMessageWriter;

public class AttemptsCommand extends CommandBase 
{	
	private Game currentGame;
	
	public AttemptsCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer,
			Game currentGame)
	{
		super(stateMachine, repository, writer, "attempts");
		this.currentGame = currentGame;
	}

	private String makeString(GuessResult result)
	{
		return String.format(Strings.attemptResult, result.amountOfBulls, result.amountOfCows);
	}
	
	@Override
	public void execute()
	{
        for (int i = 0; i < currentGame.attempts.size(); i++)
       	{
        	String attempt = "";
        	for (int j = 0; j < 4; j++)
        	{
        		Integer number = currentGame.attempts.get(i)[j];
        		attempt = attempt.concat(number.toString());
        	}
        	writer.write(attempt);
        	writer.write(makeString(currentGame.results.get(i)));
        }
	}
}
