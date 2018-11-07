package main.Commands;

import main.Bot;
import main.Data.Challenge;
import main.Data.ChallengeRepository;
import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Game;
import main.IStateMachine;
import main.PlainResponse;
import main.Response;

import java.util.ArrayList;

public class AddChallengeCommand extends CommandBase
{
    private ChallengeRepository challengeRepository;

    public AddChallengeCommand(Bot bot, INewRepository repository,
                               ChallengeRepository challengeRepository)
    {
        super(bot, repository, "addchallenge");
        this.challengeRepository = challengeRepository;
    }

    @Override
    public ArrayList<Response> execute(User user, String... args)
    {
        try
        {
            challengeRepository.addChallenge(user.id, new Challenge(user.id, new Game(4),0));
        }
        catch (Exception ex)
        {
            return Response.compose(new PlainResponse(user.id, "You already have a challenge"));
        }

        return Response.compose(new PlainResponse(user.id, "Challenge successfully created"));
    }
}
