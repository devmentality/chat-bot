package main.States;

import main.*;
import main.Data.ChallengeDescription;
import main.Data.ChallengeRepository;
import main.Data.INewRepository;
import main.Data.User;
import main.Resources.Strings;

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
            SelectorResponse difficultySelector = new SelectorResponse(user.id, "Choose difficulty");
            difficultySelector.addOption("easy");
            difficultySelector.addOption("medium");
            difficultySelector.addOption("hard");

            bot.changeState(bot.chooseGameDifficultyState);
            return Response.compose(difficultySelector);
        }
        else if (query.equals("challenge")) {
            PlainResponse challengeDescriptions = new PlainResponse(user.id);
            challengeDescriptions.addMessageToContent("Available challenges");

            SelectorResponse challengeSelector = new SelectorResponse(user.id, "Choose challenge:");
            for (ChallengeDescription challengeDescription : challengeRepository.allChallengesDescriptions())
            {
                challengeSelector.addOption(String.format("%d", challengeDescription.creatorId));
                challengeDescriptions.addMessageToContent(
                        String.format("%d Default challenge", challengeDescription.creatorId));
            }
            challengeSelector.addOption("decline");
            bot.changeState(bot.chooseChallengeState);
            return Response.compose(challengeDescriptions, challengeSelector);
        }
        bot.changeState(bot.initializedState);
        return Response.compose(new PlainResponse(user.id, Strings.iDontUnderstand));
    }
}
