package main.Commands;

import main.GameLogic.Attempt;
import main.IStateMachine;
import main.Resources.Strings;
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
		return String.format(Strings.guessResultTemplate, result.amountOfBulls, result.amountOfCows);
	}
	
	@Override
	public void execute()
	{
		if (currentGame.attempts.size() == 0)
		{
			writer.write(Strings.noAttempts);
			return;
		}

        for (Attempt attempt: currentGame.attempts)
       	{
        	writer.write(joinGuess(attempt.getGuess()));
        	writer.write(makeString(attempt.getResult()));
        }
	}

	private String joinGuess(int []guess)
	{
		StringBuilder builder = new StringBuilder();
		for(int el: guess)
			builder.append(el);

		return builder.toString();
	}
}
