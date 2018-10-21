package main.Commands;

import main.GameLogic.Attempt;
import main.IStateMachine;
import main.Resources.Strings;
import main.Data.IAppRepository;
import main.GameLogic.Game;
import main.GameLogic.GuessResult;
import main.IO.IMessageWriter;

import java.util.ArrayList;
import java.util.List;

public class AttemptsCommand extends CommandBase 
{	
	private Game currentGame;
	
	public AttemptsCommand(IStateMachine stateMachine, IAppRepository repository, Game currentGame)
	{
		super(stateMachine, repository, "attempts");
		this.currentGame = currentGame;
	}

	private String makeString(GuessResult result)
	{
		return String.format(Strings.guessResultTemplate, result.amountOfBulls, result.amountOfCows);
	}
	
	@Override
	public ArrayList<String> execute(String... value)
	{
		if (currentGame.attempts.size() == 0)
			return constructOutput(Strings.noAttempts);

		ArrayList<String> output = new ArrayList<>();
        for (Attempt attempt: currentGame.attempts)
       	{
        	output.add(joinGuess(attempt.getGuess()));
			output.add(makeString(attempt.getResult()));
        }

        return output;
	}

	private String joinGuess(int []guess)
	{
		StringBuilder builder = new StringBuilder();
		for(int el: guess)
			builder.append(el);

		return builder.toString();
	}
}
