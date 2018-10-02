package main.Commands;

import java.util.Arrays;

import main.IStateMachine;
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
	}

	private String makeString(GuessResult result)
	{
		String answer = "";
		Integer bulls = result.amountOfBulls;
		Integer cows = result.amountOfCows;
		answer = answer.concat("Bulls: ");
		answer = answer.concat(bulls.toString());
		answer = answer.concat(" Cows: ");
		answer = answer.concat(cows.toString());
		return answer;
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
