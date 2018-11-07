package main.States;

import main.Bot;
import main.Data.*;
import main.PlainResponse;
import main.Response;

import java.util.ArrayList;

public class ChooseChallengeState extends StateBase
{
    private ChallengeRepository challengeRepository;

    public ChooseChallengeState(Bot bot, INewRepository repository, ChallengeRepository challengeRepository)
    {
        super(bot, repository);
        this.challengeRepository = challengeRepository;
    }

    @Override
    protected ArrayList<Response> handleNoncommandRequest(User user, String request)
    {
        if (request.equals("decline"))
        {
            bot.changeState(bot.initializedState);
            return new ArrayList<>();
        }
        long challengeId = Long.parseLong(request);
        Challenge challenge = challengeRepository.pickChallenge(challengeId);
        user.unfinishedGame = challenge.game;
        user.challengeDescription = new ChallengeDescription(challengeId, challenge.points);

        bot.changeState(bot.challengeGameState);
        PlainResponse responseToCreator = new PlainResponse(challenge.creatorId, "Your challenge was accepted");
        PlainResponse responseToPlayer = new PlainResponse(user.id, "Now we play");
        return Response.compose(responseToCreator, responseToPlayer);
    }
}
