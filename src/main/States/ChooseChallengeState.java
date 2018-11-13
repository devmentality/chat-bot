package main.States;

import main.Bot;
import main.Data.*;
import main.PlainResponse;
import main.Resources.Strings;
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
            return Response.compose(new PlainResponse(user.id, Strings.onDeclineChallenges));
        }
        long challengeId;
        Challenge challenge;
        try
        {
            challengeId = Long.parseLong(request);
            challenge = challengeRepository.pickChallenge(challengeId);
        }
        catch (IllegalArgumentException ex)
        {
            bot.changeState(bot.initializedState);
            return Response.compose(new PlainResponse(user.id, "Can't pick the challenge:("));
        }

        user.unfinishedGame = challenge.game;
        user.challengeDescription = new ChallengeDescription(challengeId, challenge.points);
        repository.updateUser(user);

        bot.changeState(bot.challengeGameState);
        PlainResponse responseToCreator = new PlainResponse(challenge.creatorId, Strings.yourChallengeAccepted);
        PlainResponse responseToPlayer = new PlainResponse(user.id,
                String.format(Strings.newGamePhrase, challenge.game.digitsToGuess.length));
        return Response.compose(responseToCreator, responseToPlayer);
    }
}
