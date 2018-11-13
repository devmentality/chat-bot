package main.Commands;

import main.Bot;
import main.Data.Challenge;
import main.Data.ChallengeRepository;
import main.Data.INewRepository;
import main.Data.User;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;

import java.util.ArrayList;

public class CancelChallengeCommand extends CommandBase
{
    private ChallengeRepository challengeRepository;

    public CancelChallengeCommand(Bot bot, INewRepository repository, ChallengeRepository challengeRepository)
    {
        super(bot, repository, "cancelchallenge");
        this.challengeRepository = challengeRepository;
    }

    @Override
    public ArrayList<Response> execute(User user, String... args)
    {
        try
        {
            Challenge usersChallenge = challengeRepository.pickChallenge(user.id);
            user.points += usersChallenge.points;
            repository.updateUser(user);

            return Response.compose(new PlainResponse(user.id, Strings.challengeCancelled));
        }
        catch (IllegalArgumentException ex)
        {
            return Response.compose(new PlainResponse(user.id, Strings.cantCancelChallenge));
        }
    }
}
