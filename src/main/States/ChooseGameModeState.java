package main.States;

import main.*;
import main.Data.ChallengeDescription;
import main.Data.ChallengeRepository;
import main.Data.INewRepository;
import main.Data.User;
import main.Resources.Strings;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChooseGameModeState extends StateBase
{
    private ChallengeRepository challengeRepository;

    public ChooseGameModeState(Bot bot, INewRepository repository, ChallengeRepository challengeRepository)
    {
        super(bot, repository);
        this.challengeRepository = challengeRepository;
    }

    @Override
    public ArrayList<Response> handleNoncommandRequest(User user, String query)
    {
        if (query.equals("classic"))
        {
            SelectorResponse difficultySelector = new SelectorResponse(user.id, Strings.difficutySelectorPreamble);
            difficultySelector.addOption("easy");
            difficultySelector.addOption("medium");
            difficultySelector.addOption("hard");

            bot.changeState(bot.chooseGameDifficultyState);
            return Response.compose(difficultySelector);
        }
        else if (query.equals("challenge"))
        {
            ArrayList<ChallengeDescription> allDescriptions = challengeRepository.allChallengesDescriptions();

            if (allDescriptions.isEmpty())
            {
                bot.changeState(bot.initializedState);
                return Response.compose(new PlainResponse(user.id, Strings.noAvailableChallenges));
            }

            SelectorResponse challengeSelector = constructChallengeSelector(user, allDescriptions);
            PlainResponse challengeDescriptions = constructChallengeDescriptions(user, allDescriptions);

            bot.changeState(bot.chooseChallengeState);
            return Response.compose(challengeDescriptions, challengeSelector);
        }
        bot.changeState(bot.initializedState);
        return Response.compose(new PlainResponse(user.id, Strings.iDontUnderstand));
    }

    private SelectorResponse constructChallengeSelector(User user, ArrayList<ChallengeDescription> allChallenges)
    {
        SelectorResponse challengeSelector = new SelectorResponse(user.id, Strings.challengeSelectorPreamble);

        for (ChallengeDescription challengeDescription : allChallenges)
            challengeSelector.addOption(String.format("%d", challengeDescription.creatorId));

        challengeSelector.addOption("decline");
        return challengeSelector;
    }

    private PlainResponse constructChallengeDescriptions(User user, ArrayList<ChallengeDescription> allChallenges)
    {
        PlainResponse challengeDescriptions = new PlainResponse(user.id);
        challengeDescriptions.addMessageToContent(Strings.challengeDescriptionsPreamble);

        for (ChallengeDescription challengeDescription : allChallenges)
            challengeDescriptions.addMessageToContent(
                    String.format("%d points %d", challengeDescription.creatorId, challengeDescription.points));

        return challengeDescriptions;
    }
}
