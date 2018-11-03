package main.Commands;

import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Attempt;
import main.IStateMachine;
import main.Resources.Strings;
import main.GameLogic.GuessResult;

import java.util.ArrayList;

public class AttemptsCommand extends CommandBase 
{
	public AttemptsCommand(IStateMachine stateMachine, INewRepository repository)
	{
		super(stateMachine, repository, "attempts");
	}

	private String makeString(GuessResult result)
	{
		return String.format(Strings.guessResultTemplate, result.amountOfBulls, result.amountOfCows);
	}
	
	@Override
	public ArrayList<String> execute(User user, String... value)
	{
		if (user.unfinishedGame.attempts.size() == 0)
			return constructOutput(Strings.noAttempts);

		ArrayList<String> output = new ArrayList<>();
        for (Attempt attempt: user.unfinishedGame.attempts)
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
