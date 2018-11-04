package main.Commands;

import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Attempt;
import main.IStateMachine;
import main.PlainResponse;
import main.Resources.Strings;
import main.GameLogic.GuessResult;
import main.Response;

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
	public ArrayList<Response> execute(User user, String... value)
	{
		if (user.unfinishedGame.attempts.size() == 0)
			return Response.compose(new PlainResponse(user.id, Strings.noAttempts));

		PlainResponse response = new PlainResponse(user.id);
        for (Attempt attempt: user.unfinishedGame.attempts)
       	{
        	response.addMessageToContent(joinGuess(attempt.getGuess()));
			response.addMessageToContent(makeString(attempt.getResult()));
        }

        return Response.compose(response);
	}

	private String joinGuess(int []guess)
	{
		StringBuilder builder = new StringBuilder();
		for(int el: guess)
			builder.append(el);

		return builder.toString();
	}
}
